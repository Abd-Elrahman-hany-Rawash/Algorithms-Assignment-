package merge.sort;

import java.util.Scanner;

class Test {
   static int getInvCount(int arr[], int n)
    {
        int inv_count = 0;
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++)
                if (arr[i] > arr[j])
                    inv_count++;
 
        return inv_count;
    }
 
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("How many number of array elements ?");
        int num = input.nextInt();
        int arr[] = new int[num];
        System.out.println("Enter the " + num + " array elements");
        for (int i = 0 ; i < arr.length; i++ ) {
           arr[i] = input.nextInt();
        }       
        System.out.println("Number of inversions are "+ getInvCount(arr,arr.length));
    }
}