package com.jquartz.rich.validation.core.subject;

import com.jquartz.rich.validation.core.expression.comparison.operator.ComparisonOperator;

/**
 * @author Dmitriy Kotov
 */
public class ComparisonResultSubject {
    public static final String COMPARISON_RESULT = "comparisonResult";
    public static final String OPERATOR = "operator";
    private final int comparisonResult;
    private final ComparisonOperator operator;

    public ComparisonResultSubject(int comparisonResult, ComparisonOperator operator) {
        this.comparisonResult = comparisonResult;
        this.operator = operator;
    }
}
