package cli;

import algorithms.MaxHeap;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};
        Random rand = new Random();

        try (FileWriter writer = new FileWriter("heap_performance.csv")) {
            writer.write("n,comparisons,swaps,assignments,time_ms\n");

            for (int n : sizes) {
                PerformanceTracker tracker = new PerformanceTracker();
                MaxHeap heap = new MaxHeap(n + 1, tracker);
                int[] data = new int[n];
                for (int i = 0; i < n; i++) data[i] = rand.nextInt(1_000_000);

                long start = System.nanoTime();
                for (int value : data) {
                    heap.insert(value);
                }
                for (int i = 0; i < n / 2; i++) {
                    heap.extractMax();
                }
                long end = System.nanoTime();

                double timeMs = (end - start) / 1_000_000.0;
                writer.write(String.format("%d,%d,%d,%d,%.3f\n",
                        n,
                        tracker.getComparisons(),
                        tracker.getSwaps(),
                        tracker.getAssignments(),
                        timeMs));

                System.out.printf("n=%d -> time=%.3fms, comps=%d, swaps=%d, assigns=%d\n",
                        n, timeMs, tracker.getComparisons(), tracker.getSwaps(), tracker.getAssignments());
            }

            System.out.println("Benchmark results saved to heap_performance.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}