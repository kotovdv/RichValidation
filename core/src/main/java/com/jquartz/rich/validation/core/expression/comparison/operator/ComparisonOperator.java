package com.jquartz.rich.validation.core.expression.comparison.operator;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public enum ComparisonOperator implements Comparison {

    EQUAL_TO("==") {
        @Override
        public <T extends Comparable<T>> boolean apply(@Nonnull T leftValue, @Nonnull T rightValue) {
            return compare(leftValue, rightValue) == 0;
        }
    },
    NOT_EQUAL_TO("!=") {
        @Override
        public <T extends Comparable<T>> boolean apply(@Nonnull T leftValue, @Nonnull T rightValue) {
            return compare(leftValue, rightValue) != 0;
        }
    },
    GREATER_THAN(">") {
        @Override
        public <T extends Comparable<T>> boolean apply(@Nonnull T leftValue, @Nonnull T rightValue) {
            return compare(leftValue, rightValue) > 0;
        }
    },
    GREATER_OR_EQUAL_TO(">=") {
        @Override
        public <T extends Comparable<T>> boolean apply(@Nonnull T leftValue, @Nonnull T rightValue) {
            return compare(leftValue, rightValue) >= 0;
        }
    },
    LESS_THAN("<") {
        @Override
        public <T extends Comparable<T>> boolean apply(@Nonnull T leftValue, @Nonnull T rightValue) {
            return compare(leftValue, rightValue) < 0;
        }
    },

    LESS_OR_EQUAL_TO("<=") {
        @Override
        public <T extends Comparable<T>> boolean apply(@Nonnull T leftValue, @Nonnull T rightValue) {
            return compare(leftValue, rightValue) <= 0;
        }
    };

    private final String representation;

    ComparisonOperator(String representation) {
        this.representation = representation;
    }

    private static <T extends Comparable<T>> int compare(@Nonnull T leftValue, @Nonnull T rightValue) {
        return leftValue.compareTo(rightValue);
    }

    public String getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}