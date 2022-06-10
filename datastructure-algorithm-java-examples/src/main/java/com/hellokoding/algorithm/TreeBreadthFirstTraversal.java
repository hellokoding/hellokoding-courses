package com.hellokoding.algorithm;

import com.hellokoding.datastructure.BSTByLinkedList;
import java.util.*;

public class TreeBreadthFirstTraversal {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> levelOrderTraversal(BSTByLinkedList.Node root) {
        if (root == null) return res;

        Deque<BSTByLinkedList.Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();

            int size = queue.size();
            for (int i=0; i<size; i++) {
                root = queue.remove(); level.add(root.data);
                if (root.left != null) queue.add(root.left);
                if (root.right != null) queue.add(root.right);
            }

            res.add(level);
        }

        return res;
    }

    public static void main(String[] args) {
        BSTByLinkedList tree = new BSTByLinkedList();
        tree.insert(7);
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);
        tree.insert(9);
        List<List<Integer>> result = new TreeBreadthFirstTraversal().levelOrderTraversal(tree.root);
        System.out.println(Arrays.deepToString(result.toArray()));
    }
}
