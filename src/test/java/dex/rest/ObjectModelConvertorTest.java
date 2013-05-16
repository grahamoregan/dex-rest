package dex.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Test;

public class ObjectModelConvertorTest {

	@Test
	public void jsonObject() throws Exception {

		Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/sample.json"));

		ObjectModelConvertor omc = new ObjectModelConvertor();
		ObjectModel model = omc.json(reader);

		assertNotNull(model);
		assertNotNull(model.get("/name"));

	}

	@Test
	public void jsonArray() throws Exception {

		Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/sample_array.json"));

		ObjectModelConvertor omc = new ObjectModelConvertor();
		ObjectModel model = omc.json(reader);

		assertNotNull(model);
		assertNotNull(model.get("/name"));

		// get the second node's 'name' attribute
		assertEquals("john", model.get("(/)[2]/name"));

	}

}
