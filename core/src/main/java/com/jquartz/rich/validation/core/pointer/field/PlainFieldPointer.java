package com.jquartz.rich.validation.core.pointer.field;

import com.jquartz.rich.validation.core.pointer.exception.FailedToExtractFieldValueException;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.lang.reflect.Field;

public class PlainFieldPointer<T, S> implements FieldPointer<T, S> {

    private final ClassField<T, S> target;
    private final Field field;

    public PlainFieldPointer(ClassField<T, S> target, Field field) {
        this.target = target;
        this.field = field;
    }

    @Override
    public T resolve(S source) {
        try {
            return target.getFieldClass().cast(field.get(source));
        } catch (IllegalAccessException | ClassCastException e) {
            throw new FailedToExtractFieldValueException(target.getSourceClass(), field.getName(), e);
        }
    }

    @Override
    public ClassField<T, S> getTarget() {
        return target;
    }

    @Override
    public Class<? extends T> getPointedClass() {
        return target.getFieldClass();
    }

    @Override
    public String getFieldName() {
        return field.getName();
    }

    @Override
    public Class<S> getSourceClass() {
        return target.getSourceClass();
    }

    public String getTextualRepresentation() {
        return target.getSourceClass().getSimpleName() + "." + field.getName();
    }

    @Override
    public String toString() {
        return getTextualRepresentation();
    }
}
