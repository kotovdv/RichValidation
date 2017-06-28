package com.jquartz.rich.validation.core.dependency.tree.node;

import com.jquartz.rich.validation.core.Rule;
import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.TrustworthinessContainer;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.ArrayList;
import java.util.Collection;

public class Node<T> {

    private final Node<T> parentNode;
    private final Rule<T> rule;
    private final ClassField<?, T> targetField;

    private final Collection<Node<T>> children = new ArrayList<>();

    public Node(Node<T> parentNode, ClassField<?, T> targetField, Rule<T> rule) {
        this.parentNode = parentNode;
        this.targetField = targetField;
        this.rule = rule;
    }

    public TruthValue validate(T instance, TrustworthinessContainer trustworthiness) {
        for (Node<T> child : children) {
            child.validate(instance, trustworthiness);
        }

        TruthValue validationResult = rule.validate(instance, trustworthiness);
        trustworthiness.associateValidationResult(targetField, validationResult);

        return validationResult;
    }


    public Node<T> getParentNode() {
        return parentNode;
    }

    public void addChild(Node<T> node) {
        this.children.add(node);
    }

    public void removeChildren() {
        this.children.clear();
    }

    public boolean removeCycledCase(Node<T> suspect) {
        return removeCycledCase(this, suspect);
    }

    public ClassField<?, T> getTargetField() {
        return targetField;
    }

    /**
     * @return true if had any cycled case removed/ false otherwise
     */
    private boolean removeCycledCase(Node<T> parentNode, Node<T> suspect) {
        if (parentNode == null) {
            return false;
        }

        if (parentNode.equals(suspect)) {
            parentNode.removeChildren();

            return true;
        } else {
            return removeCycledCase(parentNode.getParentNode(), suspect);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node)) {
            return false;
        }

        Node node = (Node) o;

        if (!targetField.equals(node.targetField)) {
            return false;
        }
        return rule.equals(node.rule);
    }

    @Override
    public int hashCode() {
        int result = targetField.hashCode();
        result = 31 * result + rule.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Node{" +
                "targetField=" + targetField +
                ", rule=" + rule +
                '}';
    }
}