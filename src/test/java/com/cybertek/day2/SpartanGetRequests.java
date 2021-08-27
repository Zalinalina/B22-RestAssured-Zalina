package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {
    String baseUrl = "http://54.92.248.102:8000";

     //    Given Accept type application/json
    //    When user send GET request to api/spartans end point
    //then status code must be 200
    //    Then response Content Type must be application/json
    //    And response body should include spartan result

    @Test
    public void test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");

        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());

        response.prettyPrint();

        //how to do API testing then?
        Assertions.assertEquals(response.statusCode(), 200);

        Assertions.assertEquals(response.contentType(), "application/json");
    }

         /*
        Given accept header is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */
        @DisplayName("Get ")
        @Test
        public void test2(){
           Response response = RestAssured.given().accept(ContentType.JSON).
                    when().get(baseUrl + "/api/spartans/5");

            Assertions.assertEquals(response.statusCode(),200);
            Assertions.assertEquals(response.contentType(), "application/json");
            Assertions.assertTrue(response.body().asString().contains("Blythe"));

        }
 /*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */
 @DisplayName("Get request to /api/hello")
 @Test
 public void test3(){

     Response response = RestAssured.when().get(baseUrl + "/api/hello");
     Assertions.assertEquals(200, response.statusCode());
     Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());
     Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
     System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
     System.out.println(response.header("date"));
     Assertions.assertEquals("17",response.header("Content-Length"));
     Assertions.assertEquals("Hello from Sparta",response.body().asString());
 }

    }


