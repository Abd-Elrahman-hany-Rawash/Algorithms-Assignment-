import java.util.*;

class Main {

    public static int Game(char arr[], int n, int k) {
        int counter = 0;
        ArrayList<Integer> thieves = new ArrayList<Integer>();
        ArrayList<Integer> police = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            if (arr[i] != 0 && arr[i] == 'T') {
                thieves.add(i);
            } else {
                police.add(i);
            }
        }

        int t = 0, p = 0;
        while (t < thieves.size() && p < police.size()) {

            if ((thieves.get(t) - police.get(p)) <= k) {
                counter++;
                t++;
                p++;
            } else if (police.get(p) > thieves.get(t)) {
                p++;
            } else {
                t++;
            }
        }

        return counter;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter K: "); // units
        int k = sc.nextInt();

        System.out.println("Enter N"); // number of elements

        int n = sc.nextInt();

        char arr[] = new char[n];

        System.out.println("Enter Elements By Spaces ");

        for (int i = 0; i < n; i++) {
            arr[i] = sc.next().charAt(0);

        }

        System.out.println(Game(arr, n, k));
        sc.close();
    }
}
