package com.wisdge.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wisdge.utils.exceptions.IllegalFormatException;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSonUtils {

	/**
	 * 将一个JAVA对象转义为JSON字符串
	 *
	 * @param object
	 * @return String
	 * @throws IOException
	 */
	public static String parse(Object object) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModules(new JavaTimeModule());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		objectMapper.setDateFormat(format);
		return objectMapper.writeValueAsString(object);
	}

	/**
	 * 将JSON字符串解析为JAVA对象.
	 * <p>
	 * <ol>
	 * <li>如果传入的json字符串为null，则返回null。</li>
	 * <li>如果传入的json字符串为empty，则返回“”</li>
	 * <li>正常格式的json返回为List或者Map对象</li>
	 * </ol>
	 * </p>
	 *
	 * @param json
	 *            被转义的字符串
	 * @return Java对象
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws IllegalFormatException
	 */
	public static Object read(String json) throws IOException, IllegalFormatException {
		if (json == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.configure(Feature.ALLOW_YAML_COMMENTS, true);
		mapper.configure(Feature.IGNORE_UNDEFINED, true);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(format);
		if (json.trim().startsWith("[")) {
			return mapper.readValue(json.getBytes("UTF-8"), List.class);
		} else if (json.trim().startsWith("{")) {
			return mapper.readValue(json.getBytes("UTF-8"), Map.class);
		} else {
			if (StringUtils.isEmpty(json)) {
				return new HashMap<String, Object>();
			} else {
				throw new IllegalFormatException("错误的JSON字符串格式:\n" + json);
			}
		}
	}

	public static <T> T read(String json, Class<T> valueType) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.configure(Feature.ALLOW_YAML_COMMENTS, true);
		mapper.configure(Feature.IGNORE_UNDEFINED, true);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(format);
		return mapper.readValue(json.getBytes("UTF-8"), valueType);
	}
}
