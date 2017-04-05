package com.seagen.ecc.common;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.DefaultKeyGenerator;

/**
 * spring 缓存之键生成器
 * @author jimbo
 */
public class MethodKeyGenerator extends DefaultKeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        Object[] ret = new Object[params.length + 1];
        ret[0] = target.getClass().getName() + "." + method.getName();
        for (int i = 0; i < params.length; i++) {
            ret[i + 1] = params[i];
        }
        return ret;
    }

}