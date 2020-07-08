package com.wipro;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class PostRequestTest {
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://reqres.in/";
	}
	
	@Test
	public void postRequest() { 
		
		String name = "morpheus";
		String job = "leader";
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("name", name);
		map.put("job", job);
		
		String response = given().log().all().basePath("api/users/").contentType(ContentType.JSON).body(map).when().post().then().log().all()
		.assertThat().statusCode(201).header("Content-Type", "application/json; charset=utf-8").extract().response().asString();
		
		JsonPath jPath = new JsonPath(response);
		
		String expectedName = jPath.getString("name");
		assertEquals(name, expectedName);
		
		String expectedJob = jPath.getString("job");
		assertEquals(job, expectedJob);
		
		String id = jPath.getString("id");
		assertEquals(true, !id.isEmpty());
		
		String time = jPath.getString("createdAt");
		assertEquals(true, !time.isEmpty());
		
	}

}
