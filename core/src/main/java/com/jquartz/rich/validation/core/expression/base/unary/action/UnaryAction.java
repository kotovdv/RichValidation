package com.jquartz.rich.validation.core.expression.base.unary.action;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.base.Action;

public interface UnaryAction extends Action {

    <V> TruthValue apply(V value);
}
