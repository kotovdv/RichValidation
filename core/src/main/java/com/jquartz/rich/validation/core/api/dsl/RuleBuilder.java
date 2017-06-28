package com.jquartz.rich.validation.core.api.dsl;

import com.jquartz.rich.validation.core.api.dsl.is.TargetIsPartBuilder;
import com.jquartz.rich.validation.core.api.dsl.logic.RuleLogicBuilder;
import com.jquartz.rich.validation.core.pointer.field.FieldPointer;
import com.jquartz.rich.validation.core.pointer.field.FieldPointerFactory;

import javax.annotation.Nonnull;

public class RuleBuilder<T> {

    private final Class<T> sourceClass;
    private final FieldPointerFactory fieldPointerFactory = new FieldPointerFactory();

    private RuleBuilder(@Nonnull Class<T> sourceClass) {
        this.sourceClass = sourceClass;
    }

    public static <T> RuleBuilder<T> ensureThat(@Nonnull Class<T> sourceClass) {
        return new RuleBuilder<>(sourceClass);
    }

    public TargetIsPartBuilder<T> field(@Nonnull String fieldName) {
        FieldPointer<?, T> fieldPointer = fieldPointerFactory.create(sourceClass, fieldName);
        RuleLogicBuilder<T> builder = new RuleLogicBuilder<>(fieldPointer.getTarget());

        return new TargetIsPartBuilder<>(builder);
    }
}