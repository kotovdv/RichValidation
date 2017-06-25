package com.jquartz.rich.validation.core.api.dsl.logic;

import com.jquartz.rich.validation.core.Rule;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.common.OptionalExpression;
import com.jquartz.rich.validation.core.expression.conditional.ConditionalExpression;
import com.jquartz.rich.validation.core.expression.junction.builder.LogicalElseJunctionBuilder;
import com.jquartz.rich.validation.core.expression.junction.builder.LogicalOrJunctionBuilder;

import static com.jquartz.rich.validation.core.api.dsl.logic.Pointer.TARGET;

public class RuleLogicBuilder<T> {

    private final Class<T> targetClass;
    private final String targetFieldName;
    private LogicalElseJunctionBuilder<T> ruleLogic = new LogicalElseJunctionBuilder<>();
    private LogicalOrJunctionBuilder<T> currentConditionJunction = new LogicalOrJunctionBuilder<>();
    private LogicalOrJunctionBuilder<T> currentTargetJunction = new LogicalOrJunctionBuilder<>();
    private LogicalOrJunctionBuilder<T> otherwiseJunction = new LogicalOrJunctionBuilder<>();
    private Pointer pointer = TARGET;

    public RuleLogicBuilder(Class<T> targetClass, String targetFieldName) {
        this.targetClass = targetClass;
        this.targetFieldName = targetFieldName;
    }

    public void switchToMustPart() {
        this.pointer = TARGET;
    }

    public void switchToConditionPart() {
        this.pointer = Pointer.CONDITION;
    }

    public void switchToOtherwise() {
        this.pointer = Pointer.OTHERWISE;
    }

    public void startNewOrJunction() {
        getTargetJunction().startNewOrJunction();
    }

    public void appendExpression(Expression<T> expression) {
        getTargetJunction().addExpression(expression);
    }

    public void persistCurrentChanges() {
        Expression<T> condition = currentConditionJunction.isEmpty()
                ? new OptionalExpression<>()
                : currentConditionJunction.build();

        Expression<T> mustBe = currentTargetJunction.build();

        ruleLogic.add(new ConditionalExpression<>(condition, mustBe));
    }

    public Rule<T> build() {
        if (!otherwiseJunction.isEmpty()) {
            ruleLogic.setOtherwise(otherwiseJunction.build());
        }

        return new Rule<>(ruleLogic.build());
    }

    public Class<T> getTargetClass() {
        return targetClass;
    }

    public String getTargetFieldName() {
        return targetFieldName;
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