import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private int DIMENSION;
    private char[][] grid;
    //private char[] alphabet = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public Board(int dimension){
        DIMENSION = dimension;
        grid = new char[DIMENSION][DIMENSION];// Cada posição é iniciada com 0;
        fillEmptySpace();
    }

    private void fillEmptySpace(){
        Random random = new Random();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                grid[i][j] = (char)ThreadLocalRandom.current().nextInt(65, 90);
            }
            //placeDiagonal("TESTE", 0, 0);
            System.out.println(Arrays.toString(grid[i]));
        }
    }

    private void placeWord(String word){

    }

    //Orientação da palavra, 0 - horizontal | 1 - vertical | 2 - diagonal
    private int setOrientation(){
        return ThreadLocalRandom.current().nextInt(0, 2);
    }

    private void placeHorizontal(String word, int x, int y){
        for (int i = 0; i < word.length(); i++) {
            grid[x][y] = word.charAt(i);
            y++;
        }
    }

    private void placeVertical(String word, int x, int y){
        for (int i = 0; i < word.length(); i++) {
            grid[x][y] = word.charAt(i);
            x++;
        }
    }

    private void placeDiagonal(String word, int x, int y){
        for (int i = 0; i < word.length(); i++) {
            grid[x][y] = word.charAt(i);
            x++;
            y++;
        }
    }
}
