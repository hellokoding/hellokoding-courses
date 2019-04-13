package com.hellokoding.datastructure;

public class AVLTree {
    public Node root;

    private int findHeight(Node node) {
        return node == null ? -1 : node.height;
    }

    private int findBalanceFactor(Node node) {
        return node == null ? 0 : (findHeight(node.left) - findHeight(node.right));
    }

    private Node rotateRight(Node node) {
        Node pivot = node.left;
        node.left = pivot.right;
        pivot.right = node;

        node.height = 1 + Math.max(findHeight(node.left), findHeight(node.right));
        pivot.height = 1 + Math.max(findHeight(pivot.left), findHeight(pivot.right));

        return pivot;
    }

    private Node rotateLeft(Node node) {
        Node pivot = node.right;
        node.right = pivot.left;
        pivot.left = node;

        node.height = 1 + Math.max(findHeight(node.left), findHeight(node.right));
        pivot.height = 1 + Math.max(findHeight(pivot.left), findHeight(pivot.right));

        return pivot;
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key)  {
            node.right = insert(node.right, key);
        } else {
            return node;
        }

        node.height = 1 + Math.max(findHeight(node.left), findHeight(node.right));

        int balanceFactor = findBalanceFactor(node);
        if (balanceFactor > 1 && key < node.left.key) {
            return rotateRight(node);
        }

        if (balanceFactor < - 1 && key > node.right.key) {
            return rotateLeft(node);
        }

        if (balanceFactor > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balanceFactor < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void insert(int data) {
        this.root = insert(this.root, data);
    }

    public void inTraversal(Node node) {
        if (node == null) return;

        inTraversal(node.left);
        System.out.println(node);
        inTraversal(node.right);
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(7);
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);
        tree.insert(9);

        tree.inTraversal(tree.root);
    }

    public static class Node {
        int key;
        int height;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            this.height = 0;
        }

        @Override
        public String toString() {
            return String.format("key: %d, height: %d, left: %d, right: %d", key, height, (left !=null ? left.key : null), (right != null? right.key : null));
        }
    }
}

