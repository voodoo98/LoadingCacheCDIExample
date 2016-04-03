package com.cdisample.module;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;

import com.cdisample.interceptor.time.TimeInterceptor;
import com.cdisample.interceptor.time.TimeResult;
import com.google.inject.AbstractModule;

public class TimeModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bindInterceptor(any(), annotatedWith(TimeResult.class), new TimeInterceptor());
    }
}
