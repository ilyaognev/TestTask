import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] a =  {5,2,4,6,1,3,2,6,1};
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(SortTest(a, 0, a.length-1)));
    }

    public static int[] SortTest(int[] a, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            SortTest(a, p, q);
            SortTest(a, q + 1, r);
            Merge(a, p, q, r);
        }
        return a;
    }

    public static void Merge(int[] a, int p, int q, int r) {
        int indexLeft = 0;
        int indexRight = 0;
        int[] leftArray = new int[q - p + 2];
        int[] rightArray = new int[r - q + 1];

        for (int i = p; i <= q; i++) {
            leftArray[i - p] = a[i];
        }
        leftArray[q - p + 1] = Integer.MAX_VALUE;

        for (int i = q + 1; i <= r; i++) {
            rightArray[i - q - 1] = a[i];
        }
        rightArray[r - q] = Integer.MAX_VALUE;

        for (int i = p; i <= r; i++) {
            if (leftArray[indexLeft] < rightArray[indexRight]) {
                a[i] = leftArray[indexLeft];
                indexLeft++;
            } else {
                a[i] = rightArray[indexRight];
                indexRight++;
            }
        }
    }
}
