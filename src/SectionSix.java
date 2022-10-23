import org.testng.Assert;
import org.testng.annotations.Test;

import files.DummyResponse;
import files.ReUsableMethods;
import io.restassured.path.json.JsonPath;


//Handling nested JSON 
//Handling nested array to fetch data on conditional basis 
//Using TestNG to run the tests instead of standard java execution 

public class SectionSix {

	@Test
	public void getCoursesSum() {
		//get response from dummy
				JsonPath js=ReUsableMethods.stringToJson(DummyResponse.complexCoursesResponse());
				
				int count=js.getInt("courses.size()");
				int Sum=0;
				
				for (int i=0; i<count; i++) {
					int price=js.getInt("courses["+i+"].price");
					int copies=js.getInt("courses["+i+"].copies");
					String name=js.getString("courses["+i+"].title");
					int cost=copies*price;
					System.out.println("Name: "+name+" : : Copies: "+copies+" : : Price: "+price+" : : Cost: "+cost);
					Sum=Sum+cost;
				}
				System.out.println("Total cost of courses: "+Sum);
				Assert.assertEquals(Sum, js.getInt("dashboard.purchaseAmount"));
	}

	
}
