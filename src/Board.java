import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private int DIMENSION;
    private char[][] grid;
    private int X, Y;

    public Board(int dimension, ArrayList<String> wordColletion){
        DIMENSION = dimension;
        grid = new char[DIMENSION][DIMENSION];// Cada posição é iniciada com 0;
        placeWord(wordColletion);
        fillEmptySpace();
        showBoard();
    }

    public char[][] getGrid(){
        return grid;
    }

    private void fillEmptySpace(){
        Random random = new Random();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                //gridButton[i][j] = (char)ThreadLocalRandom.current().nextInt(65, 91);
                if(grid[i][j] == 0) grid[i][j] = ' ';
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
        System.out.printf("wordOriginal: %s | word: %s | orientarion: %d\n", originalWord, word, orientation);
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
            case 3:
                placeSecondaryDiagonal(word, X, Y);
                break;
        }
    }

    private void setLocation(String word, int orientation){
        Boolean valid;
        switch (orientation){
            case 0: //Palavra na horizontal
                do{
                    //Caso a palavra seja do tamanho da matriz começar o X em 0;
                    if(word.length() == DIMENSION) X = 0; else X = ThreadLocalRandom.current().nextInt(0, DIMENSION  - word.length());
                    Y = ThreadLocalRandom.current().nextInt(0, DIMENSION);
                    valid = isPositionValid(word, X, Y, orientation);

                }while(!valid);
                break;

            case 1: //Palavra na vertical
                do{
                    X = ThreadLocalRandom.current().nextInt(0, DIMENSION);
                    //Caso a palavra seja do tamanho da matriz começar o Y em 0;
                    if(word.length() == DIMENSION) Y = 0; else Y = ThreadLocalRandom.current().nextInt(0, DIMENSION - word.length());
                    valid = isPositionValid(word, X, Y, orientation);

                }while(!valid);
                break;
            case 2: //Palavra da diagonal
                do{
                    //Caso a palavra seja do tamanho da matriz começar o X e Y em 0;
                    if(word.length() == DIMENSION) X = 0; else X = ThreadLocalRandom.current().nextInt(0, DIMENSION - word.length());
                    if(word.length() == DIMENSION) Y = 0; else Y = ThreadLocalRandom.current().nextInt(0, DIMENSION - word.length());
                    valid = isPositionValid(word, X, Y, orientation);
                }while(!valid);
                break;
            case 3: //Palavra na diagonal secundaria
                do {
                    if(word.length() == DIMENSION) X = DIMENSION - 1; else X = ThreadLocalRandom.current().nextInt(DIMENSION - word.length(), DIMENSION);
                    if(word.length() == DIMENSION) Y = DIMENSION - 1; else Y = ThreadLocalRandom.current().nextInt(0, DIMENSION - word.length());
                    valid = isPositionValid(word, X, Y, orientation);
                }while(!valid);
                break;
        }
    }

    //TODO: Verificar se tem intercecção, caso não tenha, a posição é valida, caso tenha intercecção verfircar se a letra "nos pontos" (mais de ponto de encontro) são iguais. Caso diferentes return false, else return true;
    private boolean isPositionValid(String word, int x, int y, int orientation){
        switch (orientation){
            case 0:
                for (int i = 0; i < word.length(); i++) {
                    System.out.printf("wrod: %s | grid[x][y] = %c | word.charAt(i) = %c\n",word, grid[x][y], word.charAt(i));
                    if((grid[x][y] != 0) && grid[x][y] != word.charAt(i)){
                        System.out.printf("word: %s invalida\n", word);
                        return false;
                    }
                    x++;
                }
                break;
            case 1:
                for (int i = 0; i < word.length(); i++) {
                    System.out.printf("wrod: %s | grid[x][y] = %c | word.charAt(i) = %c\n",word, grid[x][y], word.charAt(i));
                    if((grid[x][y] != 0) && grid[x][y] != word.charAt(i)){
                        System.out.printf("word: %s invalida\n", word);
                        return false;
                    }
                    y++;
                }
                break;
            case 2:
                for (int i = 0; i < word.length(); i++) {
                    System.out.printf("wrod: %s | grid[x][y] = %c | word.charAt(i) = %c\n",word, grid[x][y], word.charAt(i));
                    if((grid[x][y] != 0) && (grid[x][y] != word.charAt(i))){
                        System.out.printf("word: %s invalida\n", word);
                        return false;
                    }
                    x++;
                    y++;
                }
                break;
            case 3:
                for (int i = 0; i < word.length(); i++) {
                    System.out.printf("wrod: %s | grid[x][y] = %c | word.charAt(i) = %c\n",word, grid[x][y], word.charAt(i));
                    if((grid[x][y] != 0) && (grid[x][y] != word.charAt(i))){
                        System.out.printf("word: %s invalida\n", word);
                        return false;
                    }
                    x--;
                    y++;
                }
                break;
            default:
                System.out.println("isPositionValid != 0, 1 ou 2");
                break;

        }
        return true;
    }

    //Orientação da palavra, 0 - horizontal | 1 - vertical | 2 - diagonal | 3 - diagonal secundaria
    private int getOrientation(){
        return ThreadLocalRandom.current().nextInt(0, 4);
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

    private void placeSecondaryDiagonal(String word, int x, int y){
        for (int i = 0; i < word.length(); i++) {
            grid[x][y] = word.charAt(i);
            x--;
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
