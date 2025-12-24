import java.util.*;

public class GameProcess {
    private String targetWord;
    private HashSet<String> dict;
    private Scanner scanner = new Scanner(System.in);
    private String wordNotCompliesRequirements = "Введенное слово не соответствует требованиям, введите заново:";
    private String turnsRemainsMessage = "Ходов осталось: ";
    private String loseMessage = "Вы проиграли! Загаданное слово: ";

    public GameProcess(HashSet<String> dict) {
        this.dict = (HashSet<String>) dict.clone();
        targetWord = getRandomWord();
    }

    private String getRandomWord() {
        ArrayList<String> arr = new ArrayList<>();
        for (String st : dict) {
            arr.add(st);
        }
        Random random = new Random();
        return arr.get(random.nextInt(0, arr.size()));
    }

    public void game() {
        int turns = 6;
        print(getGreeting());
        while (turns > 0) {
            String word = requestWord();
            if (word.isEmpty()) {
                word = getRandomWord();
                print(word);
            }
            if (checkContains(word)) {
                if (compareWords(word, targetWord)) {
                    print(getCongratulation());
                    break;
                }
                print(getTip(word));
            } else {
                print(wordNotCompliesRequirements);
                continue;
            }
            turns--;
            print(turnsRemainsMessage + turns);
        }
        print(loseMessage + targetWord);

    }

    private boolean compareWords(String word, String targetWord) {
        return targetWord.equals(word);
    }

    private boolean checkContains(String word) {
        return dict.contains(word);
    }

    private String getTip(String word) {
        StringBuilder tipB = new StringBuilder();
        if (word.length() == targetWord.length()) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == targetWord.charAt(i)) {
                    tipB.append('+');
                    continue;
                } else if (targetWord.contains(word.substring(i, i + 1))) {
                    tipB.append('^');
                    continue;
                } else {
                    tipB.append('-');
                }
            }
            return tipB.toString();
        } else {
            throw new RuntimeException("Длины сравниваемых слов не соответствуют");
        }
    }

    private String requestWord() {
        String input = scanner.nextLine();
        return input;
    }

    private String getGreeting() {
        return "Хола, слово из 5 букв загадано, пожалуйста, введите в консоль слова, чтобы получать подсказки.\n"
                + "Для введенного слова вы получите подсказку-шифр, где '+' означает, что буква угадана верно, '-' - неверно\n"
                + "'^' - что буква присутствует в загаданном слове, но на другой позиции.\n"
                + "У вас 6 ходов, чтобы угадать слово, и, соответственно, 5 подсказок.\n"
                + "Обратите внимание, что вводимые вами слова должны состоять из 5 букв и существовать в словаре.\n"
                + "Если вы не знаете, какое слово ввести, нажмите Enter, кампутер подберет его за вас. GL HF.\n"
                + targetWord;
    }

    private String getCongratulation() {
        return "Поздравляем, вы победили!";
    }

    private void print(Object o) {
        System.out.println(o);
    }
}

