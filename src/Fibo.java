import java.util.Scanner;

public class Fibo implements Command {
    @Override
    public String name() {
        return "fibo";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Please enter a number");
        int number = scanner.nextInt();
        scanner.nextLine();
        System.out.println(Fibo.fibo(number));
        return true;
    }

    private static int fibo(int number) {
        if(number >= 2)
            return fibo(number - 1) + fibo(number - 2);
        else
            return number;
    }

}
