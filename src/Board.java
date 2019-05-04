import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private int DIMENSION;
    private char[][] grid;
    int X, Y;

    public Board(int dimension, ArrayList<String> wordColletion){
        DIMENSION = dimension;
        grid = new char[DIMENSION][DIMENSION];// Cada posição é iniciada com 0;
        placeWord(wordColletion);
        fillEmptySpace();
        showBoard();
    }

    private void fillEmptySpace(){
        Random random = new Random();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {

                //grid[i][j] = (char)ThreadLocalRandom.current().nextInt(65, 91);
                if(grid[i][j] == 0) grid[i][j] = '.';
            }
        }
    }

    private void placeWord( ArrayList<String> wordColletion){
        for (String word : wordColletion) {
            int orientation = getOrientation();
            setLocation(word, orientation);
            placeWordUtil(word, orientation);
        }

    }

    private void placeWordUtil(String originalWord, int orientation){
        String word = reverseWord(originalWord);
        switch (orientation){
            case 0:
                placeHorizontal(word, X, Y);
                break;
            case 1:
                placeVertical(word, X, Y);
                break;
            case 2:
                placeDiagonal(word, X, Y);
                break;
        }
    }

    private void setLocation(String word, int orientation){
        Boolean valid;
        switch (orientation){
            case 0:
                do{
                    X = ThreadLocalRandom.current().nextInt(0, DIMENSION - word.length());
                    Y = ThreadLocalRandom.current().nextInt(0, DIMENSION);
                    valid = isPositionValid(word, X, Y, orientation);

                }while(!valid);
                break;
            case 1:
                do{
                    X = ThreadLocalRandom.current().nextInt(0, DIMENSION);
                    Y = ThreadLocalRandom.current().nextInt(0, DIMENSION - word.length());
                    valid = isPositionValid(word, X, Y, orientation);
                }while(!valid);
                break;
            case 2:
                do{
                    X = ThreadLocalRandom.current().nextInt(0, DIMENSION - word.length());
                    Y = ThreadLocalRandom.current().nextInt(0, DIMENSION - word.length());
                    valid = isPositionValid(word, X, Y, orientation);
                }while(!valid);
                break;
        }
    }

    private boolean isPositionValid(String word, int x, int y, int orientation){
        switch (orientation){
            case 0:
                for (int i = 0; i < word.length(); i++) {
                    if((grid[x][y] != 0) && grid[x][y] != word.charAt(i)){
                        return false;
                    }
                    x++;
                }
                break;
            case 1:
                for (int i = 0; i < word.length(); i++) {
                    if((grid[x][y] != 0) && grid[x][y] != word.charAt(i)){
                        return false;
                    }
                    y++;
                }
                break;
            case 2:
                for (int i = 0; i < word.length(); i++) {
                    if((grid[x][y] != 0) && (grid[x][y] != word.charAt(i))){
                        return false;
                    }
                    x++;
                    y++;
                }
                break;
            default:
                System.out.println("isPositionValid != 0, 1 ou 2");
                break;

        }
        return true;
    }

    //Orientação da palavra, 0 - horizontal | 1 - vertical | 2 - diagonal
    private int getOrientation(){
        return ThreadLocalRandom.current().nextInt(0, 3);
    }

    private void placeHorizontal(String word, int x, int y){
        for (int i = 0; i < word.length(); i++) {
            grid[x][y] = word.charAt(i);
            x++;
        }
    }

    private void placeVertical(String word, int x, int y){
        for (int i = 0; i < word.length(); i++) {
            grid[x][y] = word.charAt(i);
            y++;
        }
    }

    private void placeDiagonal(String word, int x, int y){
        for (int i = 0; i < word.length(); i++) {
            grid[x][y] = word.charAt(i);
            x++;
            y++;
        }
    }

    private String reverseWord(String word){
        int reverse = ThreadLocalRandom.current().nextInt(0, 2);

        if (reverse == 0) return word;
        else return new StringBuilder(word).reverse().toString();
    }

    private void showBoard(){
        for (int i = 0; i < DIMENSION; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
    }
}
