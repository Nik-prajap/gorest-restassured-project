package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PostsAssertionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    @Test
    public void test001() {
        response.body("size()", equalTo(20));
    }

    @Test
    public void test002() {
        response.body("find{it.id ==93997 }.title", equalTo("Demitto conqueror atavus argumentum corrupti cohaero libero."));
    }

    @Test
    public void test003() {
        response.body("[4].user_id", equalTo(5914249));
    }


    @Test
    public void test004() {
        response.body("id", hasItems(93999, 93997, 93996));
    }

    @Test
    public void test005() {
        response.body("find{it.user_id == 5914254}.body", equalTo("Depulso auris vereor. " +
                "Acceptus suffragium repudiandae. Cotidie cubicularis deprecator. Virtus validus aliquid. " +
                "Adduco somnus quibusdam. Despecto nihil vinum. Claudeo nam ullus. Sursum tutamen rerum. " +
                "Cenaculum tabula adultus. Charisma thema super. Vobis cavus clibanus. Quo quod avaritia. " +
                "Condico apparatus nulla. Textilis depopulo acidus."));
    }
}
