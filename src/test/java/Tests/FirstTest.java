package Tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest {
    @Test
    public void test_1() {
        //create object
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        System.out.println("Status Code: " + response.getStatusCode());

        //to see time
        System.out.println(response.getTime());

        //status line
        System.out.println(response.getStatusLine());

        //response
        System.out.println(response.getBody().asString());

        //to know the content ie in which lang
        System.out.println(response.getHeader("content-type"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
