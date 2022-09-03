/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p4;

import java.util.Scanner;

/**
 *
 * @author Abdelrahman Hany
 */
public class problem4 {

    private static int[] arr = new int[5010];

    public static int answer(int l, int r) {
        if (l > r) {
            return 0;
        } else if (l == r && arr[l] == 0) {
            return 0;
        } else if (l == r && arr[l] != 0) {
            return 1;
        }

        int minindx = 0;

        int min = Integer.MAX_VALUE;

        for (int i = l; i <= r; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minindx = i;
            }
        }
        int minimum = arr[minindx];

        for (int i = l; i <= r; i++) {
            arr[i] = arr[i] - minimum;
        }

        int l1 = minindx - 1;
        int r1 = minindx + 1;

        int ans = Math.min((r - l) + 1, answer(l, l1) + answer(r1, r) + minimum);

        return ans;
    }

    public static void main(String[] args) {
        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        System.out.println(answer(0, n));
    }
}
