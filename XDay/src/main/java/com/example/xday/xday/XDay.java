package com.example.xday.xday;

import org.apache.juli.logging.Log;
import org.springframework.stereotype.Component;
import com.example.xday.cache.Cache;
import com.example.xday.counter.Counter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
@Component
public class XDay {
    private final Logger LOG = Logger.getLogger(Log.class.getName());
    private final Cache cache;
    public XDay(Cache cache) {
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
        }
        return cache.get(key);
    }
}
