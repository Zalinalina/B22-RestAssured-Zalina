package com.cybertek.Homework.NewSpartanBorn;

import com.cybertek.pojo.Spartan;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SpartanUtils{

        public static Map<String, Object> FakerMap () {
            Faker faker = new Faker();

            Spartan spartan = new Spartan();

            spartan.setName(faker.name().name());
            spartan.setGender(faker.demographic().sex());
            spartan.setPhone(Long.valueOf(faker.number().digits(10)));

            Map<String, Object> createSpartan = new LinkedHashMap<>();
            createSpartan.put("name", spartan.getName());
            createSpartan.put("gender", spartan.getGender());
            createSpartan.put("phone", spartan.getPhone());

            return createSpartan;


        }

    }