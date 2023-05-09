package com.example.xday.service;

import org.springframework.stereotype.Component;
import com.example.xday.cache.Cache;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
@Component
public class Service {
    private final Cache cache;
    public Service(Cache cache) {
        this.cache = cache;
    }
    public final String getDay(String day,String year) {
        String key = day+':'+year;
        if (cache.get(key) == null) {
            Locale RU = new Locale("ru");
            LocalDate date = LocalDate.ofYearDay(Integer.parseInt(year), Integer.parseInt(day));
            DayOfWeek x = date.getDayOfWeek();
            String str = x.getDisplayName(TextStyle.FULL, RU);
            cache.put(key,str);
            return str;
        }
        return cache.get(key);
    }
}