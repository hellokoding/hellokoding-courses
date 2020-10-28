package com.hellokoding.datastructure;

public class MyDoublyLinkedList {
    Node head;
    Node tail;

    public void addFirst(int data) {
        Node newNode = new Node(null, data, this.head);
        this.head = newNode;
        if(this.tail == null) this.tail = this.head;
    }

    public void addLast(int data) {
        Node newNode = new Node(this.tail, data, null);
        this.tail = newNode;
        if(this.head == null) this.head = this.tail;
    }

    public void removeFirst() {
        if (this.head == null) return;

        this.head = this.head.next;
        if (this.head == null) this.tail = null;
        this.head.previous = null;
    }

    public void removeLast() {
        if (this.head == null) return;

        if (this.head.next == null) {
            this.head = null;
            this.tail = null;
            return;
        }

        Node beforeTail = this.tail.previous;
        beforeTail.next = null;
        this.tail = beforeTail;
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
        MyDoublyLinkedList myLinkedList = new MyDoublyLinkedList();
        myLinkedList.addLast(2);
        myLinkedList.addLast(6);
        myLinkedList.addFirst(9);
        myLinkedList.traversal();

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
        public Node previous;
        public Node next;

        public Node(Node previous, int data, Node next) {
            this.previous = previous;
            if (previous != null) previous.next = this;

            this.data = data;

            this.next = next;
            if (next != null) next.previous = this;
        }
    }
}
