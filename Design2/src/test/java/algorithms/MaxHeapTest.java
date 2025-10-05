package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MaxHeapTest {

    private MaxHeap heap;
    private PerformanceTracker tracker;

    @BeforeEach
    void setUp() {
        tracker = new PerformanceTracker();
        heap = new MaxHeap(10, tracker);
    }

    @Test
    void testInsertAndPeek() {
        heap.insert(10);
        heap.insert(5);
        heap.insert(20);
        assertEquals(20, heap.peek());
        assertEquals(3, heap.getSize());
    }

    @Test
    void testExtractMax() {
        heap.insert(10);
        heap.insert(40);
        heap.insert(30);
        assertEquals(40, heap.extractMax());
        assertEquals(2, heap.getSize());
        assertEquals(30, heap.peek());
    }

    @Test
    void testIncreaseKey() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.increaseKey(2, 25);
        assertEquals(25, heap.peek());
    }

    @Test
    void testBuildHeap() {
        int[] arr = {3, 9, 2, 1, 4, 5};
        heap.buildHeap(arr);
        assertEquals(9, heap.peek());
        assertEquals(6, heap.getSize());
    }

    @Test
    void testIsEmpty() {
        assertTrue(heap.isEmpty());
        heap.insert(1);
        assertFalse(heap.isEmpty());
    }

    @Test
    void testPerformanceTrackerCounts() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(15);
        assertTrue(tracker.getComparisons() > 0);
        assertTrue(tracker.getSwaps() > 0);
        assertTrue(tracker.getAssignments() > 0);
    }

    @Test
    void testExtractFromEmptyHeapThrows() {
        assertThrows(IllegalStateException.class, () -> heap.extractMax());
    }

    @Test
    void testIncreaseKeyInvalidIndexThrows() {
        assertThrows(IndexOutOfBoundsException.class, () -> heap.increaseKey(5, 100));
    }

    @Test
    void testIncreaseKeySmallerValueThrows() {
        heap.insert(50);
        assertThrows(IllegalArgumentException.class, () -> heap.increaseKey(0, 10));
    }
}
