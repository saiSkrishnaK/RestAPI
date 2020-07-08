package com.wipro;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class PutRequestTest {
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://reqres.in/";
	}
	
	@Test
	public void putRequest() {
		
		int userid = 2;
		String name = "morpheus";
		String job = "leader";
		
		String response = given().log().all().basePath("api/users/").header("Content-Type","application/json").body("{\n    \"name\": \""+name+"\",\n    \"job\": \""+job+"\"\n}")
				.when().put("/{user_id}",userid).then().log().all().assertThat().statusCode(200).header("Content-Type", "application/json; charset=utf-8").extract().response().asString();
				
		JsonPath jPath = new JsonPath(response);
		
		String expectedName = jPath.getString("name");
		assertEquals(name, expectedName);
		
		String expectedJob = jPath.getString("job");
		assertEquals(job, expectedJob);
		
		String time = jPath.getString("updatedAt");
		assertEquals(true, !time.isEmpty());
	}

}
