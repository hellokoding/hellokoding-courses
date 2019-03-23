package com.hellokoding.algorithm;

import com.hellokoding.datastructure.BSTByLinkedList;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeDepthFirstTraversalIterably extends BSTByLinkedList {
    public void preTraversal(Node node) {
        if (node == null) return;

        Deque<Node> stack = new ArrayDeque<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            System.out.println(currentNode);

            if (currentNode.right != null)
                stack.push(currentNode.right);

            if (currentNode.left != null)
                stack.push(currentNode.left);
        }
    }

    public void inTraversal(Node node) {
        Deque<Node> stack = new ArrayDeque<>();

        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.println(node);

            if (node.right != null)
                stack.push(node.right);
        }
    }

    public void postTraversal(Node node) {
        Deque<Node> stack = new ArrayDeque<>();

        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        Node lastVisitedNode = null;
        while (!stack.isEmpty()) {
            node = stack.peek();

            if (node.right != null && node.right != lastVisitedNode) {
                stack.push(node.right);
            } else {
                System.out.println(node);
                lastVisitedNode = stack.pop();
            }
        }
    }

    public static void main(String[] args) {
        TreeDepthFirstTraversalIterably tree = new TreeDepthFirstTraversalIterably();
        tree.insert(7);
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);
        tree.insert(9);

        System.out.println("Pre-order Traversal");
        tree.preTraversal(tree.root);
        System.out.println();

        System.out.println("In-order Traversal");
        tree.inTraversal(tree.root);
        System.out.println();

        System.out.println("Post-order Traversal");
        tree.postTraversal(tree.root);
    }
}
