import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.Random;

public class Main {
    static Screen screen;

    static int x = 3; //col
    static int y = 7;//row
    final char bomb = '\u1F4A'; //
    final char block2 = '\u2588';
    final static char player = 'X';
    final static char block = '\u2588';

    public static void main(String[] args) throws Exception {
        screen = new Screen();

        screen.printToScreen(x, y, player);
        pirntBoundries();

        for (int i = 0; i < 50; i++) {
            screen.printToScreen(i+3, 3, block);
            screen.printToScreen(i+3, 5, block);
            screen.printToScreen(3, i+10, block);
            screen.printToScreen(25, i+5, block);
            screen.printToScreen(i+3, 9, block);
            screen.printToScreen(i+5, 7, block);
            screen.printToScreen(3, 3, block);
        }

        boolean continueReadingInput = true;
        while (continueReadingInput) {

            KeyStroke keyStroke = null;
            do {
                Thread.sleep(5); // might throw InterruptedException
                keyStroke = screen.getInput();
            } while (keyStroke == null);

            KeyType type = keyStroke.getKeyType();
            Character c = keyStroke.getCharacter(); // used Character instead of char because it might be null

            System.out.println("keyStroke.getKeyType(): " + type + " keyStroke.getCharacter(): " + c);

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

//             ***** more than one if else statement solved

            if (screen.getChar(x, y) == block || screen.getChar(x, y) == block || screen.getChar(x, y) == block) {
                x = oldX;
                y = oldY;
            } else {
//
                screen.printToScreen(oldX, oldY, ' ');
                screen.printToScreen(x, y, player);
            }
//            if (bombPosition.x == x && bombPosition.y == y) {
//             screen.close();
//                continueReadingInput = false;
//            }
        }
    }

    public static void pirntBoundries() throws IOException {
        for (int i = 0; i < 78; i++) {                          // printing horizontal boundary
            screen.printToScreen(i + 1, 24, block);
            screen.printToScreen(i + 1, 1, block);
        }
        for (int i = 0; i < 24; i++) {                          // print vertical boundary
            screen.printToScreen(1, i + 1, block);
            screen.printToScreen(79, i + 1, block);
        }
    }

}