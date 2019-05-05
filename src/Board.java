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


    //Retorna a matriz que representa o board
    public char[][] getGrid(){
        return grid;
    }

    //Preenche os espaços vazios da matriz com letras aleatorias
    private void fillEmptySpace(){
        Random random = new Random();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                //gridButton[i][j] = (char)ThreadLocalRandom.current().nextInt(65, 91);
                if(grid[i][j] == 0) grid[i][j] = ' ';
            }
        }
    }


    //Metodo inicial que recebe a lista de palavras a serem inseridas na matriz
    //Organiza a orientação da palavra (com o metodo getOrientation) e se ira ser invertidada (com o metodo reverseWord)
    //Verifica se a posição é valida, com setLocation
    //Por fim coloca a palavra da matriz
    private void placeWord( ArrayList<String> wordColletion){
        for (String originalWord : wordColletion) {
            int orientation = getOrientation();
            String word = reverseWord(originalWord);
            setLocation(word, orientation);
            placeWordUtil(word, orientation);
        }

    }

    //Chama o motedo de acordo a a orientação passada no argumento para colocar a palavra na matriz
    private void placeWordUtil(String word, int orientation){
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

    //Define o range em que as palavras podem ser colocadas paseado no tamanho da palavra e no tamnho da matriz
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

    private boolean isPositionValid(String word, int x, int y, int orientation){
        switch (orientation){
            case 0:
                for (int i = 0; i < word.length(); i++) {
                    if((grid[x][y] != 0) && (grid[x][y] != word.charAt(i))){
                        return false;
                    }
                    x++;
                }
                break;
            case 1:
                for (int i = 0; i < word.length(); i++) {
                    if((grid[x][y] != 0) && (grid[x][y] != word.charAt(i))){
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
            case 3:
                for (int i = 0; i < word.length(); i++) {
                    if((grid[x][y] != 0) && (grid[x][y] != word.charAt(i))){
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

    //Define a orientação da palavra, 0 - horizontal | 1 - vertical | 2 - diagonal | 3 - diagonal secundaria
    private int getOrientation(){
        return ThreadLocalRandom.current().nextInt(0, 4);
    }


    //Coloca a string na posição horizontal dada uma posição inicial
    /*
     * * * * * * * * *
     * S T R I N G * *
     * * * * * * * * *
     * * * * * * * * *
     * * * * * * * * *
     * * * * * * * * *
     */
    private void placeHorizontal(String word, int x, int y){
        for (int i = 0; i < word.length(); i++) {
            grid[x][y] = word.charAt(i);
            x++;
        }
    }


    //Coloca a string na posição vertical dada uma posição inicial
    /*
         * * * S * * * * *
         * * * T * * * * *
         * * * R * * * * *
         * * * I * * * * *
         * * * N * * * * *
         * * * G * * * * *
     */
    private void placeVertical(String word, int x, int y){
        for (int i = 0; i < word.length(); i++) {
            grid[x][y] = word.charAt(i);
            y++;
        }
    }

    //Dada uma posição colocar a string na matriz 'grid' no formato de diagonal principal
    /*
         * S * * * * * * *
         * * T * * * * * *
         * * * R * * * * *
         * * * * I * * * *
         * * * * * N * * *
         * * * * * * G * *
     */

    private void placeDiagonal(String word, int x, int y){
        for (int i = 0; i < word.length(); i++) {
            grid[x][y] = word.charAt(i);
            x++;
            y++;
        }
    }

    //Dada uma posição colocar a string na matriz 'grid' no formato de diagonal secundaria
    /*
        * * * * * * * S *
        * * * * * * T * *
        * * * * * R * * *
        * * * * I * * * *
        * * * N * * * * *
        * * G * * * * * *
     */
    private void placeSecondaryDiagonal(String word, int x, int y){
        for (int i = 0; i < word.length(); i++) {
            grid[x][y] = word.charAt(i);
            x--;
            y++;
        }
    }

    //Define se a string recebida vai ser invertida, e retorna a string (invertida ou não, 50% de chance)
    private String reverseWord(String word){
        int reverse = ThreadLocalRandom.current().nextInt(0, 2);

        if (reverse == 0) return word;
        else return new StringBuilder(word).reverse().toString();
    }

    //Mostra a matriz no console
    private void showBoard(){
        for (int i = 0; i < DIMENSION; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
    }
}
