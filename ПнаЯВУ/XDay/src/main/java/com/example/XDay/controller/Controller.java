package com.example.XDay.controller;

import com.example.XDay.checking.Checking;

import com.example.XDay.xday.XDay;
import org.apache.juli.logging.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;
import java.util.logging.Level;

@RestController
public class Controller {
    private final XDay x;
    public Controller(XDay x) {
        this.x = x;
    }
    @GetMapping("xday")
    public ResponseEntity<String> xday(@RequestParam("day") String day,
                                       @RequestParam("year") String year) {
        Logger LOG = Logger.getLogger(Log.class.getName());
        ResponseEntity<String> entity = Checking.check(day, year);
        return entity.getStatusCode() == HttpStatus.OK ?
                new ResponseEntity<>("{ DayOfWeek: " + x.getDay(Integer.parseInt(day), Integer.parseInt(year)) + " }", HttpStatus.OK) :
                entity;
    }

}
