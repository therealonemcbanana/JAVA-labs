package com.example.xday.controller;

import com.example.xday.checking.Checking;
import com.example.xday.counter.Counter;
import com.example.xday.model.InputModel;
import com.example.xday.service.Service;
import com.example.xday.statistics.Statistics;
import org.apache.juli.logging.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class Controller {
    private final Service service;
    private final Logger LOG = Logger.getLogger(Log.class.getName());

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("counter")
    public ResponseEntity<?> getCounter() {
        LOG.log(Level.INFO, "Get counter");
        return new ResponseEntity<>("Amount of requests : " + Counter.getCount(), HttpStatus.OK);
    }

    @GetMapping("service")
    public ResponseEntity<String> getAnswer(@RequestParam("day") Integer day,
                                            @RequestParam("year") Integer year) {
        try {
            Counter.increment();
            Checking.check(new InputModel(day, year));
            LOG.log(Level.INFO, "Request was successfully processed!");
            return new ResponseEntity<>("DayOfWeek: " + service.getDayOfWeek(new InputModel(day, year)), HttpStatus.OK);
        } catch (IllegalArgumentException error) {
            LOG.log(Level.WARNING, "Request Status: BAD REQUEST " + error.getMessage());
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("bulk")
    public ResponseEntity<?> getAnswers(@RequestBody List<InputModel> requests) {
        List<String> resultList = requests.stream().map(request -> {
            try {
                Checking.check(request);
                return service.getDayOfWeek(request);
            } catch (Exception error) {
                return "Bad request";
            }
        }).toList();
        LOG.log(Level.INFO, "Requests was successfully processed!");
        String mostPopularResult = Statistics.mostPopular(resultList.stream()
                .filter(value -> !value.startsWith("B")).toList());
        return new ResponseEntity<>(resultList + "\nMost popular result : " + mostPopularResult, HttpStatus.OK);
    }
}
