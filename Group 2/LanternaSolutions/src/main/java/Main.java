import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.io.IOException;
import java.util.Random;
public class Main {
    static Screen screen;
    final static char block = '\u2588';
    final static char player = 'X';
    static int x = 1; //col
    static int y = 22;//row
    static int a = 78; //col
    static int b = 21;//row
    final static char arrow = '\u21db'; //
    final static char bomb = '\u1F4A'; //

    public static void main(String[] args) throws Exception {

        screen = new Screen();
        screen.printToScreen(x, y, player);
        screen.printToScreen(a, b, arrow);

        printBoundries();
        mazeObstacles();
        bombNumber();

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
            int oldX = x;
            int oldY = y;
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
            if (screen.getChar(x, y) == block) {
                x = oldX;
                y = oldY;
            } else if (screen.getChar(x, y) == bomb) {
                screen.printToScreen(35, 12, 'B');
                screen.printToScreen(36, 12, 'A');
                screen.printToScreen(37, 12, 'N');
                screen.printToScreen(38, 12, 'G');
                screen.printToScreen(39, 12, ',');
                screen.printToScreen(40, 12, 'G');
                screen.printToScreen(41, 12, 'A');
                screen.printToScreen(42, 12, 'M');
                screen.printToScreen(43, 12, 'E');
                screen.printToScreen(44, 12, ' ');
                screen.printToScreen(45, 12, 'O');
                screen.printToScreen(46, 12, 'V');
                screen.printToScreen(47, 12, 'E');
                screen.printToScreen(48, 12, 'R');
                screen.printToScreen(49, 12, '!');
                Thread.sleep(5000);
                screen.close();
                continueReadingInput = false;
            } else {
                screen.printToScreen(oldX, oldY, ' ');
                screen.printToScreen(x, y, player);
            }
            Position p = new Position(a, b);
            if ((p.x == x && p.y == y)) {
                screen.printToScreen(35, 12, 'G');
                screen.printToScreen(36, 12, 'A');
                screen.printToScreen(37, 12, 'M');
                screen.printToScreen(38, 12, 'E');
                screen.printToScreen(39, 12, ' ');
                screen.printToScreen(40, 12, 'W');
                screen.printToScreen(41, 12, 'O');
                screen.printToScreen(42, 12, 'N');
                screen.printToScreen(43, 12, '!');
                Thread.sleep(5000);
                screen.close();
                continueReadingInput = false;
            }
        }
    }
    public static void printBoundries() throws IOException {

        for (int i = 0; i < 78; i++) {                          // printing horizontal boundary
            screen.printToScreen(i + 1, 23, block);
            screen.printToScreen(i + 1, 1, block);
        }
        for (int i = 0; i < 20; i++) {                          // print vertical boundary
            screen.printToScreen(1, i + 1, block);
            screen.printToScreen(79, i + 1, block);
        }
    }

    public static void mazeObstacles() throws IOException {
        for (int i = 0; i < 10; i++) {
            screen.printToScreen(i + 1, 20, block);
            screen.printToScreen(i + 6, 6, block);
        }
        for (int i = 0; i < 10; i++) {
            screen.printToScreen(15, i + 6, block);//right corner curve
        }

        for (int i = 0; i < 6; i++) {
            screen.printToScreen(i + 30, 16, block);// L shape
            screen.printToScreen(i + 15, 16, block);// Z Shape
            screen.printToScreen(i + 24, 10, block);// long T shap
        }
        for (int i = 0; i < 16; i++) { // 16 to 5 and 5 to16
            screen.printToScreen(30, i + 1, block); // vertical from horizontal boundar
        }
        for (int i = 0; i < 5; i++) { // 16 to 5 and 5 to16
            screen.printToScreen(36, i + 16, block);
            //screen.printToScreen(21, i + 19, block);
        }
        for (int i = 0; i < 8; i++) { // continuation of z shape
            screen.printToScreen(21, i + 16, block);
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
    public static void bombNumber() throws IOException {
        Position bombPosition;
        for (int i = 0; i < 100; i++) {
            Random r = new Random();
            bombPosition = new Position(r.nextInt(76) + 2, r.nextInt(20) + 2);
            if (screen.getChar(bombPosition.x, bombPosition.y) != block) {
                screen.printToScreen(bombPosition.x, bombPosition.y, bomb);
            }
        }
    }
}