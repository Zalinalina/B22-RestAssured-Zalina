package com.cybertek.day5;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSHamcrestTest extends HRTestBase {

    @DisplayName("Get request to Regions IT_PROG endpoint and chaining")
    @Test
    public void employeesTest1(){
        List<String>names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");
        given()
                .accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .and()
                .body("items.job_id",everyItem(equalTo("IT_PROG")))
                .body("items.first_name", containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"))
                .body("items.first_name", containsInAnyOrder("Diana","Alexander","Bruce","David","Valli"))
                .body("items.email", containsInAnyOrder("DLORENTZ","VPATABAL","DAUSTIN","BERNST","AHUNOLD"))
                .body("items.first_name",equalToObject(names));

    }
    @Test
    public void employeesTest2(){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id",everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();

        jsonPath.prettyPrint();


        //assert that we have only 5 first names

        assertThat(jsonPath.getList("items.first_name"), hasSize(5));
        assertThat(jsonPath.getList("items.first_name"), containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"));


    }
}
