package com.cybertek.day6;

import com.cybertek.pojo.Employee;
import com.cybertek.pojo.Regions;
import com.cybertek.pojo.Region;
import com.cybertek.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class ORDSPojoGetRequestTest extends HRTestBase {
    @Test
    public void regionTest() {

        JsonPath jsonPath = get("/regions")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);


        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());
    }

    @Test
    public void employeeGet() {
        Employee employee1 = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);

        System.out.println(employee1);
    }
@DisplayName("Get request to region only some fields test")
    @Test
    public void regionsPojoTest() {
        Regions regions = get("/regions").then().statusCode(200)
                .extract().as(Regions.class);
        assertThat(regions.getCount(),is(4));

    List<String> regionNames = new ArrayList<>();
    List<Integer> regionIds = new ArrayList<>();

    List<Region> items = regions.getItems();

    for (Region region : items) {
        regionIds.add(region.getRId());
        regionNames.add(region.getRegion_name());
    }

    List<Integer>expectedRegionIds = Arrays.asList(1,2,3,4);
    List<String>expectedRegionNames = Arrays.asList("Europe","Americas","Asia","Middle East and Africa");

    assertThat(regionIds,is(expectedRegionIds));
    assertThat(regionNames,is(equalTo(expectedRegionNames)));

    System.out.println("regionIds = " + regionIds);
    System.out.println("regionNames = " + regionNames);

}
}// List<String> allCountryIds =  jsonPath.getList("items.country_id");
      //  System.out.println(allCountryIds);

       //         List<String> allCountryIdsTwo =  jsonPath.getList("items.findAll{it.region_id==2}.country_name");
       // System.out.println(allCountryIdsTwo);