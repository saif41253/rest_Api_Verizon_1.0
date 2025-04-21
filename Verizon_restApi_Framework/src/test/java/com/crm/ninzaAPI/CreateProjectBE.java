package com.crm.ninzaAPI;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
import com.verizon.crm.api.generic.JavaUtility;
import com.verizon.crm.api.pojoclass.ProjectPojoUtility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateProjectBE {
	// create project using restApi
	@Test 
	public void postProject() throws SQLException {
		JavaUtility jLib = new JavaUtility();
		int randomNo = jLib.getRandomNumber();
		ProjectPojoUtility pobj = new ProjectPojoUtility("mri_"+randomNo, "Created", "Simar", 0);
		
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("createdBy", "Saif");
//		jsonObject.put("projectName", "awfis_45");
//		jsonObject.put("status", "Created");
//		jsonObject.put("teamSize", 0);
		
		Response resp = given()
		.contentType(ContentType.JSON)
		.body(pobj)
		.when().post("http://49.249.28.218:8091/addProject");
		
		resp.then().log().all().assertThat().statusCode(201);
		Object projName = resp.jsonPath().get("content.projectName");
		System.out.println(projName);
		
		//get project in dB
		boolean flag = false;
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:mysql://49.249.28.218:8091:3333/ninza_hrm","root", "root");
		Statement state = conn.createStatement();
		ResultSet result = state.executeQuery("select * from project");
		while(result.next()) {
			if(result.getString(4).equals(projName)) {
				flag = true;
				break;
			}
		}
		conn.close();
		Assert.assertTrue(flag,"Proj is not verified in dB");

		
	}
	
}
