package com.jquartz.rich.validation.core.verification.expression.operator;

import com.jquartz.rich.validation.core.verification.expression.value.Value;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public enum ComparisonOperator implements Comparison {

    EQUAL_TO {
        @Override
        public <T extends Comparable<T>> boolean apply(@Nonnull Value<T> leftValue, @Nonnull Value<T> rightValue) {
            return compare(leftValue, rightValue) == 0;
        }
    },
    NOT_EQUAL_TO {
        @Override
        public <T extends Comparable<T>> boolean apply(@Nonnull Value<T> leftValue, @Nonnull Value<T> rightValue) {
            return compare(leftValue, rightValue) != 0;
        }
    },
    GREATER_THAN {
        @Override
        public <T extends Comparable<T>> boolean apply(@Nonnull Value<T> leftValue, @Nonnull Value<T> rightValue) {
            return compare(leftValue, rightValue) > 0;
        }
    },
    GREATER_OR_EQUAL_TO {
        @Override
        public <T extends Comparable<T>> boolean apply(@Nonnull Value<T> leftValue, @Nonnull Value<T> rightValue) {
            return compare(leftValue, rightValue) >= 0;
        }
    },
    LESS_THAN {
        @Override
        public <T extends Comparable<T>> boolean apply(@Nonnull Value<T> leftValue, @Nonnull Value<T> rightValue) {
            return compare(leftValue, rightValue) < 0;
        }
    },

    LESS_OR_EQUAL_TO {
        @Override
        public <T extends Comparable<T>> boolean apply(@Nonnull Value<T> leftValue, @Nonnull Value<T> rightValue) {
            return compare(leftValue, rightValue) <= 0;
        }
    };

    private static <T extends Comparable<T>> int compare(@Nonnull Value<T> leftValue, @Nonnull Value<T> rightValue) {
        return leftValue.get().compareTo(rightValue.get());
    }
}