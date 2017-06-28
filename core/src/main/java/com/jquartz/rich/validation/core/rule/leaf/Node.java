package com.jquartz.rich.validation.core.rule.leaf;

import com.jquartz.rich.validation.core.Rule;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.ArrayList;
import java.util.Collection;

public class Node {

    private final Node parentNode;
    private final ClassField<?> targetField;
    private final Rule rule;

    private final Collection<Node> children = new ArrayList<>();

    public Node(Node parentNode,
                ClassField<?> targetField,
                Rule rule) {
        this.parentNode = parentNode;
        this.targetField = targetField;
        this.rule = rule;
    }

    public void addChild(Node node) {
        this.children.add(node);
    }

    public Node getParentNode() {
        return parentNode;
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