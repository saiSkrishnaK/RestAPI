package com.wipro;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class DeleteRequestTest {
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://reqres.in/";
	}
	
	@Test
	public void deleteRequest() {
		
		int userid = 2;
		
		
		
		given().log().all().basePath("/api/users/").pathParam("user_id", userid).when().delete("/{user_id}",String.valueOf(userid)).then()
				.assertThat().statusCode(204).header("Content-Length", "0");
		
		Headers headers=RestAssured.given().basePath("/api/users/").pathParam("user_id", userid).when().delete("/{user_id}",String.valueOf(userid)).thenReturn().getHeaders();
		System.out.println("Headers: ");
		for(Header header:headers)
			System.out.println(header.getName() + " : " +header.getValue());
		
	}

}
