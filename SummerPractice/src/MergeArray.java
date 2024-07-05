import java.util.Arrays;
import java.util.Scanner;

public class MergeArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива m: ");
        int mSize = scanner.nextInt();
        int[] m = new int[mSize];
        System.out.println("Введите элементы массива m:");
        for (int i = 0; i < mSize; i++) {
            m[i] = scanner.nextInt();
        }

        System.out.print("Введите размер массива n: ");
        int nSize = scanner.nextInt();
        int[] n = new int[nSize];
        System.out.println("Введите элементы массива n:");
        for (int i = 0; i < nSize; i++) {
            n[i] = scanner.nextInt();
        }
        int[] result = mergeAndSort(m, n);
        System.out.println("Результат: " + Arrays.toString(result));

        scanner.close();
    }

    public static int[] mergeAndSort(int[] m, int[] n) {
        int[] merged = new int[m.length + n.length];
        System.arraycopy(m, 0, merged, 0, m.length);
        System.arraycopy(n, 0, merged, m.length, n.length);

        boolean isEven = (m.length + n.length) % 2 == 0;

        if (isEven) {
            Arrays.sort(merged);
        } else {
            Arrays.sort(merged);
            reverseArray(merged);
        }

        return merged;
    }

    private static void reverseArray(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }
}