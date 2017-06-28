package com.jquartz.rich.validation.core.rule;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.jquartz.rich.validation.core.Rule;
import com.jquartz.rich.validation.core.rule.leaf.Node;

import javax.annotation.Nonnull;
import java.util.Collection;

public class RuleDictionaryBuilder {

    private Multimap<Class<?>, Rule<?>> classToRules = HashMultimap.create();
    private Multimap<ClassField<?>, Rule<?>> fieldToRules = HashMultimap.create();

    public RuleDictionaryBuilder submit(@Nonnull Rule<?> rule) {
        classToRules.put(rule.getTarget().getOwnerClass(), rule);
        fieldToRules.put(rule.getTarget(), rule);

        return this;
    }

    public RuleDictionary build() {
        for (Rule<?> rule : classToRules.values()) {

            //TODO should only accept rule itself probably
            //Or rule should have no info about the target field itself
            Node targetRootNode = new Node(null, rule.getTarget(), rule);

            fillDependencyTree(targetRootNode, rule.getAccomplices());
        }

        return new RuleDictionary(null);
    }

    //TODO DONT FORGET ABOUT CYCLED CASES
    //TODO REMOVE TARGET ACCOMPLICE FROM DIS
    private void fillDependencyTree(Node parentNode,
                                    Collection<? extends ClassField<?>> accomplices) {

        for (ClassField<?> accomplice : accomplices) {
            Collection<Rule<?>> rules = fieldToRules.get(accomplice);

            for (Rule<?> rule : rules) {
                Node newNode = new Node(parentNode, accomplice, rule);
                fillDependencyTree(newNode, rule.getAccomplices());

                parentNode.addChild(newNode);
            }
        }
    }
}