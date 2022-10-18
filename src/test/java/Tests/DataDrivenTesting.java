package Tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
/**
 * How do you run a test multiple times with different sets of data in TestNG
 * In TestNG we have @DataProvider
 * It helps to write data driven tests
 * Same tests can be run multiple times with different sets of data
 * The annotated method can be used to return object containing Test Data
 * This Test DAta can be used in Actual Testing*/


public class DataDrivenTesting {

    @DataProvider(name = "PostingData")
    public Object[][] dataForPost() {
        //Method 1: Matrix wise : Rows and Columns
//        Object[][] data = new Object[2][3];
//
//        //2 rows of data and 3 cols of info 2*3 = 6
//
//        data[0][0] = "Sachin";
//        data[0][1] = "Tendulkar";
//        data[0][2] = 1;
//        data[1][0] = "Rohit";
//        data[1][1] = "Sharma";
//        data[1][2] = 2;
//
//        return data;
        //Method 2: JSON Style

        return new Object[][]{
                {"Virat", "Kohli", 1},
                {"KL", "Rahul", 2}
        };
    }

    @Test(dataProvider = "PostingData")
    public void post(String firstName, String lastName, int subjectId) {
        baseURI = "http://localhost:3000";
        JSONObject request = new JSONObject();
        request.put("firstName", firstName);
        request.put("lastName", lastName);
        request.put("subjectId", subjectId);

        given().contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("/users").
                then().
                statusCode(201).
                log().
                all();
    }

    @DataProvider(name = "DataForDeletion")
    private Object dataForDelete() {
        return new Object[] {
                7,8
        };
    }

    @Test(dataProvider = "DataForDeletion")
    public void testDelete(int userId) {
        baseURI = "http://localhost:3000";

        when().delete("/users/" +userId).
                then().statusCode(200).log().all();
        //status code is 200 or 204 when the data is successfully deleted
    }
}