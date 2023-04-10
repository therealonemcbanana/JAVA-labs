package com.example.xday.xday;

import org.springframework.stereotype.Component;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
@Component
public class XDay {
    public final String getDay(int day,int year) {
        Locale RU = new Locale("ru");
        LocalDate date = LocalDate.ofYearDay(year, day);
        DayOfWeek x =date.getDayOfWeek();
        String str = x.getDisplayName(TextStyle.FULL,RU);
        return str;
    };
}
