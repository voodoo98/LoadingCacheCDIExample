package com.cdisample.consumer;

import com.cdisample.interceptor.cache.ObjectCache;
import com.cdisample.module.AllModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class MainApplication {

    public static void main(final String[] args) {
        final Injector injector = Guice.createInjector(new AllModule());
        final ObjectCache schemaCache = injector.getInstance(ObjectCache.class);

        System.err.println(schemaCache.getCached("apple"));
        System.err.println(schemaCache.getCached("peach"));
        System.err.println(schemaCache.getCached("malone"));
        System.err.println(schemaCache.getCached("apple"));
        System.err.println(schemaCache.getCached("apple"));

    }
}