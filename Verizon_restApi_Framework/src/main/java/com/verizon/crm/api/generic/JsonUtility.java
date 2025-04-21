package com.verizon.crm.api.generic;

import java.util.List;

import com.jayway.jsonpath.JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class JsonUtility  {
	/*
	 * get the data from based on json complex xpath
	 * @param resp
	 * @param jsonXpath
	 * @return
	*/
	
public String getDataFromJsonFile(Response resp, String jsonXpath) throws Throwable{
	List<Object> list = JsonPath.read(resp.asString(), jsonXpath);
	return list.get(0).toString();
}
/*
 * get the data from based on xml complex xpath
 * @param resp
 * @param xmlXpath
 * @return
*/
public String getDataOnXpathPath(Response resp, String xmlPath) {
	return resp.xmlPath().get(xmlPath);
}
/*
 * verify the data in jsonbody based on jsonpath
 * @param resp
 * @param jsonXpath
 * @param expectedData
 * @return
*/
public boolean verifyDataOnJsonPath(Response resp , String jsonXpath, String expectedData) {
	List <String> list = JsonPath.read(resp.asString(), jsonXpath); 
	boolean flag = false;
	for(String str : list) {
		if(str.equals(expectedData)) {
			System.out.println(expectedData+" is available == Pass");
			flag = true;
		}
	}
	if(flag==false) {
		System.out.println(expectedData+" is not available == Pass");
	}
	return flag;
}
public String getAccessToken() {
	Response resp = given()
			.formParam("client_id", "ninza-client")
			.formParam("client_secret", "gPQBf1Yxew5OMccMhzos1GefIyiSnXzM")
			.formParam("grant_type", "client_credentials")
			.when().post("http://49.249.28.218:8091/auth/realms/ninza/protocol/openid-connect/token");
	resp.then()
	.log().all();
	String token = resp.jsonPath().get("access_token");
			
	return token;
	
}

}
