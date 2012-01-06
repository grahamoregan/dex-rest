package dex.rest;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class ObjectModelConvertor {

	private ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	public ObjectModel json(Reader reader) {

		try {

			Map<String, Object> map = mapper.readValue(reader, HashMap.class);

			return ObjectModel.create(map);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
