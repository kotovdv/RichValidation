package com.jquartz.rich.validation.core.api.textual;

/**
 * Temporary stub for future textual API
 *
 * @author Dmitriy Kotov
 */
public enum Tokens {
    IS_EQUAL_TO("IS EQUAL TO"),
    IS_NOT_EQUAL_TO("IS NOT EQUAL TO"),
    IS_NULL("IS NULL"),
    IS_NOT_NULL("IS NOT NULL"),
    OPTIONAL("---"),
    ENSURE_THAT("ENSURE THAT"),
    WHEN("WHEN"),
    ELSE("ELSE ENSURE THAT"),
    AND("AND"),
    OR("OR"),
    OTHERWISE("OTHERWISE ENSURE THAT");

    private final String representation;

    Tokens(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}