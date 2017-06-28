package com.jquartz.rich.validation.core.expression.junction;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.TruthValueBinaryOperator;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.rule.ClassField;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public abstract class LogicalOperatorJunction<T> implements Expression<T> {

    private final TruthValueBinaryOperator binaryOperator;
    private final TruthValue defaultValue;
    protected List<Expression<T>> junctions = new ArrayList<>();

    LogicalOperatorJunction(Collection<Expression<T>> expressionsJunctions,
                            TruthValueBinaryOperator binaryOperator,
                            TruthValue defaultValue) {
        this.junctions.addAll(expressionsJunctions != null ? expressionsJunctions : emptyList());
        this.binaryOperator = binaryOperator;
        this.defaultValue = defaultValue;
    }


    @Override
    public TruthValue apply(@Nonnull T instance) {
        TruthValue result = defaultValue;

        for (Expression<T> expression : junctions) {
            result = binaryOperator.apply(result, expression.apply(instance));
        }

        return result;
    }

    @Override
    public Collection<ClassField<T>> getAccomplices() {
        return junctions.stream()
                .map(Expression::getAccomplices)
                .flatMap(Collection::stream)
                .collect(toList());
    }
}