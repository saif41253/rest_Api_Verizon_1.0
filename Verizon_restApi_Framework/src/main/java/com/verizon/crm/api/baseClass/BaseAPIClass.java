package com.verizon.crm.api.baseClass;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.verizon.crm.api.generic.DataBaseUtility;
import com.verizon.crm.api.generic.ExcelUtility;
import com.verizon.crm.api.generic.FileUtility;
import com.verizon.crm.api.generic.JavaUtility;
import com.verizon.crm.api.generic.JsonUtility;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass {
	public JavaUtility jLib = new JavaUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public DataBaseUtility dLib = new DataBaseUtility();
	public FileUtility fLib = new FileUtility();
	public JsonUtility jsLib =new JsonUtility();
	public RequestSpecification specReqObj;
	public ResponseSpecification specResObj;
	
	
	@BeforeSuite
	public void configBS() throws IOException {
		dLib.getConnection();
		System.out.println("=====Conncted=====");
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		builder.setAuth(basic("username", "password"));
		builder.addHeader("", "");
		builder.setBasePath(fLib.getDataFromPropertyFile("BaseURI"));
		specReqObj = builder.build();
		
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		resBuilder.expectContentType(ContentType.JSON);
		specResObj = resBuilder.build();
	}
	@AfterSuite
	public void configAS() throws Throwable {
		dLib.closeConnection();
		System.out.println("====Disconnected====");
		
	}

}
