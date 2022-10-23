import files.DummyResponse;
import files.ReUsableMethods;
import io.restassured.path.json.JsonPath;


//Get a sample response from Dummy class 
//Get nested array data using conditional statements and loop

public class SectionSixPartOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js=ReUsableMethods.stringToJson(DummyResponse.complexCoursesResponse());
		
		int count=js.getInt("courses.size()");
		
		for (int i=0; i<count; i++) {
			if (js.getString("courses["+i+"].title").equalsIgnoreCase("RPA")) {
				System.out.println(js.getString("courses["+i+"].copies"));
				break;
			}
		}
		
	}

}
