import java.util.ArrayList;

public class Game{
    public static void main(String[] args) {
        ArrayList<String> wordCollection = new ArrayList<>();
        wordCollection.add("BALEIA");
        wordCollection.add("AGUA");
        wordCollection.add("CAFE");
        wordCollection.add("CELULAR");

        Board board = new Board(15, wordCollection);
    }
}
