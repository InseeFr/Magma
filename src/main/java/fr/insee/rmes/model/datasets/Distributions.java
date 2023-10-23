package fr.insee.rmes.model.datasets;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.insee.rmes.modelSwagger.dataset.LangContent;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.annotation.Generated;
import java.util.List;
@RequiredArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "identifier",
        "byteSize",
        "created",
        "description",
        "downloadURL",
        "format",
        "modified",
        "title",
        "uri"
})
@Generated("jsonschema2pojo")
public class Distributions {
    @JsonProperty("identifier")
    private String identifier;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("byteSize")
    private String byteSize;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("created")
    private String created;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("description")
    private List<LangContent> description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("downloadURL")
    private List<String> downloadURL;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("format")
    private String format;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("modified")
    private String modified;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("title")
    private List<LangContent> title;
    @JsonProperty("uri")
    private String uri;

    public Distributions(String identifier, String uri) {
        this.identifier = identifier;
        this.uri = uri;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getByteSize() {
        return byteSize;
    }

    public void setByteSize(String byteSize) {
        this.byteSize = byteSize;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<LangContent> getDescription() {
        return description;
    }

    public void setDescription(List<LangContent> description) {
        this.description = description;
    }

    public List<String> getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(List<String> downloadURL) {
        this.downloadURL = downloadURL;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public List<LangContent> getTitle() {
        return title;
    }

    public void setTitle(List<LangContent> title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "{" +
                "identifier='" + identifier + '\'' +
                ", byteSize='" + byteSize + '\'' +
                ", created='" + created + '\'' +
                ", description=" + description +
                ", downloadURL=" + downloadURL +
                ", format='" + format + '\'' +
                ", modified='" + modified + '\'' +
                ", title=" + title +
                ", uri='" + uri + '\'' +
                '}';
    }
}
