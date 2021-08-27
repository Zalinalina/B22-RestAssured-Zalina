package com.cybertek.OH;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TaskHW {
    String url ="https://www.getpostman.com/collections/5d9f72679607a60f23af";

    @Test
    public void test1() {
        Gson gson = new Gson();

        Response response = RestAssured.get(url);
        //Assert the status code

        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8",response.contentType());

        System.out.println("response.body().path(\"info.name\") = " + response.body().path("info.name"));
        assertEquals("Ciceksepeti API Test Project",response.body().path("info.name"));









    }
}
