package br.com.httpclientdemo.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.httpclientdemo.entities.User;

public class ResponseUtils {
	
	public static String getHeader(CloseableHttpResponse response, String headerName) {
		Header[] headers = response.getAllHeaders();
		List<Header> httpHeaders = Arrays.asList(headers);
		String returnHeader = null;
		
		for (Header header : httpHeaders) {
			if(headerName.equalsIgnoreCase (header.getName())) {
				returnHeader = header.getValue();
			}
		}
		
		if (returnHeader.isEmpty()) {
			throw new RuntimeException("Didn't find the header: " +   headerName);
		}
		return returnHeader;
	}
	
	public static User unmarshall(CloseableHttpResponse response, Class<User> clazz) throws ParseException, IOException {
		String jsonBody = EntityUtils.toString(response.getEntity());
		return new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.readValue(jsonBody, clazz);
	}
	
	// java 8 implementation
//	public static boolean headerPresent(CloseableHttpResponse response, String headerName) {
//		List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
//		
//		return httpHeaders.stream().anyMatch(header -> header.getName().equalsIgnoreCase(headerName));
//		
//	}

}
