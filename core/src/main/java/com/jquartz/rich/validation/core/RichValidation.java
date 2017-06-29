package com.jquartz.rich.validation.core;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.jquartz.rich.validation.core.dependency.tree.DependencyTree;
import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.rule.ClassField;
import com.jquartz.rich.validation.core.rule.evaluation.ValidationResult;
import com.jquartz.rich.validation.core.rule.exception.ValidationException;

import java.util.Collection;

import static com.jquartz.rich.validation.core.util.Assertions.assertNotNull;

public class RichValidation {

    private Multimap<Class<?>, DependencyTree> dependencyTrees = HashMultimap.create();

    RichValidation(Multimap<Class<?>, DependencyTree> dependencyTrees) {
        this.dependencyTrees = dependencyTrees;
    }

    public static RichValidationBuilder builder() {
        return new RichValidationBuilder();
    }

    public ValidationResult validateSilently(Object object) {
        assertNotNull(object, "Unable to validate null object");

        return new ValidationResult(getResults(object));
    }

    public void validate(Object object) {
        ValidationResult validationResult = validateSilently(object);

        if (validationResult.hasFailsOrUnknowns()) {
            throw new ValidationException(validationResult);
        }
    }

    private Multimap<TruthValue, ClassField<?, ?>> getResults(Object object) {
        Multimap<TruthValue, ClassField<?, ?>> results = HashMultimap.create();
        for (DependencyTree dependencyTree : getDependencyTrees(object)) {
            TruthValue validationResult = validate(object, dependencyTree);

            results.put(validationResult, dependencyTree.getTarget());
        }

        return results;
    }

    @SuppressWarnings("unchecked")
    private TruthValue validate(Object object, DependencyTree dependencyTree) {
        return dependencyTree.validate(object);
    }

    private Collection<DependencyTree> getDependencyTrees(Object object) {
        return this.dependencyTrees.get(object.getClass());
    }
}