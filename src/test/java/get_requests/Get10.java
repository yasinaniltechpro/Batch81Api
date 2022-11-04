package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestBaseUrl {
     /*
        Given
            https://gorest.co.in/public/v1/users/2986
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            /*
            {
        "meta": null,
        "data": {
            "id": 2986,
            "name": "Navin Talwar",
            "email": "navin_talwar@mclaughlin.name",
            "gender": "male",
            "status": "inactive"
              }
         }
     */

    @Test
    public void get10(){
        spec.pathParams("first","users","second",2986);

        GoRestTestData obj = new GoRestTestData();
        Map<String,String> dataKeyMap = obj.dataKeyMap("Navin Talwar","navin_talwar@mclaughlin.name","male","inactive");
        Map<String,Object> expectedData = obj.expectedDataMethod(null, dataKeyMap);
        System.out.println(expectedData);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(expectedData.get("meta"),actualData.get("meta"));
        assertEquals(dataKeyMap.get("name"), ((Map)actualData.get("data")).get("name"));
        assertEquals(dataKeyMap.get("email"), ((Map)actualData.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"), ((Map)actualData.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"), ((Map)actualData.get("data")).get("status"));
        assertEquals(200,response.statusCode());

    }
}