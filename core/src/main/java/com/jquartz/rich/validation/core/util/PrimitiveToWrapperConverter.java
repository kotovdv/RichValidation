package com.jquartz.rich.validation.core.util;

import com.google.common.collect.ImmutableMap;
import com.jquartz.rich.validation.core.util.exception.NoWrapperForPrimitiveTypeFoundException;

/**
 * @author Dmitriy Kotov
 */
public class PrimitiveToWrapperConverter {

    private final ImmutableMap<Class<?>, Class<?>> primitiveToWrapperMapping = ImmutableMap.<Class<?>, Class<?>>builder()
            .put(boolean.class, Boolean.class)
            .put(char.class, Character.class)
            .put(byte.class, Byte.class)
            .put(short.class, Short.class)
            .put(int.class, Integer.class)
            .put(float.class, Float.class)
            .put(long.class, Long.class)
            .put(double.class, Double.class)
            .build();

    public Class<?> getWrapperFor(Class<?> primitiveType) {
        Class<?> wrapperType = primitiveToWrapperMapping.get(primitiveType);
        if (wrapperType == null) {
            throw new NoWrapperForPrimitiveTypeFoundException(primitiveType);
        }

        return wrapperType;
    }
}