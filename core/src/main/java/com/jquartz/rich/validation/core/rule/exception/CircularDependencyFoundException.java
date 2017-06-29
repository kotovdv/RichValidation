package com.jquartz.rich.validation.core.rule.exception;

import com.jquartz.rich.validation.core.dependency.tree.node.DependencyTreeNode;
import com.jquartz.rich.validation.core.exception.RichValidationException;

import java.util.Collections;
import java.util.LinkedList;

/**
 * @author Dmitriy Kotov
 */
public class CircularDependencyFoundException extends RichValidationException {

    public CircularDependencyFoundException(DependencyTreeNode<?> parentCycledNode, DependencyTreeNode<?> childCycledNode) {
        super(createMessage(parentCycledNode, childCycledNode));
    }

    private static String createMessage(DependencyTreeNode<?> parentCycledNode, DependencyTreeNode<?> childCycledNode) {
        LinkedList<String> messages = createMessages(parentCycledNode, childCycledNode);

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("\n");
        for (int i = 0; i < messages.size(); i++) {
            messageBuilder.append(createTabs(i)).append(messages.get(i)).append("\n");
        }

        return messageBuilder.toString();
    }

    private static LinkedList<String> createMessages(DependencyTreeNode<?> parentCycledNode, DependencyTreeNode<?> childCycledNode) {
        LinkedList<String> messages = new LinkedList<>();
        DependencyTreeNode<?> currentChildNode = childCycledNode;

        do {
            messages.addFirst(createMessage(currentChildNode));

            currentChildNode = currentChildNode.getParentNode();
        } while (!parentCycledNode.equals(currentChildNode));
        messages.addFirst(createMessage(parentCycledNode));

        return messages;
    }

    private static String createMessage(DependencyTreeNode<?> node) {
        return String.format("Level [%d] Field [%s] Rule [%s]", node.getLevel(), node.getTargetField(), node.getRule());
    }

    private static String createTabs(int i) {
        return String.join("", Collections.nCopies(i, "\t"));
    }
}