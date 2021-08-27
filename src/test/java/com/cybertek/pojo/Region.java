package com.cybertek.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region {
//
//    private int region_id;
//    private String region_name;
//    private List<Link> links;

    //region_id
    //if your jsonkey and variable name not matching, you can map it with jsonProperty
    @JsonProperty("region_id")
    private int rId;
    @JsonProperty("region_name")
    private String region_name;
    @JsonProperty("links")
    private List<Link> links;

}