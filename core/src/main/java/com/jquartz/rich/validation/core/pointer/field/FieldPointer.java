package com.jquartz.rich.validation.core.pointer.field;

import com.jquartz.rich.validation.core.pointer.Pointer;

public interface FieldPointer<T, S> extends Pointer {

    T resolve(S source);

    Class<T> getPointedClass();
}
