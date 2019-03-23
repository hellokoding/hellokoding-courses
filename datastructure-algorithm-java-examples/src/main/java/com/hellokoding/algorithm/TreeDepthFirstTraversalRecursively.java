package com.hellokoding.algorithm;

import com.hellokoding.datastructure.BSTByLinkedList;

public class TreeDepthFirstTraversalRecursively extends BSTByLinkedList {
    public void preTraversal(Node node) {
        if (node == null) return;

        System.out.println(node);
        preTraversal(node.left);
        preTraversal(node.right);
    }

    public void inTraversal(Node node) {
        if (node == null) return;

        inTraversal(node.left);
        System.out.println(node);
        inTraversal(node.right);
    }

    public void postTraversal(Node node) {
        if (node == null) return;

        postTraversal(node.left);
        postTraversal(node.right);
        System.out.println(node);
    }

    public static void main(String[] args) {
        TreeDepthFirstTraversalRecursively tree = new TreeDepthFirstTraversalRecursively();
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
