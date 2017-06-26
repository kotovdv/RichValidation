package com.jquartz.rich.validation.core.expression.base.binary.action.equality;

import com.jquartz.rich.validation.core.api.textual.Tokens;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * @author Dmitriy Kotov
 */
public class AreNotEqualAction extends EqualityAction {

    @Override
    protected boolean applyEqualityCheck(@Nullable Object left, @Nullable Object right) {
        return !Objects.equals(left, right);
    }

    @Override
    public String getTextualRepresentation() {
        return Tokens.IS_NOT_EQUAL_TO.toString();
    }
}