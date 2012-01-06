package dex.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ObjectModelTest {

	private ObjectModelConvertor objectModelConvertor = new ObjectModelConvertor();

	private ObjectModel objectModel;

	@Before
	public void setup() {

		Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/sample.json"));

		objectModel = objectModelConvertor.json(reader);
	}

	@Test
	public void test() {

		assertEquals(Double.valueOf(3), objectModel.count("/list"));

		assertEquals("b", objectModel.get("/list[2]"));
		assertEquals(Integer.valueOf(25), objectModel.get("/person/age"));

		assertEquals(Double.valueOf(3.0), objectModel.add("/one", "/two"));
		assertEquals(Double.valueOf(1.0), objectModel.subtract("/two", "/one"));
		assertEquals(Double.valueOf(2.0), objectModel.multiply("/two", "/one"));
		assertEquals(Double.valueOf(2.0), objectModel.divide("/two", "/one"));

		assertFalse(objectModel.equals("/two", "/one"));
		assertTrue(objectModel.equals("/two", "/two"));
		assertTrue(objectModel.notEquals("/two", "/one"));

		assertTrue(objectModel.or("/two", 2, 1));
		assertTrue(objectModel.and("/two", 2, 2));

		assertEquals(Double.valueOf(0.0), objectModel.mod("/two", "/one"));

	}

	@Test
	public void testList() throws Exception {

		Map<String, Object> map = objectModel.getMap();

		assertNotNull(map.get("builds"));

		System.out.println(objectModel.get("/builds/number"));

	}

	/**
	 * This is the example used in the README.md
	 * 
	 * @throws Exception
	 */
	@Test
	public void testExample() throws Exception {

		Reader reader = new StringReader("{ \"list\":[\"a\",\"b\",\"c\"] }");

		ObjectModel model = objectModelConvertor.json(reader);

		assertEquals(Double.valueOf(3), model.count("/list"));

	}

}
