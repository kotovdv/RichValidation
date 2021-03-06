package com.jquartz.rich.validation.core.expression.junction;

import com.google.common.base.Joiner;
import com.jquartz.rich.validation.core.api.textual.Tokens;
import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.ConditionalExpression;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.OptionalExpression;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.jquartz.rich.validation.core.api.textual.Tokens.OTHERWISE;
import static com.jquartz.rich.validation.core.evaluation.TruthValue.*;
import static java.util.Collections.emptyList;

/**
 * @author Dmitriy Kotov
 */
public class LogicalElseJunction<T> implements Expression<T> {

    private final List<ConditionalExpression<T>> expressions = new ArrayList<>();
    private final Expression<T> otherwise;

    public LogicalElseJunction(Collection<ConditionalExpression<T>> expressions) {
        this(expressions, new OptionalExpression<>());
    }

    public LogicalElseJunction(Collection<ConditionalExpression<T>> expressions, Expression<T> otherwise) {
        this.expressions.addAll(expressions != null ? expressions : emptyList());
        this.otherwise = otherwise;
    }

    @Override
    public TruthValue apply(@Nonnull T subject) {
        TruthValue hadApplicableExpression = FALSE;
        for (ConditionalExpression<T> expression : expressions) {
            TruthValue applicability = expression.isApplicable(subject);
            if (applicability == TRUE) {
                return expression.apply(subject);
            }

            hadApplicableExpression = hadApplicableExpression.or(applicability);
        }

        return hadApplicableExpression == FALSE
                ? otherwise.apply(subject)
                : UNKNOWN;
    }

    @Override
    public String getTextualRepresentation() {
        return Joiner.on(Tokens.ELSE.toString()).join(expressions) + " " + OTHERWISE + " " + otherwise.getTextualRepresentation();
    }

    @Override
    public String toString() {
        return getTextualRepresentation();
    }
}