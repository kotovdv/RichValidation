package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.Trustworthiness;
import com.jquartz.rich.validation.core.evaluation.trust.TrustworthinessStub;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.Collection;

/**
 * @author Dmitriy Kotov
 */
public class Rule<T> {

    private final ClassField<?, T> target;

    private final String ruleName;
    private final Expression<T> expression;

    public Rule(ClassField<?, T> target, String ruleName, Expression<T> expression) {
        this.target = target;
        this.ruleName = ruleName;
        this.expression = expression;
    }

    public TruthValue validate(T instance) {
        return expression.apply(instance, TrustworthinessStub.INSTANCE);
    }

    public TruthValue validate(T instance, Trustworthiness trustworthiness) {
        return expression.apply(instance, trustworthiness);
    }

    public String getTextualRepresentation() {
        return expression.getTextualRepresentation();
    }

    public ClassField<?, T> getTarget() {
        return target;
    }

    public Collection<ClassField<?, T>> getAccomplices() {
        Collection<ClassField<?, T>> accomplices = expression.getAccomplices();
        accomplices.remove(target);

        return accomplices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule<?> rule = (Rule<?>) o;

        if (!target.equals(rule.target)) return false;
        return ruleName.equals(rule.ruleName);
    }

    @Override
    public int hashCode() {
        int result = 0;

        result = target.hashCode();
        result = 31 * result + ruleName.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return getTextualRepresentation();
    }
}