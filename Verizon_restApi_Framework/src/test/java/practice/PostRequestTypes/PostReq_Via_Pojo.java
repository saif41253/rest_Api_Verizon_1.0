package practice.PostRequestTypes;

import java.util.Random;

import org.testng.annotations.Test;

import com.verizon.crm.api.pojoclass.ProjectPojoUtility;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class PostReq_Via_Pojo {
	@Test
	public void postDataFromServer() {
		Random random = new Random();
	 int randomNum = random.nextInt(5000);
	 
		ProjectPojoUtility pObj = new ProjectPojoUtility ("Nizame_"+ randomNum, "Created", "Saif", 0);

		given().contentType(ContentType.JSON).body(pObj).post("http://49.249.28.218:8091/addProject")
				.then().assertThat().statusCode(201).log().all();

	}

}
