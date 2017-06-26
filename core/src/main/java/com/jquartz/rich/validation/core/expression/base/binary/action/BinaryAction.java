package com.jquartz.rich.validation.core.expression.base.binary.action;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.base.Action;

public interface BinaryAction extends Action {

    <V> TruthValue apply(V leftOperand, V rightOperand);
}
