package br.com.httpclientdemo.tests;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.httpclientdemo.base.BaseClass;
import br.com.httpclientdemo.entities.User;
import br.com.httpclientdemo.utils.ResponseUtils;

public class BodyTestWithSimpleMap extends BaseClass {
	


	@Test
	
	// test using unmarshall 
	public void returnsCorrectLogin() throws IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
		response = client.execute(get);
		
		User user = ResponseUtils.unmarshall(response, User.class);		
		Assert.assertEquals(user.getLogin(), "andrejss88");
		
	}

	@Test
	public void returnsCorrectId() throws IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
		response = client.execute(get);
		
		String jsonBody = EntityUtils.toString(response.getEntity());
		
		JSONObject jsonObject = new JSONObject(jsonBody);
		
		Integer IdValue = (Integer) getValueFor(jsonObject, User.ID);
		
		Assert.assertEquals(IdValue, "11834443");
		
	}
	private Object getValueFor(JSONObject jsonObject, String key) {
		return jsonObject.get(key);
		
	}

}
