package com.hoan.lam.demo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonHelper<T> {

	private static final ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static final ObjectMapper get() {
		return mapper;
	}

	/**
	 * 
	 * @param object
	 *            the data needs convert
	 * @param clazzname
	 *            where is data come from
	 * @return object's string after transformation
	 */
	public static final String object2String(Object object, String clazzname) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("[ERROR][" + clazzname + "] " + e.getMessage());
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 
	 * @param object
	 *            the data needs convert
	 * @param clazzname
	 *            where is data come from
	 * @return object's string after transformed
	 */
	public static final <T> Optional<Object> string2object(String src, Class<?> clazz, String clazzname) {
		try {
			return Optional.ofNullable(mapper.readValue(src, clazz));
		} catch (IOException e) {
			log.error("[ERROR][" + clazzname + "] " + e.getMessage());
			return Optional.empty();
		}
	}

	public static final <T> T string2objects(String src, Class<?> clazz, String clazzname) {
		try {
			return mapper.readValue(src, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
		} catch (IOException e) {
			log.error("[ERROR][" + clazzname + "] " + e.getMessage());
			return null;
		}
	}
}
