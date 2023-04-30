package com.example.xday.cache;

import org.apache.juli.logging.Log;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Cache {
    private final Logger LOG =Logger.getLogger(Log.class.getName());
    private final Map< String , String> cache = new HashMap<>();

    public void put(String key, String value) {
        LOG.log(Level.INFO, "New element of cache!");
        this.cache.put(key, value);
    }

    public String get(String key) {
        return cache.get(key);
    }
}
