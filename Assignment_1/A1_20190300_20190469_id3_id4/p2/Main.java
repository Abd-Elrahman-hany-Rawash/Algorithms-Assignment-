
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Abdelrahman Hany
 */
public class Main {
    static long mw,mz;

    public static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = low - 1; //to i++ to incert numer in array

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    public static int quicksort(int arr[], int low, int high, int kth) {
        int result=0 ;
        if(low == high){
        return arr[low];
        }
        else {

            int position = partition(arr, low, high);
            int rank = position - low + 1;
            if (kth == rank) {
                return arr[position];
            } 
            if (kth < rank) {
               return quicksort(arr, low, position - 1, kth);
            } else {
                return quicksort(arr, position + 1, high, kth-rank);

            }

        }

      //  return result;

    }
    public static long random(){
    
    mz=36969*(mz&65535)+(mz>>16);
    mw=18000*(mw&65535)+(mw>>16);
    long res=(mz<<16)+mw;
    return res%1000000000;
    }

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int n=0;
        int arr[];
        int kth;
        
        System.out.println("enter input");
        n=input.nextInt();
        kth=input.nextInt();
        mw=input.nextLong();
        mz=input.nextLong();
        arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=(int)random();
        }
        
        
        
        
        
        int result=quicksort(arr,0,arr.length-1,kth);
        System.out.println(result);
        
       /* for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }*/
    }
}
