package com.cdisample.interceptor.time;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TimeInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(final MethodInvocation ctx) throws Throwable {
        final long time = System.currentTimeMillis();
        final Object proceed = ctx.proceed();
        System.out.println("Running time: " + (System.currentTimeMillis() - time));
        return proceed;
    }

}
