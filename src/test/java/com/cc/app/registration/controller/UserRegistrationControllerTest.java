package com.cc.app.registration.controller;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cc.app.registration.model.User;

import junit.framework.TestCase;

public class UserRegistrationControllerTest extends TestCase{
	//private static final String BASIC_CREDENTIALS = "Basic " + Base64.encodeBase64String("test:secret".getBytes());

	private CloseableHttpClient client;

	private ObjectMapper mapper;
	
	private String url = "http://localhost:8080/UserRegREST/user/";
	
	@Before
	public void setUp() throws Exception {
		client = HttpClientBuilder.create().build();
		mapper = new ObjectMapper();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testListAllUsers() throws ClientProtocolException, IOException{
		HttpGet request;
		CloseableHttpResponse response;
		request = new HttpGet(url );
		response = client.execute(request);
		//response.close();
		InputStream dataStream = response.getEntity().getContent();
		TypeReference ref = new TypeReference<List<User>>() { };		
		List<User> listAll = mapper.readValue(dataStream,ref);
		System.out.println( listAll );
		assertEquals(SC_OK,response.getStatusLine().getStatusCode());
	}

	@Test
	public final void testGetUser() throws ClientProtocolException, IOException,Exception {
		Integer id = 1;
		System.out.println(url + id);
		HttpGet request = new HttpGet(url + id);
		
		CloseableHttpResponse response = client.execute(request);
		assertEquals(SC_OK, response.getStatusLine().getStatusCode());
		User user  = parseEntity(response.getEntity(), User.class);
		System.out.println(user.toString());
		assertEquals(id, user.getUserId());
		assertEquals("User No 1", user.getUsername());
		assertEquals("User No@gmail.com", user.getEmail() );	
	}
	
	/*@Test
	public final void testGetUserFailed() throws ClientProtocolException, IOException,Exception{
		HttpGet request = new HttpGet(url + "/99999999");
		CloseableHttpResponse response = client.execute(request);
		response.close();
		assertEquals(SC_NOT_FOUND, response.getStatusLine().getStatusCode());
	}*/

	@Test
	public final void testCreateUserSuccess() throws ClientProtocolException, IOException,Exception{
		CloseableHttpResponse response = createSample();
		//response.close();
		Integer id = parseEntity(response.getEntity(), Integer.class);
		assertNotNull(id);

		String expectedLocation = url + id;
		String returnedLocation = response.getHeaders("Location")[0].getValue();
		assertEquals(expectedLocation, returnedLocation);

		HttpGet request = new HttpGet(returnedLocation);
		response = client.execute(request);
		response.close();
	}
	
	
	/*@Test
	public final void testDeleteUserSuccess() throws ClientProtocolException, IOException,Exception{
		Integer id = 9;
		HttpDelete request = new HttpDelete(url + id);
		CloseableHttpResponse response = client.execute(request);
		response.close();
		assertEquals(SC_NO_CONTENT, response.getStatusLine().getStatusCode());
	}*/
	
	private CloseableHttpResponse createSample() throws Exception {
		User user = new User();
		user.setUserId(3);
		user.setUsername("John");
		user.setEmail("john@gmail.com");

		HttpPost request = new HttpPost(url );
		request.setEntity(EntityBuilder.create().setText(mapper.writeValueAsString(user)).build());
		request.addHeader("Content-Type", "application/json");

		CloseableHttpResponse response = client.execute(request);
		response.close();

		return response;
	}

	

	private <T> T parseEntity(HttpEntity entity, Class<T> type) throws Exception {
		return mapper.readValue(entity.getContent(), type);
	}

	private HttpEntity createEntity(Object object) throws Exception {
		return EntityBuilder.create().setText(mapper.writeValueAsString(object)).build();
	}
}
