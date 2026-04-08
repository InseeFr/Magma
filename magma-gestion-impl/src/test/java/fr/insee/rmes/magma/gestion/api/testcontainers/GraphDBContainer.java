package fr.insee.rmes.magma.gestion.api.testcontainers;

import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.MountableFile;

import java.io.IOException;

@Slf4j
public class GraphDBContainer extends GenericContainer<GraphDBContainer> {
    public static final String DOCKER_ENTRYPOINT_INITDB = "/docker-entrypoint-initdb";
    private String folder;

    public GraphDBContainer(final String dockerImageName) {
        super(dockerImageName);
        withExposedPorts(7200);
    }

    @Override
    public void start() {
        super.start();
        withInitFolder("/testcontainers").withExposedPorts(7200);

        withRepository("config.ttl");
        withTrigFiles("statements.trig");
    }

    private void clearStatements() {
        try {
            execInContainer("curl", "-s", "-o", "/dev/null", "-X", "DELETE", "http://localhost:7200/repositories/gestion/statements");
        } catch (IOException | InterruptedException e) {
            throw new AssertionError("Could not clear repository statements", e);
        }
    }

    public GraphDBContainer withInitFolder(String folder){
        this.folder = folder;
        return this;
    }

    public GraphDBContainer withRepository(String ttlFile) {
        try {
            String path = copyFile(ttlFile);
            ExecResult result = execInContainer("curl", "-s", "-w", "\nHTTP_STATUS:%{http_code}", "-X", "POST", "-H", "Content-Type:multipart/form-data", "-F", "config=@" + path, "http://localhost:7200/rest/repositories");
            String output = result.getStdout();
            log.info("Repository creation response: {}", output);
            String httpCode = output.substring(output.lastIndexOf("HTTP_STATUS:") + 12).trim();
            if (!"201".equals(httpCode) && !"409".equals(httpCode)) {
                throw new AssertionError("Repository creation failed with HTTP status: " + httpCode + " body: " + output);
            }
        } catch (IOException | InterruptedException e) {
            throw new AssertionError("The TTL file was not loaded", e);
        }
        return this;
    }

    public GraphDBContainer withTrigFiles(String file) {
        try {
            String path = copyFile(file);
            ExecResult result = execInContainer("curl", "-s", "-o", "/dev/null", "-w", "%{http_code}", "-X", "POST", "-H", "Content-Type: application/x-trig", "--data-binary", "@" + path, "http://localhost:7200/repositories/gestion/statements");
            String httpCode = result.getStdout().trim();
            log.info("Load trig HTTP status: {}", httpCode);
            if (!"204".equals(httpCode)) {
                throw new AssertionError("Trig file loading failed with HTTP status: " + httpCode);
            }
        } catch (IOException | InterruptedException e) {
            throw new AssertionError("The Trig file was not loaded", e);
        }
        return this;
    }

    private String copyFile(String file) throws IOException, InterruptedException {
        String fullPath = DOCKER_ENTRYPOINT_INITDB  + "/" + file;
        copyFileToContainer(MountableFile.forClasspathResource(this.folder + "/" + file), fullPath);
        return fullPath;
    }
}

