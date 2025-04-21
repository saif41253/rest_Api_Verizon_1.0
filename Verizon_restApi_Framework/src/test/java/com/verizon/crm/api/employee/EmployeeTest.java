package com.verizon.crm.api.employee;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
import com.verizon.crm.api.constants.endpoints.IEndPoint;
import com.verizon.crm.api.generic.DataBaseUtility;
import com.verizon.crm.api.generic.FileUtility;
import com.verizon.crm.api.generic.JavaUtility;
import com.verizon.crm.api.pojoclass.EmployeePOJO;
import com.verizon.crm.api.pojoclass.ProjectPojoUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EmployeeTest {
	JavaUtility jLib = new JavaUtility();
	DataBaseUtility dLib = new DataBaseUtility();
	FileUtility fLib = new FileUtility();
	@Test
	public void addEmployee() throws SQLException, IOException {
		
		// create a pojo class object
		String BaseURI = fLib.getDataFromPropertyFile("BASEUri");
		
				String projName = "Bharti_"+jLib.getRandomNumber();
				String userName = "user_"+jLib.getRandomNumber();
				ProjectPojoUtility  pobj= new ProjectPojoUtility(projName,"Created","Saif",0);
				
				// api 1 create a project
				 given().contentType(ContentType.JSON)
				 .body(pobj)
				.when().post(BaseURI+IEndPoint.ADDProj)
				 .then().assertThat().statusCode(201).and().assertThat()
				 .body("msg", Matchers.equalTo("Successfully Added")).log().all();
				
				// api02 add employee to same project
				
				EmployeePOJO epojo = new EmployeePOJO("Architect", "24/11/1996", "saif@gmail.com", 
						"Saif"+jLib.getRandomNumber(), 05, "9122648874", projName, "Employee", userName);
				given().contentType(ContentType.JSON).body(epojo)
				.when().post(BaseURI+IEndPoint.ADDEmp)
				.then().assertThat().statusCode(201)
				.and().time(Matchers.lessThan(3000L))
				.assertThat().body("msg", Matchers.equalTo("Employee Added Successfully"))
				.log().all();
				// verify project name in dB
				dLib.getConnection();
				ResultSet flag = dLib.executeSelectQuery("select * from employee", 5, userName);
				Assert.assertEquals(flag, "project name is verified");
				dLib.closeConnection();
				
//				boolean flag = false;
//				Driver driverRef = new Driver();
//				DriverManager.registerDriver(driverRef);
//				Connection conn = DriverManager.getConnection("jdbc:mysql://49.249.28.218:8091:3333/ninza_hrm","root@%", "root");
//				Statement state = conn.createStatement();
//				ResultSet result = state.executeQuery("select * from employee");
//				while(result.next()) {
//					//System.out.println(result.getString(4));
//					if(result.getString(4).equals(userName)) {
//						flag = true;
//						break;
//					}
//				}
//				conn.close();
//				Assert.assertTrue(flag,"employee is not verified in dB");
				

				
			}
 
	}
