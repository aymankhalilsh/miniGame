import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Random;

public class Screen {

    char [][] array =new char[80][24];

    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
    Terminal terminal;
    public void printToScreen(int col, int row, char c) throws IOException {

        terminal.setCursorPosition(col, row);
        terminal.putCharacter(c);
        array[col-1][row-1] =c;
        terminal.flush();

    }

    public KeyStroke getInput() throws IOException {
        return terminal.pollInput();
    }

    public char getChar(int col, int row){

        return array[col-1][row-1];

    }

    public Screen() throws IOException {
        terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

    }

    public void close() throws IOException {
       terminal.close();
    }

/*    public void bomb(){
        Random r = new Random();
        Position bombPosition = new Position(r.nextInt(80), r.nextInt(24));
      *//*  terminal.setCursorPosition(bombPosition.x, bombPosition.y);
        terminal.putCharacter(bomb);*//*
       printToScreen(bombPosition.x, bombPosition.y, );

        terminal.flush();
    }*/
}
