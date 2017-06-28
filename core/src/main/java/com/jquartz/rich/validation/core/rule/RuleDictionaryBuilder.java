package com.jquartz.rich.validation.core.rule;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.jquartz.rich.validation.core.Rule;
import com.jquartz.rich.validation.core.dependency.tree.DependencyTree;
import com.jquartz.rich.validation.core.dependency.tree.node.Node;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;

public class RuleDictionaryBuilder {

    private Multimap<Class<?>, Rule<?>> classToRules = HashMultimap.create();
    private Multimap<ClassField<?, ?>, Rule<?>> fieldToRules = HashMultimap.create();

    public RuleDictionaryBuilder submit(@Nonnull Rule<?> rule) {
        classToRules.put(rule.getTarget().getSourceClass(), rule);
        fieldToRules.put(rule.getTarget(), rule);

        return this;
    }

    public RuleDictionary build() {
        Multimap<Class<?>, DependencyTree> dependencyTrees = HashMultimap.create();

        for (Map.Entry<Class<?>, Rule<?>> entry : classToRules.entries()) {
            Class<?> targetClass = entry.getKey();
            Rule<?> targetRule = entry.getValue();

            Node<?> rootNode = createTreeStructure(targetRule);
            DependencyTree<?> dependencyTree = new DependencyTree<>(rootNode);

            dependencyTrees.put(targetClass, dependencyTree);
        }

        return new RuleDictionary(dependencyTrees);
    }

    private <T> Node<T> createTreeStructure(Rule<T> targetRule) {
        Node<T> rootNode = new Node<>(null, targetRule.getTarget(), targetRule);
        fillDependencyTree(rootNode, targetRule.getAccomplices());

        return rootNode;
    }

    private <T> void fillDependencyTree(Node<T> parentNode, Collection<? extends ClassField<?, ?>> accomplices) {
        for (ClassField<?, ?> accomplice : accomplices) {
            Collection<Rule<?>> rules = getRules(accomplice);

            for (Rule<?> rule : rules) {
                Node<T> newNode = createNewNode(parentNode, accomplice, rule);
                boolean hadCycledCase = parentNode.removeCycledCase(newNode);
                if (hadCycledCase) {
                    continue;
                }

                parentNode.addChild(newNode);

                Collection<? extends ClassField<?, ?>> targetRuleAccomplices = rule.getAccomplices();
                fillDependencyTree(newNode, targetRuleAccomplices);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Node<T> createNewNode(Node<T> parentNode, ClassField<?, ?> accomplice, Rule<?> rule) {
        return (Node<T>) new Node(parentNode, accomplice, rule);
    }

    private <T> Collection<Rule<?>> getRules(ClassField<?, T> accomplice) {
        return fieldToRules.get(accomplice);
    }
}