package com.jquartz.rich.validation.core.pointer.field;

import com.jquartz.rich.validation.core.pointer.exception.FailedToExtractFieldValueException;

import java.lang.reflect.Field;

public class PlainFieldPointer<T, S> implements FieldPointer<T, S> {

    private final Class<S> sourceClass;
    private final Class<T> targetClass;
    private final Field field;

    public PlainFieldPointer(Class<S> sourceClass, Class<T> targetClass, Field field) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
        this.field = field;
    }

    @SuppressWarnings("unchecked")
    public T resolve(S source) {
        try {
            return (T) field.get(source);
        } catch (IllegalAccessException e) {
            throw new FailedToExtractFieldValueException(sourceClass, field.getName(), e);
        }
    }

    @Override
    public Class<T> getPointedClass() {
        return targetClass;
    }

    public Class<S> getSourceClass() {
        return sourceClass;
    }

    public Class<T> getTargetClass() {
        return targetClass;
    }

    public String getTextualRepresentation() {
        return sourceClass.getSimpleName() + "." + field.getName();
    }

    @Override
    public String toString() {
        return getTextualRepresentation();
    }
}
