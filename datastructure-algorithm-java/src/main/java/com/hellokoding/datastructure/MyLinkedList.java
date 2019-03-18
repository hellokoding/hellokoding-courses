package com.hellokoding.datastructure;

public class MyLinkedList {
    Node head;

    public void addLast(int data) {
        if (head == null) {
            head = new Node(data);
            return;
        }

        Node currentNode = this.head;
        while (currentNode.next != null) currentNode = currentNode.next;
        currentNode.next = new Node(data);
    }

    public void removeLast() {
        Node currentNode = this.head;
        while (currentNode.next.next != null) currentNode = currentNode.next;
        currentNode.next = null;
    }

    public void traversal() {
        Node currentNode = this.head;
        while (currentNode != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addLast(9);
        myLinkedList.addLast(2);
        myLinkedList.addLast(6);

        myLinkedList.traversal();
        myLinkedList.removeLast();
        myLinkedList.traversal();
    }

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }
}
