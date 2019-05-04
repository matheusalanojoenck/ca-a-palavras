import java.util.Arrays;

public class Board {
    private int DIMENSION;
    private char[][] grid;
    private char[] alphabet = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public Board(int dimension){
        DIMENSION = dimension;
        grid = new char[DIMENSION][DIMENSION];
        fillEmptySpace();
    }

    private void fillEmptySpace(){
        for (int i = 0; i < alphabet.length; i++) {
            //System.out.println(alphabet[i] + "length: " + alphabet.length);
        }
        //Arrays.deepToString();
        grid.toString();

    }
}
