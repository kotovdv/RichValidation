package com.jquartz.rich.validation.core.rule;

public class ClassField<T, S> {

    private final Class<S> sourceClass;
    private final Class<T> fieldClass;
    private final String fieldName;

    public ClassField(Class<S> sourceClass, Class<T> fieldClass, String fieldName) {
        this.sourceClass = sourceClass;
        this.fieldClass = fieldClass;
        this.fieldName = fieldName;
    }

    public Class<S> getSourceClass() {
        return sourceClass;
    }

    public Class<T> getFieldClass() {
        return fieldClass;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassField<?, ?> that = (ClassField<?, ?>) o;

        if (!sourceClass.equals(that.sourceClass)) return false;
        return fieldName.equals(that.fieldName);
    }

    @Override
    public int hashCode() {
        int result = sourceClass.hashCode();
        result = 31 * result + fieldName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return sourceClass.getSimpleName() + "." + fieldName;
    }
}
