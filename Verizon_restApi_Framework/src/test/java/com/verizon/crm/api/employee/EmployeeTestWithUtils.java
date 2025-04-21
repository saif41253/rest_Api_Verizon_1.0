package com.verizon.crm.api.employee;

import static io.restassured.RestAssured.given;

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
import com.verizon.crm.api.pojoclass.EmployeePOJO;
import com.verizon.crm.api.pojoclass.ProjectPojoUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EmployeeTestWithUtils {
	@Test
public void addEmployee() throws SQLException {
		
		// create a pojo class object
				Random random = new  Random();
				int ranNum = random.nextInt(1000);
				String projName = "Bharti_"+ranNum;
				String userName = "user_"+ranNum;
				ProjectPojoUtility  pobj= new ProjectPojoUtility(projName,"Created","Saif",0);
				
				// api 1 create a project
				 Response resp = given().contentType(ContentType.JSON).body(pobj)
				.when().post("http://49.249.28.218:8091/addProject");
				 resp.then().assertThat().statusCode(201).and().assertThat().body("msg", Matchers.equalTo("Successfully Added")).log().all();
				
				// api02 add employee to same project
				
				EmployeePOJO epojo = new EmployeePOJO("Architect", "24/11/1996", "saif@gmail.com", 
						"Saif"+ranNum, 05, "9122648874", projName, "Employee", userName);
				given().contentType(ContentType.JSON).body(epojo).when().post("http://49.249.28.218:8091/employees")
				.then().assertThat().statusCode(201).and()
				.assertThat().body("msg", Matchers.equalTo("Employee Added Successfully"))
				.log().all();
				// verify emp name in dB
				boolean flag = false;
				Driver driverRef = new Driver();
				DriverManager.registerDriver(driverRef);
				Connection conn = DriverManager.getConnection("jdbc:mysql://49.249.28.218:8091:3333/ninza_hrm","root@%", "root");
				Statement state = conn.createStatement();
				ResultSet result = state.executeQuery("select * from employee");
				while(result.next()) {
					//System.out.println(result.getString(4));
					if(result.getString(4).equals(userName)) {
						flag = true;
						break;
					}
				}
				conn.close();
				Assert.assertTrue(flag,"employee is not verified in dB");

}
	}
