package com.hellokoding.datastructure;

public class PriorityQueueByHeap {
    private MaxHeapByArray maxHeapByArray;

    public PriorityQueueByHeap(int capacity) {
        this.maxHeapByArray = new MaxHeapByArray(capacity);
    }

    public void enqueue(int x) {
        maxHeapByArray.push(x);
    }

    public int dequeue() {
        return maxHeapByArray.pop();
    }

    public int front() {
        return maxHeapByArray.peek();
    }

    public static void main(String[] args) {
        PriorityQueueByHeap myQueue = new PriorityQueueByHeap(5);
        myQueue.enqueue(7);
        myQueue.enqueue(2);
        myQueue.enqueue(3);
        myQueue.enqueue(1);
        myQueue.enqueue(9);

        System.out.println(myQueue.front()); // 9
        System.out.println(myQueue.dequeue()); // 9
        System.out.println(myQueue.front()); // 7
    }
}
