package br.com.httpclientdemo.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.httpclientdemo.base.BaseClass;
import br.com.httpclientdemo.utils.ResponseUtils;

public class ResponseHeaders extends BaseClass {
	
	@Test
	public void contentTypeIsJson() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT);
		response = client.execute(get);
		
		Header contentType = response.getEntity().getContentType();
		assertEquals(contentType.getValue(), "text/html; charset=UTF-8");
		
		ContentType ct = ContentType.getOrDefault(response.getEntity());
		assertEquals(ct.getMimeType(), "application/json");
	}
	
	@Test
	public void serverIsGithub() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT);
		response = client.execute(get);
		 
		String headerValue = ResponseUtils.getHeader(response ,"server");
		
		assertEquals(headerValue, "GitHub.com");
	}
	
	@Test 
	public void eTagIsPresent() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT);
		response = client.execute(get);
		
	//	boolean eTagIsPresent = ResponseUtils.headerPresent(response, "eTag");
		
	//	Assert.assertTrue(eTagIsPresent);

	}



}
