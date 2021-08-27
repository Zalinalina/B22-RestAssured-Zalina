package com.cybertek.Homework.NewSpartanBorn;

import com.cybertek.pojo.Spartan;
import com.cybertek.utilities.SpartanAuthTestBase;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AddNewSpartan extends SpartanAuthTestBase {

    @Test
    public void newSpartan() {

        Spartan spartan = given().auth().basic("admin", "admin").accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(SpartanUtils.FakerMap()).log().all()
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is("A Spartan is Born!")).log().all()
                .extract().jsonPath().getObject("data", Spartan.class);

        int id = spartan.getId();

        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", id)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).log().all().extract().as(Spartan.class);


        assertThat(spartanPosted.getName(), is(spartan.getName()));
        assertThat(spartanPosted.getGender(), is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(), is(spartan.getPhone()));


        //Create one SpartanUtil class
        //create a static method that returns Map<String,Object>
        //use faker library(add as a depedency) to assign each time different information
        //for name,gender,phone number
        //then use your method for creating spartan as a map,dynamically.


    }

        @DisplayName("PUT request to one spartan for update with Map")
        @Test
        public void PUTRequest(){
            //just like post request we have different options to send body, we will go with map
            Map<String,Object> putRequestMap = new LinkedHashMap<>();
            putRequestMap.put("name","AnnaBurk");
            putRequestMap.put("gender","Female");
            putRequestMap.put("phone",8811111115L);

         given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                    .contentType(ContentType.JSON)
                    .body(putRequestMap).log().body()
                    .and().pathParam("id",118)
                    .when().put("/api/spartans/{id}")
                    .then()
                    .statusCode(204);


            //send a GET request after update, make sure updated field changed, or the new info matching
            //with requestBody that we send

        }

        @DisplayName("PATCH request to one spartan for partial update with Map")
        @Test
        public void PATCHRequest(){
            //just like post request we have different options to send body, we will go with map
            Map<String,Object> putRequestMap = new LinkedHashMap<>();
            putRequestMap.put("phone",8811111834L);
            putRequestMap.put("name","Peter");

            given().contentType(ContentType.JSON) //hey api I am sending JSON body
                    .body(putRequestMap).log().body()
                    .and().pathParam("id",434)
                    .when().patch("/api/spartans/{id}")
                    .then()
                    .statusCode(204);

            //send a GET request after update, make sure updated field changed, or the new info matching
            //with requestBody that we send




        }
//DELETE spartan
    @DisplayName("DELETE one spartan")
    @Test
    public void deleteSpartan() {
        int idToDelete = 432;


        given().pathParam("id", idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

    }

    //send a get request after you delete make sure you are getting 404
    @DisplayName("GET one deleted spartan from api and database")
    @Test
    public void testDB1(){
    Response response = given().pathParam("id", 428)
            .when().get("/api/spartans/{id}")
            .then().statusCode(404).extract().response();


    }
}
