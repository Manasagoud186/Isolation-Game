import java.util.Scanner;

public class Factor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number n: ");
        int n = sc.nextInt();

        long fact = 1; // use long for bigger numbers
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }

        System.out.println(n + "! = " + fact);
    }
}