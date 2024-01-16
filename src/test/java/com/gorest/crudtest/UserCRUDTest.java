package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    static String name = "Prime" + TestUtils.getRandomValue();
    static String email =  TestUtils.getRandomValue() + "PrimeTesting@gmail.com";
    static String updatedEmail = TestUtils.getRandomValue() + "PrimeTesting@gmail.com";
    static String gender = "Male";
    static String status = "Active";
    static int userId;
    static String token = "872638e67e43125f2cd558c795afc35b3159022170177f681f69c94e6c43ca35";


    // 1. verifyUserCreatedSuccessfully()
    @Test
    public void test001() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .body(userPojo)
                .when()
                .post("/users").then().extract().response();
        response.then().statusCode(201);
        userId = response.jsonPath().get("id");
        System.out.println("Id is: "+ userId);
    }

    // 2.1 verifyUserUpdateSuccessfully()
    @Test
    public void test002() {
        Response response = given()
                .header("Authorization","Bearer" + token)
                .when()
                .get("/users" + "/" + userId);
        response.then().log().all().statusCode(200);
    }

    // 2.2 verifyUserUpdateSuccessfully()
    @Test
    public void test003() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(updatedEmail);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .header("Authorization","Bearer" + token)
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .patch("/users" + "/" + userId);
        response.then().log().all().statusCode(200);
    }

    // 3. VerifyUserDeleteSuccessfully()
    @Test
    public void test004() {
       Response response = given()
                .header("Authorization","Bearer" + token)
                .when()
                .delete("/users" + "/" + userId);
        response.then().log().all().statusCode(204);
        response.prettyPrint();

    }
}
