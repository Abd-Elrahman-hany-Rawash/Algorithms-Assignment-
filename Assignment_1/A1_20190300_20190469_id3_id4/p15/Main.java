/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p_14;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Abdelrahman Hany
 */
public class Main {

    static void SearchIndex(int arr[]) {
        int index = -1;

        Map<Integer, Integer> map = new Hashtable<>();

        for (int i = arr.length - 1; i >= 0; i--) {

            if (map.containsKey(arr[i])) {
                index = i;
            } else {
                map.put(arr[i],i);
            }
        }
        if (index == -1) {
             System.out.println("Invalid Input");
           
        } else {
            System.out.println("The minimum index of the repeating element is " + index);
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
        SearchIndex(arr);
    }

}
