import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private int DIMENSION;//Tamanho do tabuleiro
    private char[][] grid;
    private int X, Y;//Posição inicial da palavra, para cada palavra X e Y são atulizados

    Board(int dimension, ArrayList<String> wordColletion){
        DIMENSION = dimension;
        grid = new char[DIMENSION][DIMENSION];// Cada posição é iniciada com 0;
        placeWord(wordColletion);
        fillEmptySpace();
        showBoard();
    }

    /**
     * Retorna a matriz que representa o board
     * @return a matriz que representa o tabuleiro
     */
    public char[][] getGrid(){
        return grid;
    }

    /**
     * Preenche os espaços vazios da matriz com letras aleatorias
     */
    private void fillEmptySpace(){
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if(grid[i][j] == 0) grid[i][j] = ' ';// Agora preenche com um espaço vazio para questoes de teste e visualização
                //Descomentar a linha de baixo para colocar letras aleatorias
                //if(grid[i][j] == 0) grid[i][j] = (char)ThreadLocalRandom.current().nextInt(65, 91);
            }
        }
    }

    /**
     * Metodo inicial que recebe a lista de palavras a serem inseridas na matriz
     * Organiza a orientação da palavra (com o metodo getOrientation) e se ira ser invertidada (com o metodo reverseWord)
     * Verifica se a posição é valida, com setLocation
     * Por fim coloca a palavra da matriz
     * @param wordColletion lista que palavras que vão ser adicionadas no tabuleiro
     */
    private void placeWord( ArrayList<String> wordColletion){
        for (String originalWord : wordColletion) {
            int orientation = getOrientation();
            String word = reverseWord(originalWord);
            setLocation(word, orientation);
            placeWordUtil(word, orientation);
        }

    }

    /**
     * Chama o motedo de acordo com a orientação passada no argumento para colocar a palavra na matriz
     * @param word palavra que vai ser adicionada
     * @param orientation orientação da palavra em releção ao tabuleiro
     */
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

    //

    /**
     * Define o local em que as palavras podem ser colocadas baseado no tamanho da palavra e no tamnho da matriz
     * @param word a palavra que vai ser adionada
     * @param orientation a orientação da palavra em relação ao tabuleiro
     */
    private void setLocation(String word, int orientation){
        boolean valid;
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

    /**
     * Verifica se a posição da palavra é valida, verificando se a nova palavra não esta substituindo nenhuma letra das palavras anteriores
     * @param word a palavra que vai ser adicionada
     * @param x posição X da palavra no tabuleiro
     * @param y posição Y da palavra no tabuleiro
     * @param orientation qual a orientação da palavra( horizontal, vertical ou diagonal), gerado pelo metodo getOrientation()
     * @return true caso a posição seja valida ou false caso a posição não seja valida
     */
    private boolean isPositionValid(String word, int x, int y, int orientation){
        switch (orientation){
            case 0:
                for (int i = 0; i < word.length(); i++) {
                    //Se a posição em que a letra vai ser colocada for diferente de 0(vazio)
                    //E se a letra for diferente que vai ser colocada é diferente da letra que já esta na posição, então retorna false
                    //A mesma logica se aplica nas verificações posteriores
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



    /**
     * Define a orientação da palavra, 0 - horizontal | 1 - vertical | 2 - diagonal | 3 - diagonal secundaria
     * @return retorna um numero inteiro entre 0 e 4
     */
    private int getOrientation(){
        return ThreadLocalRandom.current().nextInt(0, 4);
    }

    /**
     * Coloca a string na posição horizontal dada uma posição inicial
     *      * * * * * * * * *
     *      * S T R I N G * *
     *      * * * * * * * * *
     *      * * * * * * * * *
     *      * * * * * * * * *
     *      * * * * * * * * *
     * @param word palavra que vai ser adicionada
     * @param x posição X inicial da palavra
     * @param y posição Y inicial da palavra
     */
    private void placeHorizontal(String word, int x, int y){
        for (int i = 0; i < word.length(); i++) {
            grid[x][y] = word.charAt(i);
            x++;
        }
    }

    /**
     * Coloca a string na posição vertical dada uma posição inicial
     *      * * * S * * * * *
     *      * * * T * * * * *
     *      * * * R * * * * *
     *      * * * I * * * * *
     *      * * * N * * * * *
     *      * * * G * * * * *
     * @param word palavra que vai ser adicionada
     * @param x posição X inicial da palavra
     * @param y posição Y inicial da palavra
     */
    private void placeVertical(String word, int x, int y){
        for (int i = 0; i < word.length(); i++) {
            grid[x][y] = word.charAt(i);
            y++;
        }
    }

    /**
     * Coloca a string na posição diagonal dada uma posição inicial
     *      * S * * * * * * *
     *      * * T * * * * * *
     *      * * * R * * * * *
     *      * * * * I * * * *
     *      * * * * * N * * *
     *      * * * * * * G * *
     * @param word palavra que vai ser adicionada
     * @param x posição X inicial da palavra
     * @param y posição Y inicial da palavra
     */
    private void placeDiagonal(String word, int x, int y){
        for (int i = 0; i < word.length(); i++) {
            grid[x][y] = word.charAt(i);
            x++;
            y++;
        }
    }

    /**
     * Coloca a string na posição diagonal secundaria dada uma posição inicial
     *      * * * * * * * S *
     *      * * * * * * T * *
     *      * * * * * R * * *
     *      * * * * I * * * *
     *      * * * N * * * * *
     *      * * G * * * * * *
     * @param word palavra que vai ser adicionada
     * @param x posição X inicial da palavra
     * @param y posição Y inicial da palavra
     */
    private void placeSecondaryDiagonal(String word, int x, int y){
        for (int i = 0; i < word.length(); i++) {
            grid[x][y] = word.charAt(i);
            x--;
            y++;
        }
    }

    /**
     * Define se a string recebida vai ser invertida, e retorna a string
     * @param word palavra original
     * @return retorna a nova palavra, invertida ou não
     */
    private String reverseWord(String word){
        int reverse = ThreadLocalRandom.current().nextInt(0, 2);

        if (reverse == 0) return word;
        else return new StringBuilder(word).reverse().toString();
    }

    /**
     * Mostra a matriz no console
     */
    private void showBoard(){
        for (int i = 0; i < DIMENSION; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
    }
}
