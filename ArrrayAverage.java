import java.util.Scanner;
public class ArrrayAverage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] marks = new int [5];
        int sum = 0 ;
        System.out.println("Enter 5 subject marks : ");

        for(int i = 0; i < marks.length ; i++){
            marks[i] = sc.nextInt();
        }
        for(int i = 0 ; i < marks.length ; i++){
            sum = sum + marks [i];
        }
        double average = (double)sum / marks.length;
        System.out.println("Sum = " + sum);
        System.out.println("Average = " + average);
    }
    
}
