package com.cdisample.interceptor.cache;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheInterceptor implements MethodInterceptor {

    private final Cache<Object, Object> cache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build();

    @Override
    public Object invoke(final MethodInvocation ctx) throws Throwable {

        final CachedResult ann = ctx.getMethod().getAnnotation(CachedResult.class);
        if (ann != null) {
            final String className = ctx.getClass().getName();
            final String methodName = ctx.getMethod().getName();
            final Object[] args = ctx.getArguments();
            final Object result = this.cache.get(new CacheKey(className, methodName, args), new Callable<Object>() {
                @Override
                public Object call() {
                    Object result = null;
                    try {
                        result = ctx.proceed();
                    } catch (final Throwable e) {
                        e.printStackTrace();
                    }
                    return result;
                }
            });
            return result;
        } else {
            return ctx.proceed();
        }
    }

    static final class CacheKey {
        String className;
        String methodName;
        Object[] args;

        public CacheKey(final String className, final String methodName, final Object[] args) {
            this.className = className;
            this.methodName = methodName;
            this.args = args;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(this.args);
            result = prime * result + ((this.className == null) ? 0 : this.className.hashCode());
            result = prime * result + ((this.methodName == null) ? 0 : this.methodName.hashCode());
            return result;
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            final CacheKey other = (CacheKey) obj;
            if (!Arrays.equals(this.args, other.args)) {
                return false;
            }
            if (this.className == null) {
                if (other.className != null) {
                    return false;
                }
            } else if (!this.className.equals(other.className)) {
                return false;
            }
            if (this.methodName == null) {
                if (other.methodName != null) {
                    return false;
                }
            } else if (!this.methodName.equals(other.methodName)) {
                return false;
            }
            return true;
        }
    }
}
