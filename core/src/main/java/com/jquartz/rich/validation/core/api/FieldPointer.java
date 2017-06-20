package com.jquartz.rich.validation.core.api;

import com.jquartz.rich.validation.core.exception.api.FailedToExtractFieldValueException;

import java.lang.reflect.Field;

public class FieldPointer<T, S> {

    private final Class<S> sourceClass;
    private final Class<T> targetClass;
    private final Field field;

    public FieldPointer(Class<S> sourceClass, Class<T> targetClass, Field field) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
        this.field = field;
    }

    public T resolve(S sourceInstance) {
        try {
            return targetClass.cast(field.get(sourceInstance));
        } catch (IllegalAccessException e) {
            throw new FailedToExtractFieldValueException(sourceClass, field.getName(), e);
        }
    }

    public Class<S> getSourceClass() {
        return sourceClass;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }
}
