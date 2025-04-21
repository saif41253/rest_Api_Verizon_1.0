package practice.PostRequestTypes;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class PostReq_Via_JsonFile {
	@Test
	public void postDataFromServer() {
//		JSONObject jsonObj = new JSONObject();
//		jsonObj.put("createdBy", "Talha Anjum");
//		jsonObj.put("projectName", "Helicopter");
//		jsonObj.put("status", "Created");
//		jsonObj.put("teamSize", 0);
		
		File file = new File("./project.json");

		given().contentType(ContentType.JSON).body(file).post("http://49.249.28.218:8091/addProject")
				.then().assertThat().statusCode(201).log().all();

	}

}
