package com.cybertek.Homework;

import com.cybertek.Homework.NewSpartanBorn.SpartanUtils;
import com.cybertek.pojo.Spartan;
import com.cybertek.utilities.SpartanAuthTestBase;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.ws.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HW4 extends SpartanAuthTestBase {


    /*
        As a homework,write a detealied test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able take all CRUD
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? 401 for all

     */


    @DisplayName("admin")
    @Test
    public void test1() {
        given().and().auth().basic("admin", "admin").when()
                .get("/api/spartans").then().assertThat().statusCode(200);

        int idFromPost = given().auth().basic("admin", "admin").accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(SpartanUtils.FakerMap()).log().all()
                .when()
                .post("/api/spartans")
                .then().assertThat()
                .statusCode(201)
                .extract().jsonPath().getInt("data.id");
        System.out.println("idFromPost = " + idFromPost);
        given()
                .auth().basic("admin", "admin")
                .and().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .log().all();
        //just like post request we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone", 8811111834L);
        putRequestMap.put("name", "AnnaPetrova");
        given().auth().basic("admin", "admin").contentType(ContentType.JSON) //hey api I am sending JSON body
                .body(putRequestMap).log().body()
                .and().pathParam("id", idFromPost)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);
        given()
                .auth().basic("admin", "admin")
                .and().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when()
                .delete("api/spartans/{id}")
                .then()
                .statusCode(204)
                .log().body();
        given()
                .auth().basic("admin", "admin")
                .and().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when()
                .delete("api/spartans/{id}")
                .then()
                .statusCode(404)
                .log().body();
    }

    @DisplayName("editor")
    @Test
    public void test2() {
        given().and().auth().basic("editor", "editor").when()
                .get("/api/spartans").then().assertThat().statusCode(200);

        int idFromEditor = given().auth().basic("editor", "editor").accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(SpartanUtils.FakerMap()).log().all()
                .when()
                .post("/api/spartans")
                .then().assertThat()
                .statusCode(201)
                .extract().jsonPath().getInt("data.id");
        System.out.println("idFromEditor = " + idFromEditor);
        given()
                .auth().basic("editor", "editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id", idFromEditor)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .log().all();
        //just like post request we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone", 8811111834L);
        putRequestMap.put("name", "AnnaPetrova");
        given().auth().basic("editor", "editor").contentType(ContentType.JSON) //hey api I am sending JSON body
                .body(putRequestMap).log().body()
                .and().pathParam("id", idFromEditor)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);
        given()
                .auth().basic("editor", "editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id",idFromEditor)
                .when()
                .delete("api/spartans/{id}")
                .then()
                .statusCode(403)
                .log().body();
        given()
                .auth().basic("editor", "editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id",idFromEditor)
                .when()
                .delete("api/spartans/{id}")
                .then()
                .statusCode(403)
                .log().body();
    }
    @DisplayName("user")
    @Test
    public void test3() {
        given().and().auth().basic("user", "user").when()
                .get("/api/spartans").then().assertThat().statusCode(200);

        given().auth().basic("user", "user").accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(SpartanUtils.FakerMap()).log().all()
                .when()
                .post("/api/spartans")
                .then().assertThat()
                .statusCode(403)
                .extract().jsonPath();

        given()
                .auth().basic("user", "user")
                .and().accept(ContentType.JSON)
                .and().pathParam("id",145)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .log().all();
        //just like post request we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone", 8811111834L);
        putRequestMap.put("name", "AnnaPetrova");
        given().auth().basic("user", "user").contentType(ContentType.JSON) //hey api I am sending JSON body
                .body(putRequestMap).log().body()
                .and().pathParam("id", 145)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(403);
        given()
                .auth().basic("user", "user")
                .and().accept(ContentType.JSON)
                .and().pathParam("id",145)
                .when()
                .delete("api/spartans/{id}")
                .then()
                .statusCode(403)
                .log().body();
        given()
                .auth().basic("user", "user")
                .and().accept(ContentType.JSON)
                .and().pathParam("id",145)
                .when()
                .delete("api/spartans/{id}")
                .then()
                .statusCode(403)
                .log().body();
    }

}

