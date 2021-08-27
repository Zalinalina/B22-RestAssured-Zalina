package com.cybertek.day7;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


public class PutAndPatchRequestDemo extends SpartanTestBase {
    @DisplayName("PUT request to one spartan for update with Map")
    @Test
    public void PUTRequest() {
        //just like post request we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name", "BruceWayne");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 8877441111L);


        given().and().contentType(ContentType.JSON)
                .body(putRequestMap).log().body()
                .and().pathParam("id", 250)
                .when().put("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }

    @DisplayName("PUT request to one spartan for update with Map")
    @Test
    public void PATCHRequest() {
        //just like post request we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone", 8877441111L);
        putRequestMap.put("name", "Peter");


        given().and().contentType(ContentType.JSON)
                .body(putRequestMap).log().body()
                .and().pathParam("id", 250)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }

    @DisplayName("Delete one spartan")
    @Test
    public void DELETERequest() {
        //just like post request we have different options to send body, we will go with map
        int idToDelete = 241;


        given().pathParam("id", idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);
    }
}