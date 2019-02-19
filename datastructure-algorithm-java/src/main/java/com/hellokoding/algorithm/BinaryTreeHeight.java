package com.hellokoding.algorithm;

import com.hellokoding.datastructure.BSTByLinkedList;

public class BinaryTreeHeight extends BSTByLinkedList {
    public int height(Node node) {
        if (node == null) return -1;

        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public static void main(String[] args) {
        BinaryTreeHeight tree = new BinaryTreeHeight();
        tree.insert(7);
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);
        tree.insert(9);

        tree.inTraversal(tree.root);
        System.out.println(tree.height(tree.root));
    }
}
