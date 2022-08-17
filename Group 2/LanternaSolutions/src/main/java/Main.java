import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.Random;

public class Main {
    static Screen screen;
    final static char block = '\u2588';

    public static void main(String[] args) throws Exception {

        int x = 1; //col
        int y = 22;//row
        int a = 78; //col
        int b = 21;//row
        final char arrow = '\u21db'; //
        final char bomb = '\u1F4A'; //
        //final char bomb2 = '\u2F2E'; // monster
        final char block2 = '\u2588';
        final char player = 'X';


        screen = new Screen();
        screen.printToScreen(x, y, player);
        pirntBoundries();

        screen.printToScreen(a, b, arrow);
        pirntBoundries();

        Position bombPosition = null;
        for(int i =3; i<15;i++){
            Random r = new Random();
            bombPosition = new Position(r.nextInt(76), r.nextInt(20));
            screen.printToScreen(bombPosition.x, bombPosition.y,bomb);}

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


            Position p = new Position(a, b);
            if ((bombPosition.x == x && bombPosition.y == y)||(p.x == x && p.y == y)) { // add 2nd bomb statement
                screen.close();
                continueReadingInput = false;
            }
        }
    }

    public static void pirntBoundries() throws IOException {

        for (int i = 0; i < 10; i++) {
            screen.printToScreen(i + 1, 20, block);
            screen.printToScreen(i + 6, 6, block);

        }

        for (int i = 0; i < 10; i++) {
            screen.printToScreen(15, i +6, block);//right corner curve
            //screen.printToScreen(i + 6, 6, block);

        }

        for (int i = 0; i < 6; i++) {
            screen.printToScreen(i + 30, 16, block);// L shape
            screen.printToScreen(i + 15, 16, block);// Z Shape
            screen.printToScreen(i + 24, 10, block);// long T shap

        }
        for (int i = 0; i < 16; i++){ // 16 to 5 and 5 to16
            screen.printToScreen(30, i + 1, block); // vertical from horizontal boundar
        }
        for (int i = 0; i < 5; i++){ // 16 to 5 and 5 to16
            screen.printToScreen(36, i + 16, block);
            //screen.printToScreen(21, i + 19, block);
        }
        for (int i = 0; i < 8; i++){ // continuation of z shape
            screen.printToScreen(21, i + 16, block);
        }
        for (int i = 0; i < 78; i++) {                          // printing horizontal boundary
            screen.printToScreen(i + 1, 23, block);
            screen.printToScreen(i + 1, 1, block);
        }
        for (int i = 0; i < 20; i++) {                          // print vertical boundary
            screen.printToScreen(1, i + 1, block);
            screen.printToScreen(79, i + 1, block);
        }

        for (int i = 0; i < 8; i++) {                          // print 2 vertical boundary on right
            screen.printToScreen(70, i + 1, block);
            screen.printToScreen(70, i + 16, block);
        }

        for (int i = 0; i < 20; i++) {                          // printing horizontal across vertical boundary
            screen.printToScreen(i + 59, 11, block);
            screen.printToScreen(i + 39, 5, block);//long horizontal
        }

        for (int i = 0; i < 15; i++) {                          // print 2 vertical crossing horizontal
            screen.printToScreen(59, i + 5, block);
            screen.printToScreen(45, i + 9, block);
        }

        for (int i = 0; i < 10; i++) {                          // print 2 vertical crossing horizontal
            screen.printToScreen(39, i + 5, block);
        }
    }
}