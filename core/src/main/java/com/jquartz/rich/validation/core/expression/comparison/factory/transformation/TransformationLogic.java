package com.jquartz.rich.validation.core.expression.comparison.factory.transformation;

public class TransformationLogic<T> {
    private final Transformation<T> leftArgumentTransformation;
    private final Transformation<T> rightArgumentTransformation;

    public TransformationLogic(Transformation<T> leftArgumentTransformation, Transformation<T> rightArgumentTransformation) {
        this.leftArgumentTransformation = leftArgumentTransformation;
        this.rightArgumentTransformation = rightArgumentTransformation;
    }

    public Transformation<T> getLeftTransformation() {
        return leftArgumentTransformation;
    }

    public Transformation<T> getRightTransformation() {
        return rightArgumentTransformation;
    }
}