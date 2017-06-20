package com.jquartz.rich.validation.core.verification.builder;

import com.jquartz.rich.validation.core.api.FieldPointer;
import com.jquartz.rich.validation.core.exception.api.InvalidFieldPointerException;

import java.lang.reflect.Field;

public class FieldPointerFactory {

    public <S> FieldPointer<?, S> createPointer(Class<S> sourceClass, String fieldName) {
        Field field = createFieldInstance(sourceClass, fieldName);

        return new FieldPointer<>(sourceClass, field.getType(), field);
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