import Backend.Board;
import UI.GameScreen;

import java.util.ArrayList;

public class Game{
    public static void main(String[] args) {
        ArrayList<String> wordCollection = new ArrayList<>();
        wordCollection.add("BALEIA");
        wordCollection.add("AGUA");
        wordCollection.add("CAFE");
        wordCollection.add("CELULAR");
        wordCollection.add("CAIXA");
        wordCollection.add("MONITOR");
        wordCollection.add("CADEIRA");

        int dimension = 16;
        Board board = new Board(dimension, wordCollection);
        GameScreen game = new GameScreen(board.getGrid());
    }
}
