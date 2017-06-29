package com.jquartz.rich.validation.core.dependency.tree;

import com.jquartz.rich.validation.core.dependency.tree.node.DependencyTreeNode;
import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.TrustworthinessContainer;
import com.jquartz.rich.validation.core.rule.ClassField;

public class DependencyTree<T> {

    private final DependencyTreeNode<T> rootNode;

    public DependencyTree(DependencyTreeNode<T> rootNode) {
        this.rootNode = rootNode;
    }

    public TruthValue validate(T source) {
        return rootNode.validate(source, new TrustworthinessContainer());
    }

    public ClassField<?, T> getTarget() {
        return rootNode.getTargetField();
    }
}
