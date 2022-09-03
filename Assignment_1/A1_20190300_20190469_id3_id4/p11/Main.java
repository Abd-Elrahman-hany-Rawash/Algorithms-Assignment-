/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p_10;

import java.util.Scanner;

/**
 *
 * @author Abdelrahman Hany
 */
public class Main {

    public static int SumArray(int[] arr) {
        //base case
        if (arr.length == 0) {
            return -1;
        } else if (arr.length == 1) {
            return arr[0];
        } else if (arr.length == 2) {
            return Math.max(arr[0], arr[1]);
        } else {

            int[] maxarr = new int[arr.length];
            // base case using dp button up
            maxarr[0] = arr[0];
            maxarr[1] = Math.max(arr[0], arr[1]);
            for (int i = 2; i < arr.length; i++) {
                maxarr[i] = Math.max(arr[i], Math.max(maxarr[i - 1], arr[i] + maxarr[i - 2]));

            }
            return maxarr[arr.length - 1];
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter data");

        String take_input = input.nextLine();// take input as string
        String[] data = take_input.split(" ");//split input

        int n = data.length;

        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(data[i]);//convert input tn string

        }
        //int arr[] = {3, 5, -7, 8, 10};
        if (SumArray(arr) >= 0) {
            System.out.println(SumArray(arr));
        } else {
            System.out.println(0 + "negative ");
        }

    }
}


