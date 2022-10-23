import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;
import files.ReUsableMethods;

public class SectionFive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//create place 
		
		//given
		String StringResponse=
		given()
		.queryParam("key", "qaclick123")
		.headers("Content-Type", "application/json").body(Payload.addPlacePayload())
		
		//when
		.when().post("/maps/api/place/api/json/")
		
		//then
		.then().assertThat().statusCode(200)
		
		//using hamcrest matchers within the body assertion
		.body("scope", equalTo("APP"))
		
		.extract().response().asString();
		
		
		//ReUsableMethod
		JsonPath JsonResponse=ReUsableMethods.stringToJson(StringResponse);
		String place_id=JsonResponse.getString("place_id");
		String res_status=JsonResponse.getString("status");
		
		
		/*
		 * JsonPath jsResponse=new JsonPath(response); String
		 * String place_id=jsResponse.getString("place_id");
		 * 
		 * String res_status=jsResponse.getString("status");
		 */
		
		
		
		//TestNG assertions
		Assert.assertEquals(res_status, "OK");
		
		
		
		//delete place 
		given()
		.queryParam("key", "qaclick123")
		.headers("Content-Type", "application/json")
		.body(Payload.deletePlacePayload(place_id))
		.when().delete("/maps/api/place/delete/json")
		.then().log().all()
		.assertThat().statusCode(200);
	}

}
