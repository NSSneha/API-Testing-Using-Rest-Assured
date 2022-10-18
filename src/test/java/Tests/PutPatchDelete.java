package Tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PutPatchDelete {
    @Test
    public void testPut() {
        JSONObject request = new JSONObject();
        request.put("Name", "Sneha");
        request.put("Job", "Seeker");

        System.out.println(request.toJSONString());
        baseURI = "https://reqres.in/api";

        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                put("/users/2").
                then().
                statusCode(200).
                log().all();
    }

    @Test
    public void testPatch() {
        JSONObject request = new JSONObject();
        request.put("Name", "Sneha");
        request.put("Job", "Seeker, still searching for job"); //any changes for the available data

        System.out.println(request.toJSONString());
        baseURI = "https://reqres.in/api";

        given().
                header("Content-type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                patch("/api/users/2").
                then().
                statusCode(200).
                log().all();
    }
    @Test
    public void testDelete() {
        baseURI = "https://reqres.in/api";

        when().
                delete("/api/users/2").
                then().
                statusCode(204).
                log().all();
    }
}
