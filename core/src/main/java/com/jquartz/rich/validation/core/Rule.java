package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.Collection;

/**
 * @author Dmitriy Kotov
 */
public class Rule<T> {

  private final Expression<T> expression;
  private ClassField<T> target;

  public Rule(ClassField<T> target, Expression<T> expression) {
    this.target = target;
    this.expression = expression;
  }

  public TruthValue validate(T instance) {
    return expression.apply(instance);
  }

  public String getTextualRepresentation() {
    return expression.getTextualRepresentation();
  }

  public ClassField<T> getTarget() {
    return target;
  }

  public Collection<ClassField<T>> getAccomplices() {
    Collection<ClassField<T>> accomplices = expression.getAccomplices();
    accomplices.remove(target);

    return accomplices;
  }


  @Override
  public String toString() {
    return getTextualRepresentation();
  }
}