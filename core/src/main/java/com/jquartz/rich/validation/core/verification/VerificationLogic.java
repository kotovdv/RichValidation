package com.jquartz.rich.validation.core.verification;

import com.jquartz.rich.validation.core.verification.expression.VerificationLogicExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Kotov
 */
public class VerificationLogic {

    private List<VerificationLogicExpression> expressions = new ArrayList<>();

    public <T> VerificationResult apply(T instance) {
        for (VerificationLogicExpression expression : expressions) {
//            expression.apply(instance);
        }


        return null;
    }
}