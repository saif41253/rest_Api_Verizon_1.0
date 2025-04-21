package com.verizon.crm.api.project;

import static io.restassured.RestAssured.given;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
import com.verizon.crm.api.generic.JavaUtility;
import com.verizon.crm.api.pojoclass.ProjectPojoUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProjectTestWithUtils {
	JavaUtility jLib = new JavaUtility();
	String projName ;
	ProjectPojoUtility pObj;
	@Test
	public void createProject() throws SQLException {
		// create an object to pojo class
		String expdata= "Successfully Added";
	    projName = "ABB_"+jLib.getRandomNumber();
		pObj = new ProjectPojoUtility(projName, "Created", "Saif", 0);
		
		Response resp=given().contentType(ContentType.JSON).body(pObj)
				.post("http://49.249.28.218:8091/addProject");
		resp.then()
//		.assertThat().statusCode(201)
//		.assertThat().contentType(ContentType.JSON)
//		.assertThat().time(Matchers.lessThan(3000L))
		.log().all();
//		String actdata = resp.jsonPath().get("msg");
//		Assert.assertEquals(expdata, actdata);
		// verify the projName in API layer
		boolean flag = false;
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:mysql://49.249.28.218:8091:3333/ninza_hrm","root@%", "root");
		Statement state = conn.createStatement();
		ResultSet result = state.executeQuery("select * from project");
		while(result.next()) {
			//System.out.println(result.getString(4));
			if(result.getString(4).equals(projName)) {
				flag = true;
				break;
			}
		}
		conn.close();
		Assert.assertTrue(flag,"Proj is not verified in dB");
		

}
	@Test (dependsOnMethods = "createProject")
	public void createDuplicateProject() {
		Response resp=given().contentType(ContentType.JSON).body(pObj)
				.post("http://49.249.28.218:8091/addProject");
		resp.then()
		.assertThat().statusCode(409)
		.log().all();
		
	
		
	}


}
