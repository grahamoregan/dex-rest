package dex.rest;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ObjectModelConvertor {

	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * <p>This reads the content from the input reader and copies it into memory as a {@link String}
	 * so the Jackson parser can try to parse the contents more than once. If the content isn't made available
	 * like this then a failure in the first readValue() would close the {@link Reader} so the second
	 * attempt would always fail.</p>
	 * 
	 * <p>Two attempts will be made to parse the response, the first will try and convert to a {@link Map} as this
	 * is the most likely JSON structure, if the mapping fails then it will try to map to a {@link List}
	 * instead.</p>
	 * 
	 * <p>This *will* use a lot of memory as the whole string is copied into memory before the translation to
	 * a {@link List} or {@link Map} occurs but if you are expecting a large response from the {@link Reader}
	 * then you should probably be looking at a streaming API.</p>
	 * 
	 * <p>All methods will wrap any exceptions and rethrow them as {@link RuntimeException}</p>
	 * 
	 * @param reader
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ObjectModel json(Reader reader) {

		String internal = copy(reader);

		try {

			Map<String, Object> map = mapper.readValue(internal, HashMap.class);
			return ObjectModel.create(map);

		} catch (JsonMappingException e) {

			try {

				List<Object> list = mapper.readValue(internal, ArrayList.class);
				return ObjectModel.create(list);

			} catch (Exception re) {
				throw new RuntimeException(re);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * <p>Copies the contents of the reader to a String so the content can be used more than once. Be careful that the 
	 * input isn't too large as this could easily cause an OOM.</p>
	 * 
	 * @param reader
	 * @return
	 */
	public static String copy(Reader reader) {

		try {
			BufferedReader buffered = new BufferedReader(reader);
			StringBuilder builder = new StringBuilder();

			for (String temp = null; ((temp = buffered.readLine()) != null);)
				builder.append(temp).append("\n");

			return builder.toString();

		} catch (Throwable e) {
			throw new RuntimeException(e);
		}

	}

}
