package com.cybertek.utilities;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;
public class SpartanAuthTestBase {
    @BeforeAll
    public static void init() {
        baseURI = "http://54.92.248.102:7000";
    }
}