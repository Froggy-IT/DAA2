import algorithms.ShellSort;
import algorithms.ShellSort.GapSequence;
import metrics.PerformanceTracker;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        int[] originalArray = {64, 34, 25, 12, 22, 11, 90, 42, 5};
        for (GapSequence seq : GapSequence.values()) {
            System.out.println("\nTesting " + seq + " gap sequence:");
            int[] arr = Arrays.copyOf(originalArray, originalArray.length);
            ShellSort shellSort = new ShellSort(seq);
            PerformanceTracker tracker = shellSort.getTracker();
            tracker.start();
            shellSort.sort(arr);
            tracker.stop();
            System.out.println("Sorted array: " + Arrays.toString(arr));
            tracker.printReport();
        }
    }
}
