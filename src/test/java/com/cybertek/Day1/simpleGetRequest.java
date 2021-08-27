package com.cybertek.Day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

public class simpleGetRequest {


    String url = "http://54.92.248.102:8000/api/spartans";

    @Test
    public void test1(){

        Response response = RestAssured.get(url);
        System.out.println(response.statusCode());

        response.prettyPrint();


    }
}
