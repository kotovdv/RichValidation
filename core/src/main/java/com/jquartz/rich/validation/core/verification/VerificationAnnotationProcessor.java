package com.jquartz.rich.validation.core.verification;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.jquartz.rich.validation.core.annotations.Validate;
import com.jquartz.rich.validation.core.verification.expression.LogicExpression;
import com.jquartz.rich.validation.core.verification.expression.factory.LogicExpressionFactory;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Dmitriy Kotov
 */
public class VerificationAnnotationProcessor {

    private final LogicExpressionFactory expressionFactory = new LogicExpressionFactory();

    public VerificationLogic process(@Nonnull Class<?> subjectClass) {
        Multimap<Field, Validate> appliedVerification = getVerificationApplied(subjectClass);

        return new VerificationLogic(createLogicExpressions(appliedVerification));
    }

    private List<LogicExpression<?>> createLogicExpressions(Multimap<Field, Validate> appliedVerification) {
        List<LogicExpression<?>> expressions = new ArrayList<>();

        for (Field field : appliedVerification.keySet()) {
            for (Validate currentVerify : appliedVerification.get(field)) {
                expressions.add(expressionFactory.create(field, currentVerify));
            }
        }

        return expressions;
    }

    private Multimap<Field, Validate> getVerificationApplied(@Nonnull Class<?> subjectClass) {
        Field[] declaredFields = subjectClass.getDeclaredFields();

        Multimap<Field, Validate> appliedVerification = HashMultimap.create();
        for (Field currentField : declaredFields) {
            Validate[] fieldVerification = currentField.getAnnotationsByType(Validate.class);
            if (fieldVerification.length > 0) {
                appliedVerification.putAll(currentField, asList(fieldVerification));
            }
        }

        return appliedVerification;
    }
}