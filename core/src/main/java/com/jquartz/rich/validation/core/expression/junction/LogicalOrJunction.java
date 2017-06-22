package com.jquartz.rich.validation.core.expression.junction;

import com.jquartz.rich.validation.core.expression.Expression;

import java.util.Collection;

import static com.jquartz.rich.validation.core.evaluation.TruthValue.FALSE;
import static com.jquartz.rich.validation.core.evaluation.TruthValueBinaryOperator.OR;

public class LogicalOrJunction<T> extends LogicalOperatorJunction<T> {

    public LogicalOrJunction(Collection<Expression<T>> junctions) {
        super(junctions, OR, FALSE);
    }
}