package metrics;
public class PerformanceTracker {
    private long comparisons;
    private long swaps;
    private long startTime;
    private long endTime;
    public void start() {
        comparisons = 0;
        swaps = 0;
        startTime = System.nanoTime();
    }
    public void stop() {
        endTime = System.nanoTime();
    }
    public void incrementComparisons() {
        comparisons++;
    }
    public void incrementSwaps() {
        swaps++;
    }
    public long getComparisons() {
        return comparisons;
    }
    public long getSwaps() {
        return swaps;
    }
    public long getExecutionTime() {
        return (endTime - startTime) / 1_000_000;
    }
    public void printReport() {
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Swaps: " + swaps);
        System.out.println("Execution Time: " + getExecutionTime() + " ms");
    }
}