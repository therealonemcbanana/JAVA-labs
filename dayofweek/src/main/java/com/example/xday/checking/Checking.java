package com.example.xday.checking;

import com.example.xday.model.InputModel;

public class Checking {
    public static void check(InputModel request) {
        checkYear(request.getYear());
        checkDay(request.getDay(), request.getYear());
    }

    public static void checkDay(int day, int year) {
        if (day < 1 || (year % 4 != 0 && day > 365) || (year % 4 == 0 && day > 366))
            throw new IllegalArgumentException("The value of <<day>> parameter is wrong!");
    }

    public static void checkYear(int year) {
        if (year < 0)
            throw new IllegalArgumentException("The value of <<year>> parameter is wrong!");
    }
}
