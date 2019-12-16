package com.hoan.lam.demo.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonUtil 提供Json和对象之间的转换。
 * 
 * @author Kingo.Liang
 *
 */
public class JsonUtil {
	private static ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}

	public static String toJson(Object obj) {
		try {
			return getObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return "";
		}
	}

	public static String toJSonString(Object o) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			StringWriter writer = new StringWriter();
			JsonFactory factory = mapper.getFactory();
			JsonGenerator gen = factory.createGenerator(writer);
			mapper.writeValue(gen, o);
			gen.close();
			String json = writer.toString();
			writer.close();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static <T> T str2bean(String json, Class<T> clazz) {
		try {
			ObjectMapper mapper = getObjectMapper();
			return mapper.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public static Object str2list(String json, TypeReference valueTypeRef) {
		try {
			ObjectMapper mapper = getObjectMapper();
			Object domain = mapper.readValue(json, valueTypeRef);
			return domain;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		ObjectMapper mapper = getObjectMapper();
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	public static ArrayList<?> str2list(String json, Class<?> clazz) {
		ObjectMapper mapper = getObjectMapper();
		JavaType javaType = getCollectionType(ArrayList.class, clazz);
		ArrayList<?> lst = null;
		try {
			lst = (ArrayList<?>) mapper.readValue(json, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lst;
	}

	// new TypeReference<Map<String,Object>>() { }
	@SuppressWarnings("rawtypes")
	public static Object str2map(String json, TypeReference valueTypeRef) {
		try {
			ObjectMapper mapper = getObjectMapper();
			Object domain = mapper.readValue(json, valueTypeRef);
			return domain;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static JsonNode emptyObject() {
		return getObjectMapper().createObjectNode();
	}
	
	public static JsonNode emptyArray() {
		return getObjectMapper().createArrayNode();
	}
}
