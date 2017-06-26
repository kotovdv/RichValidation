package com.jquartz.rich.validation.core.expression.comparison.factory.transformation;

public class TransformationLogic<T> {
    private final Transformation<T> leftArgumentTransformation;
    private final Transformation<T> rightArgumentTransformation;
    private final Class<T> resultingClass;

    public TransformationLogic(Transformation<T> left,
                               Transformation<T> right,
                               Class<T> resultingClass) {
        this.leftArgumentTransformation = left;
        this.rightArgumentTransformation = right;
        this.resultingClass = resultingClass;
    }

    public Transformation<T> getLeftTransformation() {
        return leftArgumentTransformation;
    }

    public Transformation<T> getRightTransformation() {
        return rightArgumentTransformation;
    }

    public Class<T> getResultingClass() {
        return resultingClass;
    }
}