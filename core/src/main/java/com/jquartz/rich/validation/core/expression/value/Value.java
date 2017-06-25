package com.jquartz.rich.validation.core.expression.value;

public interface Value<T, S> {

    T get(S source);

    String getTextualRepresentation();
}