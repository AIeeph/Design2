package algorithms;

import metrics.PerformanceTracker;

public class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;
    private PerformanceTracker tracker;

    public MaxHeap(int capacity, PerformanceTracker tracker) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
        this.tracker = tracker;
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        tracker.incrementSwaps();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(int key) {
        if (size == capacity) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = key;
        tracker.incrementAssignments();
        size++;
        heapifyUp(size - 1);
    }

    private void heapifyUp(int i) {
        while (i > 0 && heap[parent(i)] < heap[i]) {
            tracker.incrementComparisons();
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public int extractMax() {
        if (size <= 0) throw new IllegalStateException("Heap is empty");
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        tracker.incrementAssignments();
        heapifyDown(0);
        return max;
    }

    private void heapifyDown(int i) {
        int largest = i;
        int left = left(i);
        int right = right(i);

        if (left < size) {
            tracker.incrementComparisons();
            if (heap[left] > heap[largest]) largest = left;
        }
        if (right < size) {
            tracker.incrementComparisons();
            if (heap[right] > heap[largest]) largest = right;
        }

        if (largest != i) {
            swap(i, largest);
            heapifyDown(largest);
        }
    }

    public void increaseKey(int index, int newKey) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index");
        if (newKey < heap[index])
            throw new IllegalArgumentException("New key must be larger");

        heap[index] = newKey;
        tracker.incrementAssignments();
        heapifyUp(index);
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");
        return heap[0];
    }

    public int getSize() {
        return size;
    }

    // Optional: buildHeap for bottom-up heap construction
    public void buildHeap(int[] arr) {
        if (arr.length > capacity)
            throw new IllegalArgumentException("Array exceeds heap capacity");
        System.arraycopy(arr, 0, heap, 0, arr.length);
        size = arr.length;
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }
}
