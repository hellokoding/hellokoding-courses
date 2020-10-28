package com.hellokoding.datastructure;

public class MyLinkedList {
    Node head;

    public void addFirst(int data) {
        if (head == null) {
            head = new Node(data);
            return;
        }

        Node newNode = new Node(data);
        newNode.next = this.head;

        this.head = newNode;
    }

    public void addLast(int data) {
        if (head == null) {
            head = new Node(data);
            return;
        }

        Node currentNode = this.head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }

        currentNode.next = new Node(data);
    }

    public void removeFirst() {
        if (this.head == null) return;

        this.head = this.head.next;
    }

    public void removeLast() {
        if (this.head == null) return;

        if (this.head.next == null) {
            this.head = null;
            return;
        }

        Node currentNode = this.head;
        while (currentNode.next.next != null) {
            currentNode = currentNode.next;
        }

        currentNode.next = null;
    }

    public void traversal() {
        Node currentNode = this.head;
        while (currentNode != null) {
            System.out.printf("%d ", currentNode.data);
            currentNode = currentNode.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addLast(2);
        myLinkedList.addLast(6);
        myLinkedList.addFirst(9);

        System.out.println("Traverse all elements");
        myLinkedList.traversal();

        System.out.println("Remove first node");
        myLinkedList.removeFirst();
        myLinkedList.traversal();

        System.out.println("Remove last node");
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
