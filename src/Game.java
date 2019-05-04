import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        ArrayList<String> wordCollection = new ArrayList<>();
        wordCollection.add("ABACAXI");
        wordCollection.add("TECLADO");
        wordCollection.add("MOUSE");
        wordCollection.add("AGUA");

        Board board = new Board(15, wordCollection);
    }
}
