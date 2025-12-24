import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        HashSet<String> dict =  WordsPreparer.getDictionary();
        GameProcess gameProcess = new GameProcess(dict);
        gameProcess.game();
    }
}