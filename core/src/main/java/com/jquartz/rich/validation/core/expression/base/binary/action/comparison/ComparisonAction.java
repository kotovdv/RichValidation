package com.jquartz.rich.validation.core.expression.base.binary.action.comparison;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.base.binary.action.BinaryAction;
import com.jquartz.rich.validation.core.expression.comparison.operator.ComparisonOperator;

public class ComparisonAction<T extends Comparable<T>> implements BinaryAction {

    private final ComparisonOperator operator;
    private final Class<T> comparableClass;

    public ComparisonAction(ComparisonOperator operator, Class<T> comparableClass) {
        this.operator = operator;
        this.comparableClass = comparableClass;
    }

    @Override
    public <V> TruthValue apply(V leftOperand, V rightOperand) {

        if (leftOperand == null || rightOperand == null) {
            return TruthValue.UNKNOWN;
        }

        return operator.apply(comparableClass.cast(leftOperand), comparableClass.cast(rightOperand))
                ? TruthValue.TRUE
                : TruthValue.FALSE;
    }

    @Override
    public String getTextualRepresentation() {
        return operator.getRepresentation();
    }
}
