package UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameScreen extends JFrame {

    private ArrayList<JButton> buttonList = new ArrayList<>();

    public GameScreen(char[][] wordGrid){
        setLayout(new GridLayout(wordGrid.length, wordGrid.length));

        int i = 0;
        for ( char[] row: wordGrid) {
            for( char cell : row){
                buttonList.add(new JButton(String.valueOf(cell)));
                add(buttonList.get(i));
                i++;
            }
        }

        //setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1920,1080);
        setVisible(true);
    }
}
