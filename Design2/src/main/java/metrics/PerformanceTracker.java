package metrics;

public class PerformanceTracker {
    private long comparisons;
    private long swaps;
    private long assignments;

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementSwaps() {
        swaps++;
    }

    public void incrementAssignments() {
        assignments++;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getAssignments() {
        return assignments;
    }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        assignments = 0;
    }

    @Override
    public String toString() {
        return String.format("Comparisons: %d, Swaps: %d, Assignments: %d", comparisons, swaps, assignments);
    }
}