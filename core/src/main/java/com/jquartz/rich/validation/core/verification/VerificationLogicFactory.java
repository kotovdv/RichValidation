package com.jquartz.rich.validation.core.verification;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public class VerificationLogicFactory {

    private final VerificationAnnotationProcessor annotationProcessor = new VerificationAnnotationProcessor();

    public VerificationLogic basedOn(@Nonnull Class<?> subjectClass) {
        return annotationProcessor.process(subjectClass);
    }
}