package com.primesoftinc.message.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.xml.bind.JAXBException;

public interface CoreService {
	public Object deserializeJsonStrToJavaObj(String jsonStr, Class<?> claz) throws JsonMappingException, JsonProcessingException;
	public String serializeJavaObjToJsonStr(Object javaObj) throws JsonProcessingException;
	public Object unmarshal(String xmlStr, Class<?> claz) throws JAXBException;
	public String marshal(Class<?> claz, Object obj) throws JAXBException,InstantiationException, IllegalAccessException;
}
