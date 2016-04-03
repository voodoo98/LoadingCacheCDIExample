package com.cdisample.module;

import static com.google.inject.matcher.Matchers.*;

import com.cdisample.interceptor.cache.CacheInterceptor;
import com.cdisample.interceptor.cache.CachedResult;
import com.google.inject.AbstractModule;

public class CacheModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bindInterceptor(any(), annotatedWith(CachedResult.class), new CacheInterceptor());
    }

}
