import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Freq implements Command {
    @Override
    public String name() {
        return "freq";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Where is the file ?(Please enter the path to the file):");
        String path = scanner.nextLine();
        Freq.three_letters_most_freq(path);
        return true;
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
