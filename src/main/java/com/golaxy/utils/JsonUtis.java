package com.golaxy.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonUtis {
	@Autowired
	public ObjectMapper mapper;

	public <T> T deserialization(String json) throws Exception {

		return mapper.readValue(json, new TypeReference<T>() {});
	}

	public <T> T deserialization(String json, Class<T> elementClasses) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		mapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
		JavaType javaType = mapper.getTypeFactory().constructType(elementClasses);
		return mapper.readValue(json, javaType);
	}

	public <T> List<T> deserializationArray(String json, Class<T> elementClasses) throws Exception {
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, elementClasses);
		return mapper.readValue(json, javaType);
	}
}
