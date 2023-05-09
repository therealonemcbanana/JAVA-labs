package com.example.xday.controller;

import com.example.xday.cache.Cache;
import com.example.xday.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller result;
    Service dayofweek;
    Cache cache;
    @BeforeEach
    void Init() {
        cache = new Cache();
        dayofweek = new Service(cache);
        result = new Controller(dayofweek);
    }
    @Test
    void commonYearCorrectRequest() {
        assertEquals(HttpStatus.OK, result.dayofweek("365","2023").getStatusCode());
    }
    @Test
    void leapYearCorrectRequest() {
        assertEquals(HttpStatus.OK, result.dayofweek("366","2020").getStatusCode());
    }
    @Test
    void wrongTypeDay() {
        assertEquals(HttpStatus.BAD_REQUEST, result.dayofweek("ten","2000").getStatusCode());
    }
    @Test
    void wrongTypeYear() { assertEquals(HttpStatus.BAD_REQUEST, result.dayofweek("100","2K22").getStatusCode());
    }
    @Test
    void wrongValueYear() {
        assertEquals(HttpStatus.BAD_REQUEST, result.dayofweek("66","-345").getStatusCode());
    }
    @Test
    void wrongValueCommonYearDay() {
        assertEquals(HttpStatus.BAD_REQUEST, result.dayofweek("366","2021").getStatusCode());
    }
    @Test
    void wrongValueCommonYearDay2() {
        assertEquals(HttpStatus.BAD_REQUEST, result.dayofweek("0","2022").getStatusCode());
    }
    @Test
    void wrongValueLeapYearDay() {
        assertEquals(HttpStatus.BAD_REQUEST, result.dayofweek("367","2004").getStatusCode());
    }

}
