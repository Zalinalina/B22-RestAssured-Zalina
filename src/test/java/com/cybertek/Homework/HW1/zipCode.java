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
public class zipCode extends ZipTestDB {
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
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zipcode", 22031)
                .when().get("/us/{zipcode}");

//                .when().get("https://www.zippopotam.us/us/22031");
//        System.out.println(response.path("'post code'").toString());
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        assertEquals("cloudflare", response.header("Server"));
        assertTrue(response.headers().hasHeaderWithName("Report-To"));

        JsonPath jsonPath = response.jsonPath();
        assertEquals(22031, jsonPath.getInt("'post code'"));
        assertEquals("United States", jsonPath.getString("country"));
        assertEquals("US", jsonPath.getString("'country abbreviation'"));
        assertEquals("Fairfax", jsonPath.getString("places[0].'place name'"));
        assertEquals("Fairfax", jsonPath.getString("places[0].'place name'"));
        assertEquals("Virginia", jsonPath.getString("places[0].state"));
        assertEquals(38.8604, jsonPath.getDouble("places[0].latitude"));

        response.prettyPrint();


    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zipcode", 50000)
                .when().get("/us/{zipcode}");
        assertEquals(404, response.statusCode());
        System.out.println(response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));


    }

    @Test
    public void test3() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("state", "va",
                        "city", "fairfax")
                .when().get("/us/va/fairfax");
        // response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        JsonPath jsonPath = response.jsonPath();
        assertEquals("US", jsonPath.getString("'country abbreviation'"));
        assertEquals("United States", jsonPath.getString("country"));
        assertEquals("Fairfax", jsonPath.getString("'place name'"));

        jsonPath.prettyPrint();
        List<String> fairfax = jsonPath.getList("places.'place name'");

        for (String eachFairfax : fairfax) {
            System.out.println("eachFairfax = " + eachFairfax);
            assertTrue(eachFairfax.contains("Fairfax"));

        }
        List<String> postCode = jsonPath.getList("places.'post code'");

        for (String eachPostCode : postCode) {
            System.out.println("eachPostCode = " + eachPostCode);
            assertTrue(eachPostCode.startsWith("22"));

        }
    }
}