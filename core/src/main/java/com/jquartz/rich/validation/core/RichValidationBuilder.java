package com.jquartz.rich.validation.core;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.jquartz.rich.validation.core.dependency.tree.DependencyTree;
import com.jquartz.rich.validation.core.dependency.tree.node.DependencyTreeNode;
import com.jquartz.rich.validation.core.rule.ClassField;
import com.jquartz.rich.validation.core.rule.Rule;
import com.jquartz.rich.validation.core.rule.exception.CircularDependencyFoundException;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class RichValidationBuilder {

    private Multimap<Class<?>, Rule<?>> classToRules = HashMultimap.create();
    private Multimap<ClassField<?, ?>, Rule<?>> fieldToRules = HashMultimap.create();

    public RichValidationBuilder submit(@Nonnull Rule<?> rule) {
        classToRules.put(rule.getTarget().getSourceClass(), rule);
        fieldToRules.put(rule.getTarget(), rule);

        return this;
    }

    public RichValidation build() {
        Multimap<Class<?>, DependencyTree> dependencyTrees = HashMultimap.create();

        for (Map.Entry<Class<?>, Rule<?>> entry : classToRules.entries()) {
            Class<?> targetClass = entry.getKey();
            Rule<?> targetRule = entry.getValue();

            DependencyTreeNode<?> rootNode = createTreeStructure(targetRule);
            DependencyTree<?> dependencyTree = new DependencyTree<>(rootNode);

            dependencyTrees.put(targetClass, dependencyTree);
        }

        return new RichValidation(dependencyTrees);
    }

    private <T> DependencyTreeNode<T> createTreeStructure(Rule<T> targetRule) {
        DependencyTreeNode<T> rootNode = new DependencyTreeNode<>(0, null, targetRule.getTarget(), targetRule);
        fillDependencyTree(1, rootNode, targetRule.getAccomplices());

        return rootNode;
    }

    private <T> void fillDependencyTree(int level, DependencyTreeNode<T> parentNode, Collection<? extends ClassField<?, ?>> accomplices) {
        for (ClassField<?, ?> accomplice : accomplices) {
            Collection<Rule<?>> rules = getRules(accomplice);

            for (Rule<?> rule : rules) {
                DependencyTreeNode<T> newNode = createNewNode(level, parentNode, accomplice, rule);
                Optional<DependencyTreeNode<T>> cycledNode = parentNode.findCycledNode(newNode);
                if (cycledNode.isPresent()) {
                    throw new CircularDependencyFoundException(cycledNode.get(), newNode);
                }

                parentNode.addChild(newNode);

                Collection<? extends ClassField<?, ?>> targetRuleAccomplices = rule.getAccomplices();
                fillDependencyTree(level + 1, newNode, targetRuleAccomplices);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> DependencyTreeNode<T> createNewNode(int level, DependencyTreeNode<T> parentNode, ClassField<?, ?> accomplice, Rule<?> rule) {
        return (DependencyTreeNode<T>) new DependencyTreeNode(level, parentNode, accomplice, rule);
    }

    private <T> Collection<Rule<?>> getRules(ClassField<?, T> accomplice) {
        return fieldToRules.get(accomplice);
    }
}