package com.wipro;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class GetRequestTest {
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://reqres.in/";
	}
	
	@Test
	public void getRequest() { 
		
		int userid = 2;
		
		String response = given().log().all().basePath("/api/users/").pathParam("user_id", userid).when().get("/{user_id}").then().log().all()
		.assertThat().statusCode(200).header("Content-Type", "application/json; charset=utf-8").extract().response().asString();
		
		JsonPath jPath = new JsonPath(response);
		String id = jPath.getString("data.id");
		assertEquals(id, String.valueOf(userid));
		
	}

}
