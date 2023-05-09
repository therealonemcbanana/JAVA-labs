package com.example.xday.controller;

import com.example.xday.checking.Checking;
import com.example.xday.counter.Counter;
import com.example.xday.service.Service;
import org.apache.juli.logging.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Level;
import java.util.logging.Logger;
@RestController
public class Controller {
    private final Service dayofweek;
    private final Logger LOG =Logger.getLogger(Log.class.getName());
    public Controller(Service dayofweek) {
        this.dayofweek = dayofweek;
    }
    @GetMapping("dayofweek")
    public ResponseEntity<String> dayofweek(@RequestParam("day") String day,
                                       @RequestParam("year") String year) {
        try{
            Counter.increment();
            LOG.log(Level.INFO, "Request number: " + Counter.getCount());
            Checking.check(day, year);
            LOG.log(Level.INFO, "Request was successfully processed!");
            return new ResponseEntity<>("{ DayOfWeek: " + dayofweek.getDay(day,year) + " }", HttpStatus.OK);
        } catch(IllegalArgumentException error) {
            LOG.log(Level.WARNING, "Request Status: BAD REQUEST " + error.getMessage()+"/n");
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
