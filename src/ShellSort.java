package algorithms;
import metrics.PerformanceTracker;
public class ShellSort {
    public enum GapSequence {
        SHELL, KNUTH, SEDGEWICK
    }
    private GapSequence sequenceType;
    private PerformanceTracker tracker;
    public ShellSort(GapSequence sequenceType) {
        this.sequenceType = sequenceType;
        this.tracker = new PerformanceTracker();
    }
    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        int[] gaps = generateGaps(n);
        for (int gap : gaps) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= gap && compare(arr[j - gap], temp) > 0) {
                    arr[j] = arr[j - gap];
                    tracker.incrementSwaps();
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }
    private int compare(int a, int b) {
        tracker.incrementComparisons();
        return Integer.compare(a, b);
    }
    private int[] generateGaps(int n) {
        switch (sequenceType) {
            case KNUTH:
                return generateKnuthGaps(n);
            case SEDGEWICK:
                return generateSedgewickGaps(n);
            default:
                return generateShellGaps(n);
        }
    }
    private int[] generateShellGaps(int n) {
        int gap = n / 2;
        int[] gaps = new int[(int) (Math.log(n) / Math.log(2))];
        int index = 0;
        while (gap > 0) {
            gaps[index++] = gap;
            gap /= 2;
        }
        return trim(gaps, index);
    }
    private int[] generateKnuthGaps(int n) {
        int gap = 1;
        int count = 0;
        while (gap < n) {
            count++;
            gap = 3 * gap + 1;
        }
        int[] gaps = new int[count];
        gap = 1;
        for (int i = 0; i < count; i++) {
            gaps[i] = gap;
            gap = 3 * gap + 1;
        }
        reverse(gaps);
        return gaps;
    }
    private int[] generateSedgewickGaps(int n) {
        int[] temp = new int[50];
        int k = 0, gap;
        while (true) {
            if (k % 2 == 0)
                gap = 9 * ((1 << (2 * k)) - (1 << k)) + 1;
            else
                gap = 8 * (1 << (2 * k)) - 6 * (1 << (k + 1)) + 1;
            if (gap > n) break;
            temp[k++] = gap;
        }
        int[] gaps = trim(temp, k);
        reverse(gaps);
        return gaps;
    }
    private int[] trim(int[] arr, int length) {
        int[] trimmed = new int[length];
        System.arraycopy(arr, 0, trimmed, 0, length);
        return trimmed;
    }
    private void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
    public PerformanceTracker getTracker() {
        return tracker;
    }
}