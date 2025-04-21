package practice.PostRequestTypes;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class PostReq_Via_Json {
	@Test
	public void postDataFromServer() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("createdBy", "Talha Anjum");
		jsonObj.put("projectName", "Helicopter");
		jsonObj.put("status", "Created");
		jsonObj.put("teamSize", 0);

		given().contentType(ContentType.JSON).body(jsonObj.toJSONString()).post("http://49.249.28.218:8091/addProject")
				.then().assertThat().statusCode(201).log().all();

	}

}
