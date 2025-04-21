package com.crm.verizon.dummy;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ContactTest {
	JSONObject jsonObject;
	@Test
	public void createContactTest() {
		jsonObject = new JSONObject();
		jsonObject.put("lastName", "Kaur");
		jsonObject.put("address", "BTM 2nd Stage, BTM Layout, Pin=560078");
		Response resp = given()
		.contentType(ContentType.JSON)
		.body(jsonObject)
		.when().post("http://49.249.28.218:8991/contact/create");
		resp.then().log().all().assertThat().statusCode(201);
		
	}
	String token = "actsASm65BH6";
	@Test
	public void getContactTest() {
		given().contentType(ContentType.JSON).auth().oauth2(token)
		.when().get("http://49.249.28.218:8991/contact/CONT_PROJ456")
		.then().log().all().assertThat().statusCode(200);
		
	}
	@Test
	public void updateContact() {
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(jsonObject)
				.when().patch("http://49.249.28.218:8991/contact/create");
				resp.then().log().all().assertThat().statusCode(200);
	}
	@Test
	public void deleteContact() {
		given().contentType(ContentType.JSON)
		.when().delete("http://49.249.28.218:8991/contact/CONT_PROJ456")
		.then().log().all().assertThat().statusCode(204);
	}

}
