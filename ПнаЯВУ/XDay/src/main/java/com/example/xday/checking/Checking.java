package com.example.xday.checking;
public class Checking {
    public static void check(String day, String year){
        checkType(day,year);
        checkDay(Integer.parseInt(day),Integer.parseInt(year));
        checkYear(Integer.parseInt(year));
    }
    public static void checkType(String day, String year) {
        try {
            Integer.parseInt(day);
            Integer.parseInt(year);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The type of <<day>> or <<year>> is wrong!");
        }
    }
    public static void checkDay(int day, int year){
        if (day<1 || (year%4!=0 && day>365) || (year%4==0 && day>366) )
            throw new IllegalArgumentException("The value of <<day>> parameter is wrong!");
    }
    public static void checkYear(int year){
        if (year<0)
            throw new IllegalArgumentException("The value of <<year>> parameter is wrong!");
    }
}
