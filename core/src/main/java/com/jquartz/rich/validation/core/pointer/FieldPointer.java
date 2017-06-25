package com.jquartz.rich.validation.core.pointer;

import com.jquartz.rich.validation.core.pointer.exception.FailedToExtractFieldValueException;

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

    @SuppressWarnings("unchecked")
    public T resolve(S sourceInstance) {
        try {
            return (T) field.get(sourceInstance);
        } catch (IllegalAccessException e) {
            throw new FailedToExtractFieldValueException(sourceClass, field.getName(), e);
        }
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
