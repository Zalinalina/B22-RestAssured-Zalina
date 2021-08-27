package com.cybertek.day8;

import com.cybertek.utilities.SpartanAuthTestBase;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class SpartanWithAuthTest extends SpartanTestBase {

    @DisplayName("POST /api/spartans as a public user expect 200")
    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .when()
        .get("/api/spartans")
                .then().statusCode(401)
                .log().all();
    }
    @DisplayName("GET /api/spartans as admin user (guest) expect 401")
    @Test
    public void testAdmin() {
        given()
                .and().accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .log().all();
    }

    @DisplayName("DELETE /spartans/{id} as editor user expect 403")
    @Test
    public void testEditorDelete() {

        given()
                .auth().basic("editor", "editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id", 30)
                .when()
                .delete("api/spartans/{id}")
                .then()
                .statusCode(403)
                .log().body();
    }
}
