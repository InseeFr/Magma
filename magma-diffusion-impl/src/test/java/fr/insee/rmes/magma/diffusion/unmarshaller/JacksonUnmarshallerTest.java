package fr.insee.rmes.magma.diffusion.unmarshaller;


import fr.insee.rmes.magma.queryexecutor.Csv;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonUnmarshallerTest {

	@Test
	void worksWithRecords(){
		JacksonUnmarshaller jacksonUnmarshaller = new JacksonUnmarshaller();
		assertThat(jacksonUnmarshaller.unmarshal(new Csv("""
				nom, prenom
				bibonne,fabrice
				"""), Personne.class)).contains(new Personne("bibonne", "fabrice"));
	}

	record Personne(String nom, String prenom) {
	}
}