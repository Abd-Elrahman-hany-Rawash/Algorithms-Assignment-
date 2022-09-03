import java.util.HashSet;
import java.util.Set;

class Problem15 {

    public static int findMinIndex(int[] arr) {
        int minIndex = arr.length;

        Set<Integer> set = new HashSet<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            if (set.contains(arr[i])) {
                minIndex = i;
            } else {
                set.add(arr[i]);
            }
        }

        if (minIndex == arr.length) {
            return -1;
        }

        return minIndex;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 6, 3, 4, 3, 6, 4 };

        int minIndex = findMinIndex(arr);

        if (minIndex != -1) {
            System.out.println("The minimum index of the repeating element is " +
                    minIndex);
        } else {
            System.out.println("Invalid Input");
        }
    }
}