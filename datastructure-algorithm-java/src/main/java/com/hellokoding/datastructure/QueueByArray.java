package com.hellokoding.datastructure;

import java.util.NoSuchElementException;

public class QueueByArray {
    private int[] queue;
    private int front;
    private int rear;

    public QueueByArray(int capacity) {
        this.queue = new int[capacity];
        this.front = 0;
        this.rear = 0;
    }

    public void enqueue(int x) {
        if (isFull()) {
            throw new IllegalStateException();
        }

        queue[rear++] = x;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return queue[front++];
    }

    public int front() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return queue[front];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return rear == queue.length;
    }

    public int size() {
        return rear - front;
    }

    public static void main(String[] args) {
        QueueByArray myQueue = new QueueByArray(3);
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        myQueue.enqueue(3);

        System.out.println(myQueue.front()); // 1
        System.out.println(myQueue.dequeue()); // 1
        System.out.println(myQueue.front()); // 2

        System.out.println(myQueue.size()); // 2
        System.out.println(myQueue.isEmpty()); // false
        System.out.println(myQueue.isFull()); // false
    }
}
