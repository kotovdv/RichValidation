package com.jquartz.rich.validation.core.dependency.tree.node;

import com.jquartz.rich.validation.core.rule.Rule;
import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.TrustworthinessContainer;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * @author Dmitriy Kotov
 */
public class DependencyTreeNode<T> {

    private final int level;
    private final DependencyTreeNode<T> parentNode;
    private final Rule<T> rule;
    private final ClassField<?, T> targetField;

    private final Collection<DependencyTreeNode<T>> children = new ArrayList<>();

    public DependencyTreeNode(int level, DependencyTreeNode<T> parentNode, ClassField<?, T> targetField, Rule<T> rule) {
        this.level = level;
        this.parentNode = parentNode;
        this.targetField = targetField;
        this.rule = rule;
    }

    public TruthValue validate(T instance, TrustworthinessContainer trustworthiness) {
        for (DependencyTreeNode<T> child : children) {
            child.validate(instance, trustworthiness);
        }

        TruthValue validationResult = rule.validate(instance, trustworthiness);
        trustworthiness.associateValidationResult(targetField, validationResult);

        return validationResult;
    }


    public DependencyTreeNode<T> getParentNode() {
        return parentNode;
    }

    public void addChild(DependencyTreeNode<T> node) {
        this.children.add(node);
    }

    public void removeChildren() {
        this.children.clear();
    }

    public Optional<DependencyTreeNode<T>> findCycledNode(DependencyTreeNode<T> suspect) {
        return findCycledNode(this, suspect);
    }

    public ClassField<?, T> getTargetField() {
        return targetField;
    }

    public int getLevel() {
        return level;
    }

    public Rule<T> getRule() {
        return rule;
    }

    /**
     * @return true if had any cycled case removed/ false otherwise
     */
    private Optional<DependencyTreeNode<T>> findCycledNode(DependencyTreeNode<T> parentNode, DependencyTreeNode<T> suspect) {
        if (parentNode == null) {
            return Optional.empty();
        }

        if (parentNode.equals(suspect)) {
            return Optional.of(parentNode);
        } else {
            return findCycledNode(parentNode.getParentNode(), suspect);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DependencyTreeNode<?> that = (DependencyTreeNode<?>) o;

        if (!rule.equals(that.rule)) return false;
        return targetField.equals(that.targetField);
    }

    @Override
    public int hashCode() {
        int result = rule.hashCode();
        result = 31 * result + targetField.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DependencyTreeNode{" +
                "rule=" + rule +
                ", targetField=" + targetField +
                '}';
    }
}