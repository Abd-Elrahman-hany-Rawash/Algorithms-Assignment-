import java.util.Arrays;
import java.util.Scanner;

class node implements Comparable<node> {
    private String JobName;
    public int Deadline;
    public int Profit;

    public node(String JobName, int Deadline, int Profit) {
        this.JobName = JobName;
        this.Deadline = Deadline;
        this.Profit = Profit;
    }

    public String getJobName() {
        return JobName;
    }

    public void setJobName(String JobName) {
        this.JobName = JobName;
    }

    public int getDeadline() {
        return Deadline;
    }

    public void setDeadline(int Deadline) {
        this.Deadline = Deadline;
    }

    public int getProfit() {
        return Profit;
    }

    public void setProfit(int Profit) {
        this.Profit = Profit;
    }

    @Override
    public int compareTo(node o) {
        return o.Profit - this.Profit;
    }

    @Override
    public String toString() {
        return "node{" + "JobName=" + JobName + ", Deadline=" + Deadline + ", Profit=" + Profit + '}';
    }
}

public class problem8 {
    public static int MaxDeadline(node[] arr) {
        int max = arr[0].getDeadline();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].getDeadline() > max) {
                max = arr[i].getDeadline();
            }
        }
        return max;
    }

    public static void optimal(node arr[], int Max) {
        String Sequance[] = new String[Max];
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr[i].Deadline - 1; j >= 0; j--) {
                if (j < Max && Sequance[j] == null) {
                    Sequance[j] = arr[i].getJobName();
                    break;
                }
            }

        }
        System.out.println("The is maximum profit sequence of jobs:");
        for (int i = 0; i < Max; i++) {
            if (Sequance[i] != null) {
                System.out.print(Sequance[i] + ",");
            }
        }
    }

    public static void main(String[] args) {
        int n;
        String JobName;
        int Deadline;
        int profit;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of jops");
        n = input.nextInt();
        node[] arr = new node[n];
        System.out.println("Ente JobID , Deadline and Profit for Every job");
        for (int i = 0; i < arr.length; i++) {
            JobName = input.next();
            Deadline = input.nextInt();
            profit = input.nextInt();
            arr[i] = new node(JobName, Deadline, profit);
        }
        int max = MaxDeadline(arr);
        Arrays.sort(arr);
        optimal(arr, max);
        input.close();
    }
}
