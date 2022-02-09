import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Welcome to Java 101, What is your command");
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        if (!text.equals("quit")) {
            System.out.println("Unknown command");
        }
    }
}
