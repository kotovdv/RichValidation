package com.jquartz.rich.validation.core.rule;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.jquartz.rich.validation.core.dependency.tree.DependencyTree;
import com.jquartz.rich.validation.core.evaluation.TruthValue;

import java.util.Collection;

import static com.jquartz.rich.validation.core.util.Assertions.assertNotNull;

public class RuleDictionary {

    private Multimap<Class<?>, DependencyTree> dependencyTrees = HashMultimap.create();

    RuleDictionary(Multimap<Class<?>, DependencyTree> dependencyTrees) {
        this.dependencyTrees = dependencyTrees;
    }

    public static RuleDictionaryBuilder builder() {
        return new RuleDictionaryBuilder();
    }

    public void validate(Object object) {
        assertNotNull(object, "Unable to validate null object");

        Class<?> objectClass = object.getClass();
        Collection<DependencyTree> dependencyTrees = this.dependencyTrees.get(objectClass);

        for (DependencyTree dependencyTree : dependencyTrees) {
            TruthValue validationResult = dependencyTree.validate(object);
            System.out.println(dependencyTree.getTarget() + " = " + validationResult);
        }
    }
}