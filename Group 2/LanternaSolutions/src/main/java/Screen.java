import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Random;

public class Screen {

    public static final TextColor
            WHITE = new TextColor.RGB(192, 192, 192),
            BLACK = new TextColor.RGB(0, 0, 0),
            BLUE = new TextColor.RGB(0, 0, 255),
            YELLOW = new TextColor.RGB(255, 255, 0),
            GREEN = new TextColor.RGB(0, 255, 0),
            RED = new TextColor.RGB(255, 0, 0);
    char[][] array = new char[80][24];
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
    Terminal terminal;

    public void printToScreen(int col, int row, char c, TextColor foreGround, TextColor backGround) throws IOException {

        terminal.setCursorPosition(col, row);
        terminal.setForegroundColor(foreGround);
        terminal.setBackgroundColor(backGround);
        terminal.putCharacter(c);
        array[col - 1][row - 1] = c;
        terminal.flush();
    }

    public KeyStroke getInput() throws IOException {
        return terminal.pollInput();
    }

    public char getChar(int col, int row) {

        return array[col - 1][row - 1];

    }

    public Screen() throws IOException {
        terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

    }

    public void close() throws IOException {
        terminal.close();
    }

    public void printGameWon(int col, int row, String s, TextColor forGround, TextColor backGround) throws IOException {
        for (int j = 0; j < s.length(); j++) {
            printToScreen(col + j, row, s.charAt(j), forGround, backGround);

        }
    }

    public void printGameOver(int col, int row, String s, TextColor forGround, TextColor backGround) throws IOException {
        for (int j = 0; j < s.length(); j++) {
            printToScreen(col + j, row, s.charAt(j), forGround, backGround);
        }
    }
}
