import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        ArrayList<String> wordCollection = new ArrayList<>();
        wordCollection.add("AAAAAAAAAAAAAAA");
        wordCollection.add("AAAAAAAAAAAAAAA");
        wordCollection.add("AAAAAAAAAAAAAAA");
        wordCollection.add("AAAAAAAAAAAAAAA");

        Board board = new Board(15, wordCollection);
    }
}
