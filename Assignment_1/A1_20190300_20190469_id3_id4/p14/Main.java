/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p_13;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Abdelrahman Hany
 */
public class Main {

    public static void GetPair(int arr[], int target) {
        boolean flag = false;
        Map<Integer, Integer> map = new Hashtable<>();
        /* for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }*/
        for (int i = 0; i < arr.length; i++) {
            int temp = target - arr[i];
            if (map.containsKey(temp)) {
                System.out.print("Pair found (" + arr[i] + "," + temp + ")");
                flag = true;
            }
            map.put(arr[i], i);

        }
        if (!flag) {
            System.out.println("Pair not found");
        }

    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter data");

        String take_input = input.nextLine();// take input as string
        String[] data = take_input.split(" ");//split input

        int n = data.length;
        int arr[] = new int[n];

        int target;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(data[i]);//convert input tn string

        }

        System.out.println("enter target");
        target = input.nextInt();
        GetPair(arr, target);

    }

}
