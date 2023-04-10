package com.example.xday.controller;

import com.example.xday.checking.Checking;
import com.example.xday.xday.XDay;

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
    private final XDay xday;
    private static final Logger LOG =Logger.getLogger(Log.class.getName());
    public Controller(XDay xday) {
        this.xday = xday;
    }
    @GetMapping("xday")
    public ResponseEntity<String> xday(@RequestParam("day") String day,
                                       @RequestParam("year") String year) {
        try{
            Checking.check(day, year);
            LOG.log(Level.INFO, "Request was successfully processed!");
            return new ResponseEntity<>("{ DayOfWeek: " + xday.getDay(Integer.parseInt(day), Integer.parseInt(year)) + " }", HttpStatus.OK);
        } catch(IllegalArgumentException error) {
            LOG.log(Level.WARNING, "Request Status: BAD REQUEST\n" + error.getMessage());
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
