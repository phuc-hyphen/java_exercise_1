
import java.util.*;


public class Launcher {
    public static void main(String[] args) throws Exception {
        boolean want_continue = true;
        Scanner scanner = new Scanner(System.in);
        List<Command> commands = List.of(
                new Quit(),
                new Fibo(),
                new Freq(),
                new Predict()
        );
        do {
            System.out.println("What is your command");
            String com = scanner.nextLine();
            Command selectedCommand = commands
                    .stream()
                    .filter((c -> c.name().equals(com)))
                    .findFirst()
                    .orElse(null);
            if (selectedCommand == null)
            {
                System.out.println("Unknown command");

            }
            else
                want_continue = selectedCommand.run(scanner);
        } while (want_continue);
    }
}
