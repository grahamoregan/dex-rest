package dex.rest;

import static org.junit.Assert.assertNotNull;

import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Test;

public class ObjectModelConvertorTest {

	@Test
	public void json() throws Exception {

		Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/sample.json"));

		ObjectModelConvertor omc = new ObjectModelConvertor();

		ObjectModel response = omc.json(reader);

		assertNotNull(response);

		assertNotNull(response.get("/name"));

	}

}
