package com.crm.verizon.dummy;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CampaignTest {
	JSONObject jsonObject;
	@Test
	public void createCompaign() {
		jsonObject = new JSONObject();
		jsonObject.put("campaignName", "Porche_300L");
		jsonObject.put("description", "This sale belongs to festive Offer");
		jsonObject.put("status", "OnGoing");
		Response resp = given()
		.contentType(ContentType.JSON)
		.body(jsonObject)
		.when().post("http://49.249.28.218:8991/campaign/create");
		
		resp.then().log().all().assertThat().statusCode(201);
		
	}
	String token = "actsASm65BH6";
	@Test
	public void getCompaign() {
		given().contentType(ContentType.JSON).auth().oauth2(token)
		.when().get("http://49.249.28.218:8991/campaign/CAMP_105")
		.then().log().all().assertThat().statusCode(200);
	}
	@Test
	public void updateCompaign() {
		Response resp = given()
		.contentType(ContentType.JSON)
		.body(jsonObject)
		.when().patch("http://49.249.28.218:8991/campaign/create");
		resp.then().log().all().assertThat().statusCode(200);
		
		
	}
	@Test
	public void deleteCompaign() {
		given().contentType(ContentType.JSON)
		.when().delete("http://49.249.28.218:8991/campaign/CAMP_105")
		.then().log().all().assertThat().statusCode(204);
		
	}
	
	
	

	
	
	

}
