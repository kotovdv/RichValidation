package com.jquartz.rich.validation.core.pointer.field;

import com.jquartz.rich.validation.core.pointer.exception.InvalidFieldPointerException;

import java.lang.reflect.Field;

public class FieldPointerFactory {

    public <S> FieldPointer<?, S> create(Class<S> sourceClass, String fieldName) {
        Field field = createFieldInstance(sourceClass, fieldName);

        return new PlainFieldPointer<>(sourceClass, field.getType(), field);
    }

    private <S> Field createFieldInstance(Class<S> sourceClass, String fieldName) {
        try {
            Field declaredField = sourceClass.getDeclaredField(fieldName);
            declaredField.setAccessible(true);

            return declaredField;
        } catch (ReflectiveOperationException e) {
            throw new InvalidFieldPointerException(fieldName, sourceClass, e);
        }
    }
}