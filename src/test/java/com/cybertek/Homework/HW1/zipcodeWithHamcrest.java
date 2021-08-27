package com.cybertek.Homework.HW1;

import com.cybertek.utilities.ZipTestDB;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class zipcodeWithHamcrest extends ZipTestDB {
    //Documentation: https://www.zippopotam.us/ (Links to an external site.)
    //
    //BASEURL: api.zippopotam.us (Links to an external site.)
    //
    //
    //
    //Scenarios:
    //
    //Given Accept application/json
    //And path zipcode is 22031
    //When I send a GET request to /us endpoint
    //Then status code must be 200
    //And content type must be application/json
    //And Server header is cloudflare
    //And Report-To header exists
    //And body should contains following information
    //    post code is 22031
    //    country  is United States
    //    country abbreviation is US
    //    place name is Fairfax
    //    state is Virginia
    //    latitude is 38.8604
    //
    //===========================
    //Given Accept application/json
    //And path zipcode is 50000
    //When I send a GET request to /us endpoint
    //Then status code must be 404
    //And content type must be application/json
    //
    //============================
    //
    //Given Accept application/json
    //And path state is va
    //And path city is farifax
    //When I send a GET request to /us endpoint
    //Then status code must be 200
    //And content type must be application/json
    //And payload should contains following information
    //    country abbreviation is US
    //    country  United States
    //    place name  Fairfax
    //    each places must contains fairfax as a value
    //    each post code must start with 22


    @Test
    public void test1() {
      given().accept(ContentType.JSON)
                .and().pathParam("zipcode", 22031)
                .when().get("/us/{zipcode}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().header("Server", is("cloudflare"))
                .and().headers("Report-To", notNullValue())
                .and().assertThat()
                .body("'post code'", is("22031"))
                .body("country", is("United States"))
                .body("'country abbreviation'", equalTo("US"))
                .body("places[0].'place name'", equalTo("Fairfax"))
                .body("places[0].state", equalTo("Virginia"))
                .body("places[0].latitude", equalTo("38.8604"));



//                .when().get("https://www.zippopotam.us/us/22031");
//        System.out.println(response.path("'post code'").toString());



    }

    @Test
    public void test2() {
        given().accept(ContentType.JSON)
                .and().pathParam("zipcode", 50000)
                .when().get("/us/{zipcode}")
                .then().statusCode(404)
                .contentType("application/json");



    }

    @Test
    public void test3() {
      JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParam("state", "va",
                        "city", "fairfax")
                .when().get("/us/va/fairfax")
              .then().statusCode(200)
              .and().contentType("application/json")
              .and().assertThat()
              .body("'country abbreviation'", equalTo("US"))
              .body("country", is("United States"))
              .body("'place name'", is("Fairfax"))
              .body("places.'place name'",everyItem(containsString("Fairfax")))
             .body("places.'post code'",everyItem(startsWith("22")))
              .extract().response().jsonPath();

jsonPath.prettyPrint();
        }
    }


