package com.cybertek.day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test1() {

        Response response = get("/countries");
//get the second country name with JsonPath

        //to use json path we assign response to jsonPath
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);


        List<String> allCountryIds =  jsonPath.getList("items.country_id");
        System.out.println(allCountryIds);

        List<String> allCountryIdsTwo =  jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println(allCountryIdsTwo);


    }
    @DisplayName("GET request to Countries")
    @Test
    public void test2() {


        Response response = given().queryParam("limit",107)
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        List<String> employeeITProgs = jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.email");
        System.out.println(employeeITProgs);

        List<String> employeeMoreThanTanK = jsonPath.getList("items.findAll{it.salary>10000}.first_name");
        System.out.println(employeeMoreThanTanK);


        String KingFirstName = jsonPath.getString("items.max{it.salary}.first_name");
        String KingFirstNameWithPathMethod = response.path("items.max{it.salary}.first_name");
        System.out.println("KingFirstName = " + KingFirstName);
        System.out.println("KingFirstNameWithPathMethod = " + KingFirstNameWithPathMethod);

    }
}