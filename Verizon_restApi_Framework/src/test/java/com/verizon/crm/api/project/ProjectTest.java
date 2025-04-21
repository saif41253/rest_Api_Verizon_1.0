package com.verizon.crm.api.project;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
import com.verizon.crm.api.constants.endpoints.IEndPoint;
import com.verizon.crm.api.generic.DataBaseUtility;
import com.verizon.crm.api.generic.FileUtility;
import com.verizon.crm.api.generic.JavaUtility;
import com.verizon.crm.api.pojoclass.ProjectPojoUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class ProjectTest {
	String projName ;
	ProjectPojoUtility pObj;
	JavaUtility jLib = new JavaUtility();
	FileUtility fLib = new FileUtility();
	DataBaseUtility dLib = new DataBaseUtility();
	
	@Test
	public void createProject() throws SQLException, IOException {
		
		String BASEUri = fLib.getDataFromPropertyFile("BASEUri");
		// create an object to pojo class
		Random random = new Random();
		int ranNum = random.nextInt(1000);
		String expdata= "Successfully Added";
	    projName = "ABB_"+ranNum;
		pObj = new ProjectPojoUtility(projName, "Created", "Saif", 0);
		
		Response resp=given().contentType(ContentType.JSON)
				.body(pObj)
				.when()
				.post(BASEUri+IEndPoint.ADDProj);
		resp.then()
		.assertThat().statusCode(201)
		.assertThat().contentType(ContentType.JSON)
		.assertThat().time(Matchers.lessThan(3000L))
		.log().all();
		String actdata = resp.jsonPath().get("msg");
		Assert.assertEquals(expdata, actdata);
		
		// verify the projName in API layer
		dLib.getConnection();
		ResultSet flag = dLib.executeSelectQuery("select * from project", 4,projName);
		Assert.assertEquals(flag, "Project is verified in dB");
		dLib.closeConnection();
		
//		boolean flag = false;
//		Driver driverRef = new Driver();
//		DriverManager.registerDriver(driverRef);
//		Connection conn = DriverManager.getConnection("jdbc:mysql://49.249.28.218:8091:3333/ninza_hrm","root@%", "root");
//		Statement state = conn.createStatement();
//		ResultSet result = state.executeQuery("select * from project");
//		while(result.next()) {
//			//System.out.println(result.getString(4));
//			if(result.getString(4).equals(projName)) {
//				flag = true;
//				break;
//			}
//		}
//		conn.close();
//		Assert.assertTrue(flag,"Proj is not verified in dB");
		

}
	@Test (dependsOnMethods = "createProject")
	public void createDuplicateProject() throws IOException {
		String BASEUri = fLib.getDataFromPropertyFile("BASEUri");
		given().contentType(ContentType.JSON).body(pObj)
		.when()
				.post(BASEUri+IEndPoint.ADDProj)
		.then()
		.assertThat().statusCode(409)
		.log().all();
		
	
		
	}

}
