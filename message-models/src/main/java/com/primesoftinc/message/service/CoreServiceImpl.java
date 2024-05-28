/**
 * 
 */
package com.primesoftinc.message.service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * @author Muralidhar Patnaikuni
 *
 */
@Configuration
@Service
public class CoreServiceImpl implements CoreService {

	// >>>>>>>> DESERIALIZATION
	@Override
	public Object deserializeJsonStrToJavaObj(String jsonStr, Class<?> claz)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Object obj = mapper.readValue(jsonStr, claz);
		return obj;
	}

	// >>>>>>>>>> SERIALIZATION
	@Override
	public String serializeJavaObjToJsonStr(Object javaObj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(javaObj);
		// System.out.println(json);
		return json;
	}

	// >>>>>>>>>> UNMARSHALLING
	@Override
	public Object unmarshal(String xmlStr, Class<?> claz) {
		Object obj=null;
		
		try(StringReader reader = new StringReader(xmlStr);) {
			JAXBContext context = JAXBContext.newInstance(claz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			obj = unmarshaller.unmarshal(reader);
			return obj;
		}catch(Exception e) {
			e.printStackTrace();
			return obj;
		}
	}

	// >>>>>>>>> MARSHALLING
	@Override
	public String marshal(Class<?> claz, Object obj)
			throws JAXBException, InstantiationException, IllegalAccessException {
		JAXBContext context = JAXBContext.newInstance(claz);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		String string = null;
		try (StringWriter writer = new StringWriter();) {
			marshaller.marshal(obj, writer);
			string = writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return string;
	}

	

}
