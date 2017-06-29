package com.jquartz.rich.validation.core.rule.evaluation;

import com.google.common.collect.Multimap;
import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.Collection;

import static com.jquartz.rich.validation.core.evaluation.TruthValue.*;
import static java.util.Collections.unmodifiableCollection;

/**
 * @author Dmitriy Kotov
 */
public class ValidationResult {

    private final Multimap<TruthValue, ClassField<?, ?>> results;

    public ValidationResult(Multimap<TruthValue, ClassField<?, ?>> results) {
        this.results = results;
    }

    public Collection<ClassField<?, ?>> getPassedFields() {
        return getFieldsWithResult(TRUE);
    }

    public Collection<ClassField<?, ?>> getFailedFields() {
        return getFieldsWithResult(FALSE);
    }

    public Collection<ClassField<?, ?>> getUnknownFields() {
        return getFieldsWithResult(UNKNOWN);
    }

    public Collection<ClassField<?, ?>> getFieldsWithResult(TruthValue aFalse) {
        return unmodifiableCollection(results.get(aFalse));
    }

    public boolean hasFailsOrUnknowns() {
        return results.containsKey(TruthValue.UNKNOWN) || results.containsKey(TruthValue.FALSE);
    }
}