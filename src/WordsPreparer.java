import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.LinkedList;
import java.nio.file.Path;
import java.nio.file.Paths;


public class WordsPreparer {
    private static String dir = System.getProperty("user.dir");
    private static String defaultPath = dir + "\\data";
    private static Path dirWordsFile = Paths.get(defaultPath + "\\words_ru.txt");

    private static LinkedList<String> loadFromFile() {
        LinkedList<String> words = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dirWordsFile.toString(), StandardCharsets.UTF_8))) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (notVoid(line)) {
                    words.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return words;
    }

    private static void writeToFile(LinkedList<String> words) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dirWordsFile.toString(), StandardCharsets.UTF_8, false))) {
            String word = words.poll();
            while (word != null) {
                writer.write(word);
                writer.newLine();
                word = words.poll();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static LinkedList<String> getWords(LinkedList<String> words) {
        LinkedList<String> newWords = new LinkedList<>();
        String word = words.poll();
        while (word != null) {
            word = word.toLowerCase();
            while (word.contains("ё")) {
                word = word.replace("ё", "е");
            }
            if (word.length() == 5) {
                newWords.add(word);
            }
            word = words.poll();
        }
        return newWords;
    }

    public static HashSet<String> getDictionary() {
        LinkedList<String> words = getWords(loadFromFile());
        HashSet<String> dict = new HashSet<>();
        String word = words.poll();
        while (word != null) {
            dict.add(word);
            word = words.poll();
        }
        return dict;
    }

    private static boolean notVoid(String line) {
        return !(line.isEmpty() | line.isBlank());
    }

}
