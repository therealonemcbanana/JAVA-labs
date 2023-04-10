package com.example.xday.controller;

import com.example.xday.xday.XDay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller result;
    XDay xday;
    @BeforeEach
    void Init() {
        xday = new XDay();
        result = new Controller(xday);
    }
    @Test
    void commonYearCorrectRequest() {
        assertEquals(HttpStatus.OK, result.xday("365","2023").getStatusCode());
    }
    @Test
    void leapYearCorrectRequest() {
        assertEquals(HttpStatus.OK, result.xday("366","2020").getStatusCode());
    }
    @Test
    void wrongTypeDay() {
        assertEquals(HttpStatus.BAD_REQUEST, result.xday("ten","2000").getStatusCode());
    }
    @Test
    void wrongTypeYear() { assertEquals(HttpStatus.BAD_REQUEST, result.xday("100","2K22").getStatusCode());
    }
    @Test
    void wrongValueYear() {
        assertEquals(HttpStatus.BAD_REQUEST, result.xday("66","-345").getStatusCode());
    }
    @Test
    void wrongValueCommonYearDay() {
        assertEquals(HttpStatus.BAD_REQUEST, result.xday("366","2021").getStatusCode());
    }
    @Test
    void wrongValueCommonYearDay2() {
        assertEquals(HttpStatus.BAD_REQUEST, result.xday("0","2022").getStatusCode());
    }
    @Test
    void wrongValueLeapYearDay() {
        assertEquals(HttpStatus.BAD_REQUEST, result.xday("367","2004").getStatusCode());
    }

}
