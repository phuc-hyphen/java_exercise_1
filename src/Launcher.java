import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Launcher {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Java 101, What is your command");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        while (!text.equals("quit")) {
            if (text.equals("fibo")) {
                System.out.println("Please enter a number");
                int number = scanner.nextInt();
                scanner.nextLine();
                System.out.format("value of the Fibonacci sequence at the index %d is %d \n", number, fibo(number));
            } else if (text.equals("freq")) {
                System.out.println("Where is the file ?(Please enter the path to the file):");
                String path = scanner.nextLine();
                three_letters_most_freq(path);

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

    public static void three_letters_most_freq(String path) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException x) {
            System.err.format("Unreadable file: %s%n", x);
        }
        data = data.toLowerCase();
        data = data.replaceAll("[^a-zA-Z0-9] ", "");
        String[] words = data.split(" ");

        for (int i = 0; i < words.length; i++) {
            if (words[i].isBlank()) {//shifting elements
                System.arraycopy(words, i + 1, words, i, words.length - 1 - i);
            }
        }
        // array -> stream
        Stream<String> stream_word = Arrays.stream(words);
        //grouping words
        Map<String, Long> grouped = stream_word
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()
                ));
        // sorting
        LinkedHashMap<String, Long> sorted_grouped_words = new LinkedHashMap<>();

        grouped.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sorted_grouped_words.put(x.getKey(), x.getValue()));
        //limiting 3
        List<String> most_used_words = new ArrayList<>(sorted_grouped_words.keySet());
        most_used_words = most_used_words.stream().limit(3).toList();

//        System.out.println(most_used_words);
        for (String ele : most_used_words)
            System.out.print(ele + " ");
        System.out.println("");
    }
}
