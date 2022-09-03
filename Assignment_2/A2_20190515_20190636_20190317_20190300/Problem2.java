
/**1. Describe why the base Ackermann function is so heavy to compute
 * The Ackermann function have base don't computing easily because any value in this function is growing rapidly
 *  even for small inputs and it's NP-HARD and it's growing exponential way, and it have 3 cases recursions that's
 *  meannig that it take much time when computing it and also waste memory by these recursion cases because 
it's take 2 numbers such m,n, then it's do it's calculations by set of conditions that it'
s difficult from side of memory and time , and when starting with (m=4 and n=1) 
and above of this base the difficulty begin appearing.
this words with evidence that in point 2 in this assignment when put inputs to calculate Ackermann {(4,1), (4,2), (5,1), and so on...) we notice that it's doing the calculation and produce the result correctlt, but also take huge time that will reach 30 seconds and it's bad complexity in any algorithm whatever which data structure is used.
 * 
 * 
 * 
*/

import java.util.Scanner;
import java.util.ArrayList;

public class Problem2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter m:");
        int m = in.nextInt();

        System.out.println("Enter n:");
        int n = in.nextInt();
        if (m > 0 && n > 0)
            ack(m, n);
        else
            System.out.println("You enter m or n less 0");
        in.close();
    }

    public static void ack(int M, int N) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (int m = 0; m <= M; m++)
            result.add(new ArrayList<Integer>());
        Boolean done = false;
        while (done == false) {
            for (int m = 0; m <= M; m++) {
                int n = result.get(m).size();
                int a = 0;
                if (m == 0)
                    a = n + 1;
                else if (n == 0) {
                    if (result.get(m - 1).size() <= 1)
                        break;
                    a = result.get(m - 1).get(1);
                } else {
                    int k = result.get(m).get(n - 1);
                    if (result.get(m - 1).size() <= k)
                        break;
                    a = result.get(m - 1).get(k);
                }
                result.get(m).add(a);
                if (m == M && n == N) {
                    System.out.println("Ack(" + M + ", " + N + ") = " + a);
                    done = true;
                    break;
                }
            }
        }
    }
}