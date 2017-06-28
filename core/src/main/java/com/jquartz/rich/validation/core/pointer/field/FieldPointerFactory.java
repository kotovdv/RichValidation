package com.jquartz.rich.validation.core.pointer.field;

import com.jquartz.rich.validation.core.pointer.exception.InvalidFieldPointerException;
import com.jquartz.rich.validation.core.rule.ClassField;
import com.jquartz.rich.validation.core.util.PrimitiveToWrapperConverter;

import java.lang.reflect.Field;

public class FieldPointerFactory {

    private PrimitiveToWrapperConverter converter = new PrimitiveToWrapperConverter();

    public <S> FieldPointer<?, S> create(Class<S> sourceClass, String fieldName) {
        Field field = createFieldInstance(sourceClass, fieldName);

        return new PlainFieldPointer<>(new ClassField<>(sourceClass, field.getType(), fieldName), field);
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