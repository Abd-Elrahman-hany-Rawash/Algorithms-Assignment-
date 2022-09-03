import java.util.HashMap;
import java.util.Map;

class Problem16 {

    public static void main(String[] args) {
        int[] arr = { 5, 4, 5, 5, 3, 1, 2, 2, 4 };
        System.out.println();
        rearRange(arr);
    }

    public static void rearRange(int[] arr) {

        Map<Integer, Integer> table = new HashMap<>();

        for (int i : arr) {
            table.put(i, table.getOrDefault(i, 0) + 1);
        }

        for (int i : arr) {
            if (table.containsKey(i)) {

                int n = table.get(i);
                while (n-- != 0) {
                    System.out.print(i + " ");
                }

                table.remove(i);
            }
        }
    }
}