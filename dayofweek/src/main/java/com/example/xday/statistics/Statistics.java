package com.example.xday.statistics;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statistics {
    public static  String mostPopular(List<String> list) {
        Map.Entry<String, Long> mostPopularValue = list.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
        if (mostPopularValue == null) return "there is no result";
        else return  mostPopularValue.getKey();
    }
}
