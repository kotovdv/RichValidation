package com.jquartz.rich.validation.core.rule;

public class ClassField<T> {

    private final Class<T> ownerClass;
    private final String fieldName;

    public ClassField(Class<T> ownerClass, String fieldName) {
        this.ownerClass = ownerClass;
        this.fieldName = fieldName;
    }

    public Class<T> getOwnerClass() {
        return ownerClass;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassField)) return false;

        ClassField target = (ClassField) o;

        if (!ownerClass.equals(target.ownerClass)) return false;
        return fieldName.equals(target.fieldName);
    }

    @Override
    public int hashCode() {
        int result = ownerClass.hashCode();
        result = 31 * result + fieldName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ClassField{" +
                "ownerClass=" + ownerClass +
                ", fieldName='" + fieldName + '\'' +
                '}';
    }
}
