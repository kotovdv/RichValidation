package com.jquartz.rich.validation.core.pointer.field;

import com.jquartz.rich.validation.core.pointer.Pointer;
import com.jquartz.rich.validation.core.rule.ClassField;

public interface FieldPointer<T, S> extends Pointer {

    T resolve(S source);

    ClassField<T, S> getTarget();

    Class<? extends T> getPointedClass();

    Class<S> getSourceClass();

    String getFieldName();
}
