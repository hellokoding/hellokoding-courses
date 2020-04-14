package com.hellokoding.datastructure;

public class MinHeapByArray {
    private int[] heap;
    private int size;

    public MinHeapByArray(int capacity) {
        this.heap = new int[capacity+1];
        this.heap[0] = Integer.MIN_VALUE;
        this.size = 0;
    }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void heapifyDown(int k) {
        int smallest = k;
        int leftIndex = 2*k;
        int rightIndex = 2*k + 1;

        if (leftIndex <= heap.length && heap[leftIndex] < heap[smallest]){
            smallest = leftIndex;
        }

        if (rightIndex <= heap.length && heap[rightIndex] < heap[smallest]) {
            smallest = rightIndex;
        }

        if (smallest != k) {
            swap(k, smallest);
            heapifyDown(smallest);
        }
    }

    private void heapifyUp(int k) {
        while (heap[k] < heap[k/2]) {
            swap(k , k/2);
            k = k/2;
        }
    }

    private void print(){
        for (int i = 1; i <= size/2; i++) {
            System.out.printf("Parent: %d, Left child: %d, Right child: %d %s", heap[i], heap[i*2], heap[i*2+1], System.lineSeparator());
        }
    }

    public void push(int x) {
        heap[++size] = x;
        heapifyUp(size);
    }

    public int pop() {
        int head = heap[1];
        heap[1] = heap[size--];
        heapifyDown(1);

        return head;
    }

    public int peek() {
        return heap[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        MinHeapByArray minHeap = new MinHeapByArray(5);
        minHeap.push(3);
        minHeap.push(1);
        minHeap.push(7);
        minHeap.push(2);
        minHeap.push(9);

        minHeap.print();

        System.out.println(minHeap.peek());
        System.out.println(minHeap.pop());
        System.out.println(minHeap.peek());
    }
}
