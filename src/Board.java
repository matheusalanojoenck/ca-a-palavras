import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private int DIMENSION;
    private char[][] grid;
    //private char[] alphabet = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public Board(int dimension){
        DIMENSION = dimension;
        grid = new char[DIMENSION][DIMENSION];
        fillEmptySpace();
    }

    private void fillEmptySpace(){
        Random random = new Random();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                grid[i][j] = (char)ThreadLocalRandom.current().nextInt(65, 90);
                //grid[i][j] = alphabet[random.nextInt(25)];
            }
            System.out.println(Arrays.toString(grid[i]));
        }
    }
}
