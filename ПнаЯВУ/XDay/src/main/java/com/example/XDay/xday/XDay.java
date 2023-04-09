package com.example.XDay.xday;


import org.springframework.stereotype.Component;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
@Component
public class XDay {
    public final synchronized String getDay(int day,int year) {
        int[] DinM = new int[] {31,28,31,30,31,30,31,31,30,31,30,31};
        int month=0;
        if (year%100%4==0) DinM[1]=29;
        while(day>DinM[month])
        {
            day-=DinM[month];
            month++;
        }
        Locale RU = new Locale("ru");
        LocalDate date = LocalDate.of(year,month+1,day);
        DayOfWeek x =date.getDayOfWeek();
        String str = x.getDisplayName(TextStyle.FULL,RU);

        return str;
    };
}
