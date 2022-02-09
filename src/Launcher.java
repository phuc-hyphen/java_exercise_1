import java.util.Scanner;

import static java.awt.Color.white;
import static java.awt.SystemColor.text;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Welcome to Java 101, What is your command");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        while (!text.equals("quit")) {
            System.out.println("Unknown command");
            text = scanner.nextLine();
        }
    }
}
