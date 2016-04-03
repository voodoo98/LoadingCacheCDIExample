package com.cdisample.interceptor.cache;

import java.text.MessageFormat;

import com.cdisample.interceptor.time.TimeResult;

public class ObjectCache {

    @TimeResult
    @CachedResult
    public String getCached(final String key) {
        try {
            Thread.sleep(500);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        return MessageFormat.format("{0}_{1}", key, System.currentTimeMillis());
    }

}
