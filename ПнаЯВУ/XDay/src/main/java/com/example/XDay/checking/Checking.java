package com.example.XDay.checking;

import org.apache.juli.logging.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.logging.Level;
import java.util.logging.Logger;
public class Checking extends Exception{
    public static ResponseEntity<String> check(String day, String year){
        Logger LOG = Logger.getLogger(Log.class.getName());
        String str = Checking.checkType(day, year);
        if (!str.equals("The type of <<day>>, <<year>> parameters is right!")) {
            LOG.log(Level.WARNING, "Request Status: BAD_REQUEST\n" + str);
            return new ResponseEntity<>(str, HttpStatus.BAD_REQUEST);
        }
        str = Checking.checkYear(Integer.parseInt(year));
        if (!str.equals("The value of <<year>> parameter is right!")) {
            LOG.log(Level.WARNING, "Request Status: BAD_REQUEST\n" + str);
            return new ResponseEntity<>(str, HttpStatus.BAD_REQUEST);
        }
        str = Checking.checkDay(Integer.parseInt(day),Integer.parseInt(year));
        if (!str.equals("The value of <<day>> parameter is right!")) {
            LOG.log(Level.WARNING, "Request Status: BAD_REQUEST\n" + str);
            return new ResponseEntity<>(str, HttpStatus.BAD_REQUEST);
        }
        LOG.log(Level.INFO, "Request was successfully processed!");
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    public static String checkType(String day, String year) {
        try {
            Integer.parseInt(day);
            Integer.parseInt(year);
        } catch (NumberFormatException e) {
            return "The type of <<day>> or <<year>> parameter is wrong!";
        }
        return "The type of <<day>>, <<year>> parameters is right!";
    }

    public static String checkDay(int day,int year){
        if ( (year%4==0 && day>0 && day<367) || (year%4!=0 && day>0 && day<366) )
            return "The value of <<day>> parameter is right!";
        else return "The value of <<day>> parameter is wrong!";

    }

    public static String checkYear(int year){
        return year >= 0 ? "The value of <<year>> parameter is right!" : "The value of <<year>> parameter is wrong!";
    }
}
