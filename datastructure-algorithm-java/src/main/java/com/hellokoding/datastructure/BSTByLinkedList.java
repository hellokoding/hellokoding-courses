package com.hellokoding.datastructure;

public class BSTByLinkedList {
    public Node root;

    private Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data)  {
            node.right = insert(node.right, data);
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
        BSTByLinkedList tree = new BSTByLinkedList();
        tree.insert(7);
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);
        tree.insert(9);

        tree.inTraversal(tree.root);
    }

    public static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.format("data: %d, left: %d, right: %d", data, (left !=null ? left.data : null), (right != null? right.data : null));
        }
    }
}

