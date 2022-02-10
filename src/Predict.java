import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Predict implements Command {
    @Override
    public String name() {
        return "predict";
    }

    ///home/huu-phuc-le/java_exercise_1/text.txt
    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Where is the file ?(Please enter the path to the file):");
        String path = scanner.nextLine();
        Predict.predict_text(path, scanner);
        return true;
    }

    private static void predict_text(String path, Scanner scanner) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(path)));

            data = data.toLowerCase();
            data = data.replaceAll("[^a-zA-Z0-9] ", "");
            String[] words = data.split(" ");

            for (int i = 0; i < words.length; i++) {
                if (words[i].isBlank()) {//shifting elements
                    System.arraycopy(words, i + 1, words, i, words.length - 1 - i);
                }
            }

            Stream<String> stream_word = Arrays.stream(words);
            String word = get_word(scanner);
//
            String finalWord = word;
            if (stream_word.noneMatch(s -> s.contains(finalWord))) {
                System.out.println("Word doesn't exist !!");
                return;
            }
            List<String> second_words = new ArrayList<>();
            second_words.add(word);
            String second;
            for (int i = 0; i < 20; i++) {
                second = get_second_word(words, word);
//            System.out.println(second);
                second_words.add(second);
                word = second;
            }
            StringJoiner joiner = new StringJoiner(" ");
            for (String ele : second_words) {
                joiner.add(ele);
            }
            String result = joiner.toString();

            System.out.println(result);
        } catch (IOException x) {
            System.err.format("Unreadable file: %s%n", x);
        }
    }

    public static String get_word(Scanner scanner) {
        System.out.println("which word you are going to type ? :");
        return scanner.nextLine();
    }

    public static String get_second_word(String[] words, String word) {
        List<String> second_word = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word)) {
                if (i + 1 != words.length)
                    second_word.add(words[i + 1]);
            }
        }
        Map<String, Long> grouped = second_word.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()
                ));
        // sorting
        LinkedHashMap<String, Long> sorted_grouped_words = new LinkedHashMap<>();

        grouped.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sorted_grouped_words.put(x.getKey(), x.getValue()));
        //limiting 3
        List<String> most_used_words = new ArrayList<>(sorted_grouped_words.keySet());

//        System.out.println(second_word);
        return most_used_words.get(0);
    }
}
