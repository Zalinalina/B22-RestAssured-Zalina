package com.cybertek.day5;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class HamcrestMatchersTest {

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1() {

        given().
                accept(ContentType.JSON)
                    .and().pathParam("id",15)
                .when()
                    .get("http://54.92.248.102:8000/api/spartans/{id}")
                    .then()
                    .statusCode(200)
                     .and()
                    .contentType("application/json")
                     .and()
                    .body("id",equalTo(15),
                            "name",is("Meta"),
                            "gender", is("Female"),
                            "phone",is(1938695106))
        .log().all();


    }
    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData() {

        given()
                .accept(ContentType.JSON)
                .pathParam("id", 10423)
                .when()
                .get("http://api.cybertektraining.com/teacher/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Length", is("236"))
                .and()
                .header("Date", notNullValue())
                .and().assertThat()
                .body("teachers[0].firstName", is("Alexander"))
                .body("teachers[0].lastName", is("Syrup"))
                .body("teachers[0].gender", is("male"));

    }
    @DisplayName("Get request to teacher/all and chaining")
    @Test
    public void teacherTest1(){

        //verify Alexander, Darleen, Sean, inside the all teachers
                 given()
                        .accept(ContentType.JSON)
                .when()
                        .get("http://api.cybertektraining.com/teacher/all")
                .then()
                        .statusCode(200)
                        .and()
                        .body("teachers.firstName", hasItems("Alexander","Darleen","Sean"));


















    }
}