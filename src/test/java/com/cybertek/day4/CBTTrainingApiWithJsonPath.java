package com.cybertek.day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.http.ContentEncoding;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CBTTrainingApiWithJsonPath {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://api.cybertektraining.com";

    }

    @DisplayName("GET Request to individual student")
    @Test
    public void test1(){
        //send a get request to student id 23401 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606

                using JsonPath
             */


        //send a get request and save response inside the Response object
     //   Response response = RestAssured.get(baseURI + "/api/students");

//        //print response status code
//        System.out.println(response.statusCode());
//
//        //print response body
//        response.prettyPrint();

      Response response =RestAssured.given().accept(ContentType.JSON)
              .and().queryParam("q","\"studentId\": 23401")
              .when()
              .get("/students");


response.prettyPrint();



        assertEquals(200,response.statusCode());
       assertEquals(response.contentType(),"application/json;charset=UTF-8");

        System.out.println("response.contentType() = " + response.contentType());

        assertTrue(response.body().asString().contains("Date"));
       // response.body().prettyPrint();

       assertTrue(response.headers().hasHeaderWithName("Date"));
        assertEquals("gzip",response.header("Content-Encoding"));
        System.out.println(response.header("Content-Encoding"));
        System.out.println(response.header("date"));


    }
}