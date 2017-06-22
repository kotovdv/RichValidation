package com.jquartz.rich.validation.core.util;

import com.jquartz.rich.validation.core.util.exception.ArgumentIsNullException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Assertions {

    private static final String DEFAULT_ERROR_MESSAGE = "Invalid argument value of null";

    public static <T> void assertNotNull(@Nullable T object) {
        assertNotNull(object, DEFAULT_ERROR_MESSAGE);
    }

    public static <T> void assertNotNull(@Nullable T object, @Nonnull String message) {
        if (object == null) {
            throw new ArgumentIsNullException(message);
        }
    }
}