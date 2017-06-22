package com.jquartz.rich.validation.core.evaluation;

import com.google.common.collect.ImmutableMap;
import com.jquartz.rich.validation.core.evaluation.exception.CannotApplyBinaryOperator;
import com.jquartz.rich.validation.core.util.UnorderedPair;

import javax.annotation.Nonnull;
import java.util.Map;

import static com.jquartz.rich.validation.core.evaluation.TruthValue.*;
import static com.jquartz.rich.validation.core.util.UnorderedPair.unorderedPairOf;

public enum TruthValueBinaryOperator {
    AND {
        private Map<UnorderedPair<TruthValue>, TruthValue> logic = ImmutableMap.<UnorderedPair<TruthValue>, TruthValue>builder()
                .put(unorderedPairOf(TRUE, TRUE), TRUE)
                .put(unorderedPairOf(FALSE, FALSE), FALSE)
                .put(unorderedPairOf(TRUE, FALSE), FALSE)
                .put(unorderedPairOf(TRUE, UNKNOWN), UNKNOWN)
                .put(unorderedPairOf(FALSE, UNKNOWN), UNKNOWN)
                .put(unorderedPairOf(UNKNOWN, UNKNOWN), UNKNOWN)
                .build();

        @Override
        protected Map<UnorderedPair<TruthValue>, TruthValue> getLogic() {
            return logic;
        }

    },
    OR {
        private Map<UnorderedPair<TruthValue>, TruthValue> logic = ImmutableMap.<UnorderedPair<TruthValue>, TruthValue>builder()
                .put(unorderedPairOf(TRUE, TRUE), TRUE)
                .put(unorderedPairOf(FALSE, FALSE), FALSE)
                .put(unorderedPairOf(TRUE, FALSE), TRUE)
                .put(unorderedPairOf(TRUE, UNKNOWN), TRUE)
                .put(unorderedPairOf(FALSE, UNKNOWN), UNKNOWN)
                .put(unorderedPairOf(UNKNOWN, UNKNOWN), UNKNOWN)
                .build();


        @Override
        protected Map<UnorderedPair<TruthValue>, TruthValue> getLogic() {
            return logic;
        }
    };


    public TruthValue apply(@Nonnull TruthValue left, @Nonnull TruthValue right) {
        UnorderedPair<TruthValue> unorderedPair = unorderedPairOf(left, right);
        TruthValue resultingValue = getLogic().get(unorderedPair);
        if (resultingValue == null) {
            throw new CannotApplyBinaryOperator(left, right, this);
        }

        return resultingValue;
    }

    protected abstract Map<UnorderedPair<TruthValue>, TruthValue> getLogic();
}