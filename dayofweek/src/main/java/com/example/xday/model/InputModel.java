package com.example.xday.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputModel {
    @JsonProperty("day")
    private final Integer day;
    @JsonProperty("year")
    private final Integer year;

    public InputModel(Integer day, Integer year){
        this.day=day;
        this.year=year;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getYear() {
        return year;
    }
}
