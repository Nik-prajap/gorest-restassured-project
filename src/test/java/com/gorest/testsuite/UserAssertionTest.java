package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    @Test
    public void test001() {
        response.body("size()", equalTo(20));
    }

    @Test
    public void test002() {
        response.body("find{it.id == 5914139}.name", equalTo("Bilwa Embranthiri"));
    }

    @Test
    public void test003() {
        response.body("name", hasItem("Aanjaneya Iyer"));
    }

    @Test
    public void test004() {
        response.body("name", hasItems("Dinesh Mehrotra", "Aanjaneya Iyer", "Chandini Prajapat"));
    }

    @Test
    public void test005() {
        response.body("find{it.id == 5914141}.email", equalTo("pilla_dandapaani_amb@goyette.test"));
    }

    @Test
    public void test006() {
        response.body("find{it.status == 'active'}.name", equalTo("Yoginder Dhawan Esq."));
    }

    @Test
    public void test007() {
        response.body("find{it.name == 'Dinesh Mehrotra'}.gender", equalTo("male"));
    }

}
