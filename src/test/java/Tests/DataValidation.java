package Tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DataValidation {
    /**
     * Static imports: inorder to use rest assured effectively it is recommended
     * to statically import methods:
     * imports we have to make are
     * 1. io.restassured.RestAssured.*
     * 2. org.hamcrest.Matchers.**/
    //Base URI- base path thats added to the base URI by Rest-Assured when we are making any request

    @Test
    public void test_data() {
        baseURI = "https://reqres.in/api";
        given().
                get("/users?page=2").
                then().statusCode(200).
                body("data[1].id", equalTo(8)).
                log().
                all();
    }
}

//for JSON validation in the response we have to use JSON Path Finder
//for finding the proper path of the JSON Response Body
