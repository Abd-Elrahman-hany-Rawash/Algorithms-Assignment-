
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Abdelrahman Hany
 */
public class problem13 {
    
    public static void print2D(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void print(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void print(int i, int j, int n, int[][] kth,node[]arr) {
        if (i == j) {

            System.out.print(arr[i-1].name);
            return;
        }
        System.out.print("(");
        print(i, kth[i][j], n, kth,arr);

        print(kth[i][j] + 1, j, n, kth,arr);
        System.out.print(")");

    }

    public static int dp(int[] d, int[][] kth,node[]arr) {
        int n = d.length;
        
        int[][] c = new int[n + 1][n + 1];

        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                c[i][j] = 99999999; //max value
                for (int k = i; j < n && k <= j - 1; k++) {
                    int temp = c[i][k] + c[k + 1][j]+ d[i - 1] * d[k] * d[j];

                    if (temp < c[i][j]) {
                        c[i][j] = temp;
                        kth[i][j] = k;
                    }
                }
            }
        }
        print2D(c);
         print2D(kth);
        print(1, n - 1, n, kth,arr);
        return kth[1][kth.length - 1];
    }

    public static void main(String[] args) {
       
        Scanner input = new Scanner(System.in);
        System.out.println("Enter numder of array");
        int n;
        n = input.nextInt();
        node arr[] = new node[n];
        for (int i = 1; i <= n; i++) {
            int x;
            int y;
            x = input.nextInt();
            y = input.nextInt();
            arr[i - 1] = new node(x, y,"A"+i );
        }
        // System.out.println(arr.length);
        int[] d = new int[n + 1];
        for (int i = 0; i < arr.length; i++) {//for d
            d[i] = arr[i].x;
            d[d.length - 1] = arr[i].y;
        }
        int[][] kth = new int[n + 1][n + 1];
        int index = dp(d, kth,arr);
       // System.err.println(index);
    }

}
