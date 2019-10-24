package br.com.httpclientdemo.base;
import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
	
	protected static final String BASE_ENDPOINT = "https://api.github.com";
	protected int actualStatus;
	
	protected CloseableHttpClient client;
	protected CloseableHttpResponse response;

	
	@BeforeMethod
	public void setUp() {
		 client = HttpClientBuilder.create().build();
	}
	
	@AfterMethod
	public void tearDown() throws IOException {
		client.close(); 
		response.close();
	}
	
}
