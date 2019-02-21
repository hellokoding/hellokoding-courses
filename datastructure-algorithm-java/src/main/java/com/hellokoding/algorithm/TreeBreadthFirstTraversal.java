package com.hellokoding.algorithm;

import com.hellokoding.datastructure.BSTByLinkedList;

import java.util.ArrayDeque;
import java.util.Queue;

public class TreeBreadthFirstTraversal extends BSTByLinkedList {
    void breadthFirstTraversal(Node root) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            System.out.println(currentNode);

            if (currentNode.left != null)
                queue.offer(currentNode.left);

            if (currentNode.right != null)
                queue.offer(currentNode.right);
        }
    }

    public static void main(String[] args) {
        TreeBreadthFirstTraversal tree = new TreeBreadthFirstTraversal();
        tree.insert(7);
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);
        tree.insert(9);

        tree.breadthFirstTraversal(tree.root);
    }
}
