
import java.util.Arrays;
import java.util.Scanner;

class Activity implements Comparable<Activity> {
    public int start;
    public int end;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Activity(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Activity o) {
        return this.end - o.end;
    }
    @Override
    public String toString() {
        return "Activity{" + "start=" + start + ", end=" + end + '}';
    }
}
public class problem4 {
    public static void Activ(int n, Activity arr[]) {
        System.out.println("{" + arr[0].start + "," + arr[0].end + "}");
        int j = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].start > +arr[j].end) {
                System.out.println("{" + arr[i].start + "," + arr[i].end + "}");
                j = i;
            }
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of Avtivities ");
        int n = input.nextInt();
        Activity[] arr = new Activity[n];
        System.out.println("Enter the start time and finish time for all Avtivities ");
        for (int i = 0; i < n; i++) {
            int start = 0;
            int end = 0;
            start = input.nextInt();
            end = input.nextInt();
            arr[i] = new Activity(start, end);
        }
        Arrays.sort(arr);
        System.out.println("Output");
        Activ(arr.length, arr);
        input.close();
    }
}
