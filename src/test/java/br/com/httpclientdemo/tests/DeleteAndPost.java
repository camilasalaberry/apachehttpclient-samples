package br.com.httpclientdemo.tests;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.httpclientdemo.base.BaseClass;

public class DeleteAndPost extends BaseClass{
	@Test
	public void deleteIsSuccessful() throws ClientProtocolException, IOException {
		HttpDelete request = new HttpDelete("https://api.github.com/repos/andrejss8/deleteme");
		request.setHeader(HttpHeaders.AUTHORIZATION, "token " + "personaltokengeneratedbygithub");
		
		response = client.execute(request);
		
		int actualStatusCode = response.getStatusLine().getStatusCode();
		
		Assert.assertEquals(actualStatusCode, 204);
		
	}

	@Test
	public void createRepoReturns201() throws ClientProtocolException, IOException {
		HttpPost request = new HttpPost("https://api.github.com/user/repos");
		// basic authentication
		String auth = "youremail@domain.com" + ":" + "yourpass";
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));
		String authHeader = "Basic "  + new String(encodedAuth);
		request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
		
		String json = "{\"name\": \"deleteme\"}";
		
		request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
		
		response = client.execute(request);
		
		int actualStatusCode = response.getStatusLine().getStatusCode();
		
		Assert.assertEquals(actualStatusCode, 201);
		
	}
}
