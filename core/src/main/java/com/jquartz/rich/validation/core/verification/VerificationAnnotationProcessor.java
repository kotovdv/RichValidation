package com.jquartz.rich.validation.core.verification;

import com.jquartz.rich.validation.core.annotations.Verify;

/**
 * @author Dmitriy Kotov
 */
public class VerificationAnnotationProcessor {

    public static void main(String[] args) {
        new VerificationAnnotationProcessor().process(VerificationSubject.class);
    }

    public void process(Class<?> subjectClass) {
        Verify[] declaredAnnotationsByType = subjectClass.getDeclaredAnnotationsByType(Verify.class);

        for (Verify verify : declaredAnnotationsByType) {
            System.out.println(verify.value());
        }
    }
}