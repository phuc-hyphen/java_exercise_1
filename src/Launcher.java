import java.util.Scanner;

import static java.awt.Color.white;
import static java.awt.SystemColor.text;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Welcome to Java 101, What is your command");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        while (!text.equals("quit")) {
            if (text.equals("fibo")) {
                System.out.println("Please enter a number");
                int number = scanner.nextInt();
                scanner.nextLine();
                System.out.format("value of the Fibonacci sequence at the index %d is %d \n", number, fibo(number));
            } else {
                System.out.println("Unknown command");
            }
            System.out.println("What is your command");
            text = scanner.nextLine();
        }
    }

    public static int fibo(int number) {
        int a = 0;
        int sum = 0;
        int b = 1;
        for (int i = 0; i < number; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return sum;
    }

}
