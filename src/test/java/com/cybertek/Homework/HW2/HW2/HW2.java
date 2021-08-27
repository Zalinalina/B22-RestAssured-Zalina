package com.cybertek.Homework.HW2.HW2;

public class HW2 {

    //Given accept type is application/json
    //And query param q is {"location_id":1700}
    //When I send a GET request to /departments endpoint
    //Then status code must be 200
    //And each location_id must be 1700
    //And number of departments is 21 in total
    //And database department names are matching with api department names
    //
    //To get this homework done
    //1.you need to make sure you have odjbc(oracle dependency for jdbc)
    //2.do needed configuration to connect hr database both base class and dbutils(if needed)
    //3.write your query to get departments location id is 1700j
    //5.get your result from api do needed assertions (try with hamcrest)
    //6.store your api result into java collection (as() method not mandatory)
    //7.compare department names against to each other (list to list equality)

    public static class HW3 {
        // Re do following homework with pojo. Use Jackson annotations if needed.
        //   send a get request to student id 23401 as a path parameter and accept header application/json
        //   verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //   verify Date header exists
        //   assert that
        //                firstName Vera
        //                batch 14
        //                section 12
        //                emailAddress aaa@gmail.com
        //                companyName Cybertek
        //                state IL
        //                zipCode 60606
    }
}
