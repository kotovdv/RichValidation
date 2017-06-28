package com.jquartz.rich.validation.core.dependency.tree;

import com.jquartz.rich.validation.core.dependency.tree.node.Node;
import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.TrustworthinessContainer;
import com.jquartz.rich.validation.core.rule.ClassField;

public class DependencyTree<T> {

    private final Node<T> rootNode;

    public DependencyTree(Node<T> rootNode) {
        this.rootNode = rootNode;
    }

    public TruthValue validate(T source) {
        return rootNode.validate(source, new TrustworthinessContainer());
    }

    public ClassField<?, T> getTarget() {
        return rootNode.getTargetField();
    }
}
