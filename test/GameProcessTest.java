import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashSet;
import static org.junit.Assert.*;

public class GameProcessTest extends GameProcess {
    private static HashSet<String> dict = WordsPreparer.getDictionary();;
    private static GameProcess gameProcess;

    public GameProcessTest() {
        super(dict);
    }

    @Test
    public void tipTest() {
        targetWord = "перец";
        String word = "пицца";
        assertEquals(getTip(word), "+-^^-");

    }
}
