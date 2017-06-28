package com.jquartz.rich.validation.core.api.dsl.logic;

import com.jquartz.rich.validation.core.Rule;
import com.jquartz.rich.validation.core.expression.ConditionalExpression;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.OptionalExpression;
import com.jquartz.rich.validation.core.expression.junction.builder.LogicalElseJunctionBuilder;
import com.jquartz.rich.validation.core.expression.junction.builder.LogicalOrJunctionBuilder;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.concurrent.atomic.AtomicInteger;

import static com.jquartz.rich.validation.core.api.dsl.logic.RulePartPointer.TARGET;

public class RuleLogicBuilder<T> {

    private static AtomicInteger counter = new AtomicInteger(0);
    private final ClassField<?, T> target;
    private LogicalElseJunctionBuilder<T> ruleLogic = new LogicalElseJunctionBuilder<>();
    private LogicalOrJunctionBuilder<T> currentConditionJunction = new LogicalOrJunctionBuilder<>();
    private LogicalOrJunctionBuilder<T> currentTargetJunction = new LogicalOrJunctionBuilder<>();
    private LogicalOrJunctionBuilder<T> otherwiseJunction = new LogicalOrJunctionBuilder<>();
    private RulePartPointer pointer = TARGET;

    public RuleLogicBuilder(ClassField<?, T> target) {
        this.target = target;
    }

    public void switchToMustPart() {
        this.pointer = TARGET;
    }

    public void switchToConditionPart() {
        this.pointer = RulePartPointer.CONDITION;
    }

    public void switchToOtherwise() {
        this.pointer = RulePartPointer.OTHERWISE;
    }

    public void startNewOrJunction() {
        getTargetJunction().startNewOrJunction();
    }

    public void appendExpression(Expression<T> expression) {
        getTargetJunction().addExpression(expression);
    }

    public void persistCurrentChanges() {
        if (currentTargetJunction.isEmpty()) {
            return;
        }

        Expression<T> condition = currentConditionJunction.isEmpty()
                ? new OptionalExpression<>()
                : currentConditionJunction.build();

        Expression<T> mustBe = currentTargetJunction.build();

        currentConditionJunction.clear();
        currentTargetJunction.clear();

        ruleLogic.add(new ConditionalExpression<>(condition, mustBe));
    }

    public Rule<T> build() {
        if (!otherwiseJunction.isEmpty()) {
            ruleLogic.setOtherwise(otherwiseJunction.build());
        }

        return new Rule<>(
                target,
                Integer.toString(counter.incrementAndGet()),
                ruleLogic.build());
    }

    public Class<T> getTargetClass() {
        return target.getSourceClass();
    }

    public String getTargetFieldName() {
        return target.getFieldName();
    }

    private LogicalOrJunctionBuilder<T> getTargetJunction() {
        switch (pointer) {
            case TARGET:
                return currentTargetJunction;
            case CONDITION:
                return currentConditionJunction;
            case OTHERWISE:
                return otherwiseJunction;
            default: {
                throw new IllegalPointerException(pointer);
            }
        }
    }
}