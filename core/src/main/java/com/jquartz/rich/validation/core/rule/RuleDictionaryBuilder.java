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
        Multimap<Class<?>, DependencyTree<?>> dependencyTrees = HashMultimap.create();

        for (Map.Entry<Class<?>, Rule<?>> entry : classToRules.entries()) {
            Class<?> targetClass = entry.getKey();
            Rule<?> targetRule = entry.getValue();

            Node<?> rootNode = createTreeStructure(targetRule);
            DependencyTree<?> dependencyTree = new DependencyTree<>(rootNode);

            dependencyTrees.put(targetClass, dependencyTree);
        }

        return new RuleDictionary(dependencyTrees);
    }

    private Node<?> createTreeStructure(Rule<?> targetRule) {
        Node<?> rootNode = new Node(null, targetRule.getTarget(), targetRule);
        fillDependencyTree(rootNode, targetRule.getAccomplices());

        return rootNode;
    }

    private void fillDependencyTree(Node<?> parentNode, Collection<? extends ClassField<?, ?>> accomplices) {
        for (ClassField<?, ?> accomplice : accomplices) {
            Collection<Rule<?>> rules = fieldToRules.get(accomplice);

            for (Rule<?> rule : rules) {
                Node newNode = new Node(parentNode, accomplice, rule);
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
}