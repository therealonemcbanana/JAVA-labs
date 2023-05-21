package com.example.xday.controller;

import com.example.xday.cache.Cache;
import com.example.xday.model.InputModel;
import com.example.xday.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller result;
    Service service;
    Cache cache;
    @BeforeEach
    void Init() {
        cache = new Cache();
        service = new Service(cache);
        result = new Controller(service);
    }
    @Test
    void commonYearCorrectRequest() {
        assertEquals(HttpStatus.OK, result.getAnswer(365,2023).getStatusCode());
    }
    @Test
    void leapYearCorrectRequest() {
        assertEquals(HttpStatus.OK, result.getAnswer(366,2020).getStatusCode());
    }

}
