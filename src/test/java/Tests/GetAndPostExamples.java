package Tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GetAndPostExamples { //GET Request
    @Test
    public void testGet() {
        baseURI = "https://reqres.in/api";
        given().
                get("/users?page=2").
                then().
                statusCode(200).
                //checking if the first name is George
                body("data[4].first_name", equalTo("George")).
                //checking if first_name parameters has names George and Rachel
                body("data.first_name", hasItems("George", "Rachel"));
    }

    @Test
    public void testPut_1() {
        //Basic step by step using MAP (Hash map belongs to map) {Not good practice}

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name","Sneha");
        map.put("job","seeker");

        System.out.println(map);
    //hash map does not follow the order, linked hashmap must be used, it follows insertion order
    //Map<String, Object> map = new LinkedHashMap<String, Object>()
    }
    @Test
    public void testPut_2() {
        //we have to use dependency json-simple which is the efficient way
        //create object of jsonObject
        JSONObject request = new JSONObject();
        request.put("Name", "Sneha");
        request.put("Job", "Seeker");

        //to print toJSONString()
        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in/api";
        given().
                /**
                 * Normally Rest Request also has some headers
                 * We can tell the server that what kind of data we are sending
                 * and what kind we are accepting
                 * 'content-type'*/
                header("Content-Type", "application/json").
                  contentType(ContentType.JSON).
                //Explicitly telling the server that content-type is json
                  accept(ContentType.JSON).
                  body(request.toJSONString()).
                  when().
                  post("/users").
                  then().
                  statusCode(201). //status code for successful creation is 201
                  log().all();
    }
}
