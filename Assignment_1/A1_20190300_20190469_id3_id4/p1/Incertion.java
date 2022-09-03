/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sort;

import java.util.Scanner;

/**
 *
 * @author Abdelrahman Hany
 */
public class Incertion {

    public static int Sort(int arr[], int n) {
        int swap = 0;

        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
                swap++;
            }
            arr[j + 1] = key;
        }
        return swap;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n_swap;
        System.out.println("Enter data");

        String take_input = input.nextLine();// take input as string
        String[] data = take_input.split(" ");//split input

        int n = data.length;

        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(data[i]);//convert input tn string

        }


        n_swap = Sort(arr, n);
        System.out.println("number of Swaping = " + n_swap);

        /*System.out.println("Array After Sorting");
        System.out.print("{ ");

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.print("}");
         */
    }
}
