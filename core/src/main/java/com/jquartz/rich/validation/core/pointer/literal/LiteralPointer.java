package com.jquartz.rich.validation.core.pointer.literal;

import com.jquartz.rich.validation.core.pointer.Pointer;

import java.util.Optional;

public interface LiteralPointer<T> extends Pointer {

    T resolve();

    Optional<Class<? extends T>> getPointedClass();
}
