package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        response = given().queryParam("page", 1).queryParam("per_page", 20).when().get("/posts").then().statusCode(200);
    }

    @Test
    public void test001() {
        List<String> allTitles = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all the Titles are : " + allTitles);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test002() {
        List<Integer> totalRecords = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of records are : " + totalRecords.size());
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test003() {
        String record = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Body of the 15th record is : " + record);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test004() {
        List<Integer> allRecordUserId = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("User Id of all the records are : " + allRecordUserId);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test005() {
        List<String> titleOfAllRecords = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Title of all the records are : " + titleOfAllRecords);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test006() {
        List<String> titleOfUser = response.extract().path("findAll{it.user_id = 5914200}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all records whose user Id is = 5914200 : " + titleOfUser);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test007() {
        List<String> body = response.extract().path("findAll{it.id = 93995}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of all records whose id = 93995 : " + body);
        System.out.println("------------------End of Test---------------------------");
    }
}
