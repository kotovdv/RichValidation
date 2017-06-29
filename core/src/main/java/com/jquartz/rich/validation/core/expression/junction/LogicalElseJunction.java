package com.jquartz.rich.validation.core.expression.junction;

import com.google.common.base.Joiner;
import com.jquartz.rich.validation.core.api.textual.Tokens;
import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.Trustworthiness;
import com.jquartz.rich.validation.core.expression.ConditionalExpression;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.OptionalExpression;
import com.jquartz.rich.validation.core.expression.base.AbstractExpression;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.jquartz.rich.validation.core.api.textual.Tokens.OTHERWISE;
import static com.jquartz.rich.validation.core.evaluation.TruthValue.*;
import static java.util.Collections.emptyList;

/**
 * @author Dmitriy Kotov
 */
public class LogicalElseJunction<T> extends AbstractExpression<T> {

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
    public TruthValue apply(T subject, Trustworthiness trustworthiness) {
        TruthValue hadApplicableExpression = FALSE;
        for (ConditionalExpression<T> expression : expressions) {
            TruthValue applicability = expression.isApplicable(subject, trustworthiness);
            if (applicability == TRUE) {
                return expression.apply(subject, trustworthiness);
            }

            hadApplicableExpression = hadApplicableExpression.or(applicability);
        }

        return hadApplicableExpression == FALSE
                ? otherwise.apply(subject, trustworthiness)
                : UNKNOWN;
    }

    @Override
    public String getTextualRepresentation() {
        return Joiner.on(Tokens.ELSE.toString())
                .join(expressions) + " " + OTHERWISE + " " + otherwise
                .getTextualRepresentation();
    }

    @Override
    public Collection<ClassField<?, T>> getAccomplices() {
        Stream<ClassField<?, T>> expressionAccomplicesStream = expressions.stream()
                .map(ConditionalExpression::getAccomplices)
                .flatMap(Collection::stream);

        Stream<ClassField<?, T>> otherwiseAccomplicesStream = otherwise.getAccomplices().stream();

        return Stream.concat(expressionAccomplicesStream, otherwiseAccomplicesStream).collect(Collectors.toList());
    }
}