package com.jquartz.rich.validation.core.expression.junction;

import com.google.common.base.Joiner;
import com.jquartz.rich.validation.core.api.textual.Tokens;
import com.jquartz.rich.validation.core.expression.Expression;

import java.util.Collection;

import static com.jquartz.rich.validation.core.evaluation.TruthValue.TRUE;
import static com.jquartz.rich.validation.core.evaluation.TruthValueBinaryOperator.AND;

public class LogicalAndJunction<T> extends LogicalOperatorJunction<T> {

    public LogicalAndJunction(Collection<Expression<T>> junctions) {
        super(junctions, AND, TRUE);
    }

    @Override
    public String getTextualRepresentation() {
        return Joiner.on(" " + Tokens.AND.toString() + " ").join(junctions);
    }

    @Override
    public String toString() {
        return getTextualRepresentation();
    }
}