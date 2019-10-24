package br.com.httpclientdemo.tests;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import br.com.httpclientdemo.base.BaseClass;

public class Get200 extends BaseClass {
	
	@DataProvider
	private Object[][] endpoints(){
		return new Object[][] {
			{"/search/repositories?q=java"}, 
			{"/rate_limit"}
		};
	}
		
	@Test(dataProvider = "endpoints")
	public void baseUrlReturn200(String endpoint) throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT); 
		response = client.execute(get);
		actualStatus = response.getStatusLine().getStatusCode();
		
		assertEquals(actualStatus, 200);
	}
	

}
