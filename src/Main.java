import algorithms.ShellSort;
import algorithms.ShellSort.GapSequence;
import metrics.PerformanceTracker;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90, 42, 5};
        ShellSort shellSort = new ShellSort(GapSequence.KNUTH);
        PerformanceTracker tracker = shellSort.getTracker();
        tracker.start();
        shellSort.sort(arr);
        tracker.stop();
        System.out.println("Sorted array: " + Arrays.toString(arr));
        tracker.printReport();
    }
}