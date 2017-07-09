package com.jquartz.rich.validation.core.pointer.field;

import com.jquartz.rich.validation.core.pointer.exception.InvalidFieldPointerException;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.lang.reflect.Field;

import static com.jquartz.rich.validation.core.util.Primitives.getWrapperFor;

public class FieldPointerFactory {

    public <S> FieldPointer<?, S> create(Class<S> sourceClass, String fieldName) {
        Field field = createFieldInstance(sourceClass, fieldName);

        return new PlainFieldPointer<>(new ClassField<>(sourceClass, getFieldType(field), fieldName), field);
    }

    private Class<?> getFieldType(Field field) {
        Class<?> initialType = field.getType();

        return initialType.isPrimitive()
                ? getWrapperFor(initialType)
                : initialType;
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