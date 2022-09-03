import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

class Query implements Comparable<Query> {
    int left, right, id, buck;

    public Query(int l, int r, int ID, int SQRT) {
        left = l;
        right = r;
        id = ID;
        buck = left / SQRT;
    }

    @Override
    public int compareTo(Query o) {
        if (buck != o.buck)
            return buck - o.buck;
        return right - o.right;
    }
}

public class Problem7 {
    static final Scanner in = new Scanner(System.in);
    static final int MAXLENGTH = 1_000_01, /* 1<=n<=100000 */ tempNum = 100;
    static int Length, NumberOfQueries;
    static int[] contentMsg;
    static long[] results;

    static Query Queries[];

    static HashSet<Integer> RootHuff;
    static int[] Frequency, tempFrequency;

    public static void Print(long[] answres) {
        for (int i = 0; i < NumberOfQueries; i++)
            System.out.println(results[i]);
    }

    public static void run() {
        System.out.println("Enter Length of msg");
        Length = in.nextInt();
        System.out.println("Enter Content  of msg");// 1 2 1 3 1 2 1

        contentMsg = new int[Length];
        for (int i = 0; i < Length; i++)
            contentMsg[i] = in.nextInt() - 1;

        System.out.println("Enter Number of queris");
        NumberOfQueries = in.nextInt();// 5
        System.out.println("Enter  queris");
        // 1 7
        // 1 3
        // 3 5
        // 2 4
        // 4 4
        Queries = new Query[NumberOfQueries];
        results = new long[NumberOfQueries];
        for (int i = 0; i < NumberOfQueries; i++) {
            int minquery = in.nextInt() - 1;
            int maxquery = in.nextInt() - 1;
            Queries[i] = new Query(minquery, maxquery, i, tempNum);
            if (minquery == maxquery)
                results[i] = 0;
        }

        Arrays.sort(Queries);

        int l = 0, r = -1;
        Frequency = new int[MAXLENGTH];
        tempFrequency = new int[tempNum];
        RootHuff = new HashSet<Integer>();
        tempFrequency[0] = Length;
        for (int qq = 0; qq < NumberOfQueries; qq++) {
            Query tempquery = Queries[qq];
            if (tempquery.left == tempquery.right)// 0 0 or 1 1..
                continue;
            while (r < tempquery.right)
                add(contentMsg[++r]);
            while (l > tempquery.left)
                add(contentMsg[--l]);
            while (r > tempquery.right)
                remove(contentMsg[r--]);
            while (l < tempquery.left)
                remove(contentMsg[l++]);

            long res = 0;
            PriorityQueue<Integer> HuffmanTree = new PriorityQueue<Integer>();

            for (int i : RootHuff)
                HuffmanTree.add(Frequency[i]);

            int[] currentFrequencey = new int[tempFrequency.length];
            for (int i = 0; i < tempFrequency.length; i++)
                currentFrequencey[i] = tempFrequency[i];

            int leftover = -1;
            for (int i = 1; i < tempNum; i++) {
                int crr = currentFrequencey[i];
                if (crr == 0)
                    continue;
                if (leftover != -1) {
                    int newvalue = leftover + i;
                    res += newvalue;
                    if (newvalue >= tempNum)
                        HuffmanTree.add(newvalue);
                    else
                        currentFrequencey[newvalue]++;
                    leftover = -1;
                    crr--;
                }
                if ((crr & 1) == 1) {
                    leftover = i;
                    crr--;
                }
                res += ((long) (i)) * crr;
                int newvalue = i * 2;
                if (newvalue >= tempNum)
                    for (int j = 0; j < crr >> 1; j++)
                        HuffmanTree.add(newvalue);
                else
                    currentFrequencey[newvalue] += crr >> 1;
            }
            if (leftover != -1)
                HuffmanTree.add(leftover);
            while (HuffmanTree.size() > 1) {
                int a = HuffmanTree.poll();
                int b = HuffmanTree.poll();
                res += (a + b);
                HuffmanTree.add(a + b);
            }
            results[tempquery.id] = res;
        }
        Print(results);
    }

    static void add(int value) {
        if (Frequency[value] >= tempNum)
            Frequency[value]++;
        else {
            tempFrequency[Frequency[value]]--;
            Frequency[value]++;
            tempFrequency[Frequency[value]]++;
        }
    }

    static void remove(int value) {
        if (Frequency[value] > tempNum)
            Frequency[value]--;
        else if (Frequency[value] < tempNum) {
            tempFrequency[Frequency[value]]--;
            Frequency[value]--;
            tempFrequency[Frequency[value]]++;
        } else {
            RootHuff.remove(value);
            tempFrequency[tempNum - 1]++;
            Frequency[value]--;
        }
    }

    public static void main(String[] args) {
        run();
    }
}
