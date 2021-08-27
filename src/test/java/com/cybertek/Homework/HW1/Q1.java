package com.cybertek.Homework.HW1;

import com.cybertek.utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;


import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.filters;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Q1 extends HRTestBase {


    //ORDS API:
    //Q1:
    //- Given accept type is Json
    //- Path param value- US
    //- When users sends request to /countries
    //- Then status code is 200
    //- And Content - Type is Json
    //- And country_id is US
    //- And Country_name is United States of America
    //- And Region_id is 2
    @DisplayName("GET request to Countries")
    @Test
    public void test1() {


        Response response = given().accept(ContentType.JSON)
                .and().param("country_id\":US}")
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());


        JsonPath jsonPath = response.jsonPath();
        String country_id = jsonPath.getString("items[22].country_id");
        System.out.println("country_id = " + country_id);
        String country_name = jsonPath.getString("items[22].country_name");
        int region_id = jsonPath.getInt("items[22].region_id");
        System.out.println("country_name = " + country_name);
        System.out.println("region_id = " + region_id);


        assertEquals("US", country_id);
        assertEquals("United States of America", country_name);
        assertEquals(2, region_id);

    }

    //Q2:
    //- Given accept type is Json
    //- Query param value - q={"department_id":80}
    //- When users sends request to /employees
    //- Then status code is 200
    //- And Content - Type is Json
    //- And all job_ids start with 'SA'
    //- And all department_ids are 80
    //- Count is 25

    @DisplayName("GET request to Countries and verify")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when()
                .get("/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        JsonPath jsonPath = response.jsonPath();

        List<String> jobIds = jsonPath.getList("items.job_id");
        System.out.println(jobIds);
        for (String jobId : jobIds) {
            assertTrue(jobId.startsWith("SA"));
        }
        List<Integer> department_ids = jsonPath.getList("items.department_id");
        System.out.println(department_ids);
        for (int departmentID : department_ids) {
            assertEquals(departmentID, 80);
        }
        assertEquals(department_ids.size(), 25);
        System.out.println("department_ids.size() = " + department_ids.size());
    }

    //Q3:
    //- Given accept type is Json
    //-Query param value q= region_id 3
    //- When users sends request to /countries
    //- Then status code is 200
    //- And all regions_id is 3
    //- And count is 6
    //- And hasMore is false
    //- And Country_name are;
    //Australia,China,India,Japan,Malaysia,Singapore
    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        JsonPath jsonPath = response.jsonPath();
        List<Integer> region_ids = jsonPath.getList("items.region_id");
        System.out.println(region_ids);
        for (int regionId : region_ids) {
            assertEquals(regionId, 3);

        }
        assertEquals(region_ids.size(),6);
        System.out.println("region_ids.size() = " + region_ids.size());

        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));


        List<String> Country_name = jsonPath.getList("items.country_name");


        List<String> expectedNamesOfCountries = new  ArrayList<String>(Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore"));
        assertEquals(Country_name,expectedNamesOfCountries);
        System.out.println("Country_name = " + Country_name);
        System.out.println("expectedNamesOfCountries = " + expectedNamesOfCountries);

//        for (String countryName : Country_name) {
//            System.out.println(countryName);
  //      }
           // assertEquals(countryName("Australia","China","India","Japan","Malaysia","Singapore"));
    }
}