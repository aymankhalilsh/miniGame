import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.Random;

public class Main {
    static Screen screen;

    public static void main(String[] args) throws Exception {

        screen  = new Screen();
        int x = 10; //col
        int y = 5;//row
        final char bomb = '\u1F4A'; //3
        final char player = 'X';
        final char mountain = '\u26F0';
        final char river = '\u2F2E'; //(U+2F2E)
        final char building = 'B';//(U+1F3E2)
        screen.printToScreen(x,y,player);

        // Use obsticles array to print to lanterna
        for (int i = 0;i<10;i++){
           /* terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter(mountain);*/
            screen.printToScreen(i+10,10,mountain);
        }

        for(int i = 0;i<5;i++){
         /*   terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter(river);*/

            screen.printToScreen(i+20,20,river);
        }

        for(int i = 0;i<10;i++){
         /*   terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter(river);*/

            screen.printToScreen(i+40,10,building);
        }

        Random r = new Random();
        Position bombPosition = new Position(r.nextInt(80), r.nextInt(24));
        screen.printToScreen(bombPosition.x, bombPosition.y,bomb);

        // Task 11
        boolean continueReadingInput = true;
        while (continueReadingInput) {

            KeyStroke keyStroke = null;
            do {
                Thread.sleep(5); // might throw InterruptedException
                keyStroke = screen.getInput();
            } while (keyStroke == null);


            KeyType type = keyStroke.getKeyType();
            Character c = keyStroke.getCharacter(); // used Character instead of char because it might be null

            System.out.println("keyStroke.getKeyType(): " + type
                    + " keyStroke.getCharacter(): " + c);

            if (c == Character.valueOf('q')) {
                continueReadingInput = false;
              screen.close();
                System.out.println("quit");
            }

            int oldX = x; // save old position x
            int oldY = y; // save old position y
            switch (keyStroke.getKeyType()) {
                case ArrowDown:
                    y += 1;
                    break;
                case ArrowUp:
                    y -= 1;
                    break;
                case ArrowRight:
                    x += 1;
                    break;
                case ArrowLeft:
                    x -= 1;
                    break;
            }

            // ***** more than one if else statement solved

            if (screen.getChar(x,y)==mountain || screen.getChar(x,y)==river|| screen.getChar(x,y)==building) {
                x = oldX;
                y = oldY;
            }
            else {

                screen.printToScreen(oldX,oldY,' ');
                screen.printToScreen(x,y,player);
            }
            if (bombPosition.x == x && bombPosition.y == y) {
             screen.close();
                continueReadingInput = false;
            }

            }
        }

    }

