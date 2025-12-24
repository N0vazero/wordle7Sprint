import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Wordle {
    private String dir = System.getProperty("user.dir");
    private String defaultPath = dir + "\\data";
    private Path dirWordsFile = Paths.get(defaultPath + "\\words_ru.txt");
}
