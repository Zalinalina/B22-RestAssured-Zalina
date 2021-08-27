package com.cybertek.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("job_id")
    private String jobId;
    @JsonProperty("salary")
    private int salary;

}
