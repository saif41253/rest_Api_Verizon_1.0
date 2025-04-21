package com.crm.verizon.dummy;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class InvoiceTest {
	JSONObject jsonObject;
	@Test
	public void createInvoice() {
		jsonObject = new JSONObject();
		jsonObject.put("subject", "Audi_200L");
		jsonObject.put("address", "BTM 2nd Stage, Bengaluru");
		Response resp = given()
		.contentType(ContentType.JSON)
		.body(jsonObject)
		.when().post("http://49.249.28.218:8991/invoice/create");
		resp.then().log().all().assertThat().statusCode(201);
		
	}
	String token = "as_dfagnmM6juBFD9446ghxh";
	@Test
	public void getInvoice() {
			given().contentType(ContentType.JSON).auth().oauth2(token)
			.when().get("http://49.249.28.218:8991/contact/CONT_PROJ456")
			.then().log().all().assertThat().statusCode(200);
		
	}

}
