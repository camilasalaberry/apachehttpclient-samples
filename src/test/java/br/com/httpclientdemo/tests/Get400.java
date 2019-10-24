package br.com.httpclientdemo.tests;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import br.com.httpclientdemo.base.BaseClass;

public class Get400 extends BaseClass {
	
	@Test
	public void notLogged() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT + "/user"); 
		response = client.execute(get);
		actualStatus = response.getStatusLine().getStatusCode();
		
		assertEquals(actualStatus, 401);  
	}	
}
