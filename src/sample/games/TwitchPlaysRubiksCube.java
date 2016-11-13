package sample.games;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sample.utils.Constants;
import sample.utils.MathUtils;
import sample.views.GenericViews;

import java.util.ArrayList;
import java.util.Random;
import static sample.utils.Constants.*;

public class TwitchPlaysRubiksCube extends GameBase {
    public int rotations = 0;

    public int[] up = new int[8];
    public int[] down = new int[8];
    public int[] left = new int[8];
    public int[] right = new int[8];
    public int[] front = new int[8];
    public int[] back = new int[8];
    

    public TwitchPlaysRubiksCube(Scene scene) {
        super(scene, Constants.RUBIKS_CUBE);
    }

    @Override
    public void start() {
        super.start();
        resetCube();
        drawScreen();
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        super.onMessage(channel, sender, login, hostname, message);
        String s = message.toUpperCase().replace(" ", "");
        boolean reverse = false;
        if (s.contains(",")) {
            s = s.replace(",", "");
            reverse = true;
        }
        if (KeyCode.getKeyCode(s) != null) {
            if (handleKeyInput(KeyCode.getKeyCode(s), reverse)) {
                messages.add(sender + ": " + message);
                if (this.messages.size() > 47)
                    messages.remove(0);
                drawScreen();
            }
        }
    }

    @Override
    public void drawScreen() {
        GridPane pane = new GridPane();
        Pane pane1 = drawCube();
        Text t = new Text("Rotations: " + rotations);
        t.setFont(new Font(15D));
        pane.add(t, 0, 0);
        pane.add(drawCube3d(200, 200, 200, true), 1, 1);
        pane.add(pane1, 2, 1);
        pane.add(drawCube3d(150, 200, 200, false), 3, 1);
        pane.add(GenericViews.chat(messages), 4, 1);
        scene.setRoot(pane);
    }

    @Override
    public boolean handleKeyInput(KeyCode keyCode, boolean reverse) {
        switch (keyCode) {
            case U:
                if (reverse)
                    rotateReverseUp();
                else
                    rotateUp();
                rotations++;
                return true;
            case R:
                if (reverse)
                    rotateReverseRight();
                else
                    rotateRight();
                rotations++;
                return true;
            case L:
                if (reverse)
                    rotateReverseLeft();
                else
                    rotateLeft();
                rotations++;
                return true;
            case D:
                if (reverse)
                    rotateReverseDown();
                else
                    rotateDown();
                rotations++;
                return true;
            case B:
                if (reverse)
                    rotateReverseBack();
                else
                    rotateBack();
                rotations++;
                return true;
            case F:
                if (reverse)
                    rotateReverseFront();
                else
                    rotateFront();
                rotations++;
                return true;
            case BACK_SPACE:
                scrambleCube();
                rotations = 0;
                return true;
        }
        return false;
    }

    public void scrambleCube() {
        KeyCode[] keys = new KeyCode[]{KeyCode.U, KeyCode.R, KeyCode.L, KeyCode.D, KeyCode.B, KeyCode.F};
        Random random = new Random();
        for (int i = 0; i < 80; i++)
            handleKeyInput(keys[random.nextInt(keys.length)], random.nextBoolean());
        drawScreen();
    }



    public Pane drawCube3d(int x, int y, double size, boolean topView) {
        Line l1 = GenericViews.makeLine(x, y, 90D, size);
        Line l2 = GenericViews.makeLine(x, y, -30D, size);
        Line l3 = GenericViews.makeLine(x, y, -150D, size);

        Line l4 = GenericViews.makeLine(l2.getEndX(), l2.getEndY(), 90D, size);
        Line l5 = new Line(l4.getEndX(), l4.getEndY(), l1.getEndX(), l1.getEndY());

        Line l6 = GenericViews.makeLine(l3.getEndX(), l3.getEndY(), 90D, size);
        Line l7 = new Line(l6.getEndX(), l6.getEndY(), l1.getEndX(), l1.getEndY());

        Line l8 = GenericViews.makeLine(l2.getEndX(), l2.getEndY(), -150D, size);
        Line l9 = new Line(l8.getEndX(), l8.getEndY(), l3.getEndX(), l3.getEndY());

        Line l10 = GenericViews.makeLine(l1, l4, 90D, 90D, size / 3);
        Line l11 = GenericViews.makeLine(l1, l4, 90D, 90D, size / 3 * 2);

        Line l12 = GenericViews.makeLine(l3, l7, -150D, MathUtils.angleFromLine(l7), size / 3, MathUtils.lengthFromLine(l7) / 3 * 2);
        Line l13 = GenericViews.makeLine(l3, l7, -150D, MathUtils.angleFromLine(l7), size / 3 * 2, MathUtils.lengthFromLine(l7) / 3);

        Line l14 = GenericViews.makeLine(l2, l5, -30D, MathUtils.angleFromLine(l5), size / 3, -MathUtils.lengthFromLine(l5) / 3 * 2);
        Line l15 = GenericViews.makeLine(l2, l5, -30D, MathUtils.angleFromLine(l5), size / 3 * 2, -MathUtils.lengthFromLine(l5) / 3);

        Line l16 = GenericViews.makeLine(l1, l6, 90D, 90D, size / 3);
        Line l17 = GenericViews.makeLine(l1, l6, 90D, 90D, size / 3 * 2);

        Line l18 = GenericViews.makeLine(l3, l8, -150D, -150D, size / 3);
        Line l19 = GenericViews.makeLine(l3, l8, -150D, -150D, size / 3 * 2);

        Line l20 = GenericViews.makeLine(l2, l9, -30D, MathUtils.angleFromLine(l9), size / 3, -MathUtils.lengthFromLine(l9) / 3 * 2);
        Line l21 = GenericViews.makeLine(l2, l9, -30D, MathUtils.angleFromLine(l9), size / 3 * 2, -MathUtils.lengthFromLine(l9) / 3);
        Pane pane;
        if (topView) {
            Group g1 = GenericViews.fillArea(l6, l3, l13, l16, front[0]);
            Group g2 = GenericViews.fillArea(l6, l16, l13, l17, front[7]);
            Group g3 = GenericViews.fillArea(l6, l17, l13, l7, front[6]);
            Group g4 = GenericViews.fillArea(l13, l3, l12, l16, front[1]);
            Group g5 = GenericViews.fillArea(l13, l16, l12, l17, ORANGE, "F");
            Group g6 = GenericViews.fillArea(l13, l17, l12, l7, front[5]);
            Group g7 = GenericViews.fillArea(l12, l3, l1, l16, front[2]);
            Group g8 = GenericViews.fillArea(l12, l16, l1, l17, front[3]);
            Group g9 = GenericViews.fillArea(l12, l17, l1, l7, front[4]);

            Group g10 = GenericViews.fillArea(l1, l2, l14, l10, right[0]);
            Group g11 = GenericViews.fillArea(l1, l10, l14, l11, right[7]);
            Group g12 = GenericViews.fillArea(l1, l11, l14, l5, right[6]);
            Group g13 = GenericViews.fillArea(l14, l2, l15, l10, right[1]);
            Group g14 = GenericViews.fillArea(l14, l10, l15, l11, GREEN, "R");
            Group g15 = GenericViews.fillArea(l14, l11, l15, l5, right[5]);
            Group g16 = GenericViews.fillArea(l15, l2, l4, l10, right[2]);
            Group g17 = GenericViews.fillArea(l15, l10, l4, l11, right[3]);
            Group g18 = GenericViews.fillArea(l15, l11, l4, l5, right[4]);


            Group g19 = GenericViews.fillArea(l9, l8, l19, l21, up[0]);
            Group g20 = GenericViews.fillArea(l9, l21, l19, l20, up[7]);
            Group g21 = GenericViews.fillArea(l9, l20, l19, l3, up[6]);
            Group g22 = GenericViews.fillArea(l19, l8, l18, l21, up[1]);
            Group g23 = GenericViews.fillArea(l19, l21, l18, l20, WHITE, "U");
            Group g24 = GenericViews.fillArea(l19, l20, l18, l3, up[5]);
            Group g25 = GenericViews.fillArea(l18, l8, l2, l21, up[2]);
            Group g26 = GenericViews.fillArea(l18, l21, l2, l20, up[3]);
            Group g27 = GenericViews.fillArea(l18, l20, l2, l3, up[4]);

            pane = new Pane(g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12, g13, g14, g15, g16, g17, g18, g19, g20, g21, g22, g23, g24, g25, g26, g27,
                    l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20, l21);
        } else {
            Group g1 = GenericViews.fillArea(l6, l3, l13, l16, left[4]);
            Group g2 = GenericViews.fillArea(l6, l16, l13, l17, left[3]);
            Group g3 = GenericViews.fillArea(l6, l17, l13, l7, left[2]);
            Group g4 = GenericViews.fillArea(l13, l3, l12, l16, left[5]);
            Group g5 = GenericViews.fillArea(l13, l16, l12, l17, BLUE, "L");
            Group g6 = GenericViews.fillArea(l13, l17, l12, l7, left[1]);
            Group g7 = GenericViews.fillArea(l12, l3, l1, l16, left[6]);
            Group g8 = GenericViews.fillArea(l12, l16, l1, l17, left[7]);
            Group g9 = GenericViews.fillArea(l12, l17, l1, l7, left[0]);

            Group g10 = GenericViews.fillArea(l1, l2, l14, l10, back[4]);
            Group g11 = GenericViews.fillArea(l1, l10, l14, l11, back[3]);
            Group g12 = GenericViews.fillArea(l1, l11, l14, l5, back[2]);
            Group g13 = GenericViews.fillArea(l14, l2, l15, l10, back[5]);
            Group g14 = GenericViews.fillArea(l14, l10, l15, l11, RED, "B");
            Group g15 = GenericViews.fillArea(l14, l11, l15, l5, back[1]);
            Group g16 = GenericViews.fillArea(l15, l2, l4, l10, back[6]);
            Group g17 = GenericViews.fillArea(l15, l10, l4, l11, back[7]);
            Group g18 = GenericViews.fillArea(l15, l11, l4, l5, back[0]);


            Group g19 = GenericViews.fillArea(l9, l8, l19, l21, down[2]);
            Group g20 = GenericViews.fillArea(l9, l21, l19, l20, down[1]);
            Group g21 = GenericViews.fillArea(l9, l20, l19, l3, down[0]);
            Group g22 = GenericViews.fillArea(l19, l8, l18, l21, down[3]);
            Group g23 = GenericViews.fillArea(l19, l21, l18, l20, YELLOW, "D");
            Group g24 = GenericViews.fillArea(l19, l20, l18, l3, down[7]);
            Group g25 = GenericViews.fillArea(l18, l8, l2, l21, down[4]);
            Group g26 = GenericViews.fillArea(l18, l21, l2, l20, down[5]);
            Group g27 = GenericViews.fillArea(l18, l20, l2, l3, down[6]);

            pane = new Pane(g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12, g13, g14, g15, g16, g17, g18, g19, g20, g21, g22, g23, g24, g25, g26, g27,
                    l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20, l21);
        }

        return pane;
    }

    


    public Pane drawCube() {

        GridPane gridPane = new GridPane();
        gridPane.add(new Rectangle(64, 64, Color.WHITE), 0, 0);
        gridPane.add(new Rectangle(64, 64, Color.WHITE), 10, 0);

        gridPane.add(GenericViews.createRectangle(back[4]), 4, 0);
        gridPane.add(GenericViews.createRectangle(back[5]), 5, 0);
        gridPane.add(GenericViews.createRectangle(back[6]), 6, 0);
        gridPane.add(GenericViews.createRectangle(back[3]), 4, 1);
        gridPane.add(GenericViews.createRectangle(RED), 5, 1);
        Text b = new Text("     B");
        b.setTextAlignment(TextAlignment.CENTER);
        b.setFont(new Font(20D));
        gridPane.add(b, 5, 1);
        gridPane.add(GenericViews.createRectangle(back[7]), 6, 1);
        gridPane.add(GenericViews.createRectangle(back[2]), 4, 2);
        gridPane.add(GenericViews.createRectangle(back[1]), 5, 2);
        gridPane.add(GenericViews.createRectangle(back[0]), 6, 2);


        gridPane.add(GenericViews.createRectangle(left[6]), 1, 3);
        gridPane.add(GenericViews.createRectangle(left[7]), 2, 3);
        gridPane.add(GenericViews.createRectangle(left[0]), 3, 3);
        gridPane.add(GenericViews.createRectangle(left[5]), 1, 4);
        gridPane.add(GenericViews.createRectangle(BLUE), 2, 4);
        Text l = new Text("     L");
        l.setTextAlignment(TextAlignment.CENTER);
        l.setFont(new Font(20D));
        gridPane.add(l, 2, 4);
        gridPane.add(GenericViews.createRectangle(left[1]), 3, 4);
        gridPane.add(GenericViews.createRectangle(left[4]), 1, 5);
        gridPane.add(GenericViews.createRectangle(left[3]), 2, 5);
        gridPane.add(GenericViews.createRectangle(left[2]), 3, 5);

        gridPane.add(GenericViews.createRectangle(up[0]), 4, 3);
        gridPane.add(GenericViews.createRectangle(up[1]), 5, 3);
        gridPane.add(GenericViews.createRectangle(up[2]), 6, 3);
        gridPane.add(GenericViews.createRectangle(up[7]), 4, 4);
        gridPane.add(GenericViews.createRectangle(WHITE), 5, 4);
        Text u = new Text("     U");
        u.setTextAlignment(TextAlignment.CENTER);
        u.setFont(new Font(20D));
        gridPane.add(u, 5, 4);
        gridPane.add(GenericViews.createRectangle(up[3]), 6, 4);
        gridPane.add(GenericViews.createRectangle(up[6]), 4, 5);
        gridPane.add(GenericViews.createRectangle(up[5]), 5, 5);
        gridPane.add(GenericViews.createRectangle(up[4]), 6, 5);


        gridPane.add(GenericViews.createRectangle(right[2]), 7, 3);
        gridPane.add(GenericViews.createRectangle(right[3]), 8, 3);
        gridPane.add(GenericViews.createRectangle(right[4]), 9, 3);
        gridPane.add(GenericViews.createRectangle(right[1]), 7, 4);
        gridPane.add(GenericViews.createRectangle(GREEN), 8, 4);
        Text r = new Text("     R");
        r.setTextAlignment(TextAlignment.CENTER);
        r.setFont(new Font(20D));
        gridPane.add(r, 8, 4);
        gridPane.add(GenericViews.createRectangle(right[5]), 9, 4);
        gridPane.add(GenericViews.createRectangle(right[0]), 7, 5);
        gridPane.add(GenericViews.createRectangle(right[7]), 8, 5);
        gridPane.add(GenericViews.createRectangle(right[6]), 9, 5);


        gridPane.add(GenericViews.createRectangle(front[0]), 4, 6);
        gridPane.add(GenericViews.createRectangle(front[1]), 5, 6);
        gridPane.add(GenericViews.createRectangle(front[2]), 6, 6);
        gridPane.add(GenericViews.createRectangle(front[7]), 4, 7);
        gridPane.add(GenericViews.createRectangle(ORANGE), 5, 7);
        Text f = new Text("     F");
        f.setTextAlignment(TextAlignment.CENTER);
        f.setFont(new Font(20D));
        gridPane.add(f, 5, 7);
        gridPane.add(GenericViews.createRectangle(front[3]), 6, 7);
        gridPane.add(GenericViews.createRectangle(front[6]), 4, 8);
        gridPane.add(GenericViews.createRectangle(front[5]), 5, 8);
        gridPane.add(GenericViews.createRectangle(front[4]), 6, 8);

        gridPane.add(GenericViews.createRectangle(down[0]), 4, 9);
        gridPane.add(GenericViews.createRectangle(down[1]), 5, 9);
        gridPane.add(GenericViews.createRectangle(down[2]), 6, 9);
        gridPane.add(GenericViews.createRectangle(down[7]), 4, 10);
        gridPane.add(GenericViews.createRectangle(YELLOW), 5, 10);
        Text d = new Text("     D");
        d.setTextAlignment(TextAlignment.CENTER);
        d.setFont(new Font(20D));
        gridPane.add(d, 5, 10);
        gridPane.add(GenericViews.createRectangle(down[3]), 6, 10);
        gridPane.add(GenericViews.createRectangle(down[6]), 4, 11);
        gridPane.add(GenericViews.createRectangle(down[5]), 5, 11);
        gridPane.add(GenericViews.createRectangle(down[4]), 6, 11);

        return gridPane;
    }

    public void resetCube() {
        for (int i = 0; i < 8; i++) {
            up[i] = WHITE;
            down[i] = YELLOW;
            left[i] = BLUE;
            right[i] = GREEN;
            front[i] = ORANGE;
            back[i] = RED;
        }
    }


    public void rotateUp() {
        int[] temp = up.clone();
        for (int i = 0; i < 8; i++) {
            up[i] = temp[(i + 6) % 8];
        }
        int[] tempFront = front.clone();
        int[] tempLeft = left.clone();
        int[] tempRight = right.clone();
        int[] tempBack = back.clone();
        for (int i = 0; i < 3; i++) {
            front[i] = tempRight[i];
            left[i] = tempFront[i];
            back[i] = tempLeft[i];
            right[i] = tempBack[i];
        }
    }

    public void rotateDown() {
        int[] temp = down.clone();
        for (int i = 0; i < 8; i++) {
            down[i] = temp[(i + 6) % 8];
        }

        int[] tempFront = front.clone();
        int[] tempLeft = left.clone();
        int[] tempRight = right.clone();
        int[] tempBack = back.clone();
        for (int i = 4; i < 7; i++) {
            front[i] = tempLeft[i];
            right[i] = tempFront[i];
            back[i] = tempRight[i];
            left[i] = tempBack[i];
        }
    }

    public void rotateLeft() {
        int[] temp = left.clone();
        for (int i = 0; i < 8; i++) {
            left[i] = temp[(i + 2) % 8];
        }

        int[] tempFront = front.clone();
        int[] tempUp = up.clone();
        int[] tempDown = down.clone();
        int[] tempBack = back.clone();
        int x;
        for (int i = 0; i < 3; i++) {
            if (i == 0)
                x = 0;
            else if (i == 1)
                x = 7;
            else
                x = 6;
            up[x] = tempFront[x];
            front[x] = tempDown[x];
        }
        back[2] = tempUp[6];
        back[3] = tempUp[7];
        back[4] = tempUp[0];

        down[0] = tempBack[4];
        down[7] = tempBack[3];
        down[6] = tempBack[2];
    }

    public void rotateRight() {
        int[] temp = right.clone();
        for (int i = 0; i < 8; i++) {
            right[i] = temp[(i + 6) % 8];
        }

        int[] tempFront = front.clone();
        int[] tempUp = up.clone();
        int[] tempDown = down.clone();
        int[] tempBack = back.clone();
        int x;
        for (int i = 0; i < 3; i++) {
            if (i == 0)
                x = 2;
            else if (i == 1)
                x = 3;
            else
                x = 4;
            up[x] = tempFront[x];
            front[x] = tempDown[x];
        }
        back[6] = tempUp[2];
        back[7] = tempUp[3];
        back[0] = tempUp[4];

        down[4] = tempBack[0];
        down[3] = tempBack[7];
        down[2] = tempBack[6];
    }

    public void rotateBack() {
        int[] temp = back.clone();
        for (int i = 0; i < 8; i++) {
            back[i] = temp[(i + 6) % 8];
        }

        int[] tempLeft = left.clone();
        int[] tempUp = up.clone();
        int[] tempDown = down.clone();
        int[] tempRight = right.clone();
        up[2] = tempRight[4];
        up[1] = tempRight[3];
        up[0] = tempRight[2];

        left[0] = tempUp[2];
        left[7] = tempUp[1];
        left[6] = tempUp[0];

        down[6] = tempLeft[0];
        down[5] = tempLeft[7];
        down[4] = tempLeft[6];

        right[2] = tempDown[4];
        right[3] = tempDown[5];
        right[4] = tempDown[6];
    }

    public void rotateFront() {
        int[] temp = front.clone();
        for (int i = 0; i < 8; i++) {
            front[i] = temp[(i + 6) % 8];
        }

        int[] tempLeft = left.clone();
        int[] tempUp = up.clone();
        int[] tempDown = down.clone();
        int[] tempRight = right.clone();
        up[6] = tempLeft[4];
        up[5] = tempLeft[3];
        up[4] = tempLeft[2];

        right[0] = tempUp[6];
        right[7] = tempUp[5];
        right[6] = tempUp[4];

        down[2] = tempRight[0];
        down[1] = tempRight[7];
        down[0] = tempRight[6];

        left[2] = tempDown[0];
        left[3] = tempDown[1];
        left[4] = tempDown[2];
    }

    public void rotateReverseUp() {
        int[] temp = up.clone();
        for (int i = 0; i < 8; i++) {
            up[i] = temp[(i + 2) % 8];
        }
        int[] tempFront = front.clone();
        int[] tempLeft = left.clone();
        int[] tempRight = right.clone();
        int[] tempBack = back.clone();
        for (int i = 0; i < 3; i++) {
            front[i] = tempLeft[i];
            right[i] = tempFront[i];
            back[i] = tempRight[i];
            left[i] = tempBack[i];
        }
    }

    public void rotateReverseDown() {
        int[] temp = down.clone();
        for (int i = 0; i < 8; i++) {
            down[i] = temp[(i + 2) % 8];
        }

        int[] tempFront = front.clone();
        int[] tempLeft = left.clone();
        int[] tempRight = right.clone();
        int[] tempBack = back.clone();
        for (int i = 4; i < 7; i++) {
            front[i] = tempRight[i];
            left[i] = tempFront[i];
            back[i] = tempLeft[i];
            right[i] = tempBack[i];
        }
    }

    public void rotateReverseLeft() {
        int[] temp = left.clone();
        for (int i = 0; i < 8; i++) {
            left[i] = temp[(i + 6) % 8];
        }

        int[] tempFront = front.clone();
        int[] tempUp = up.clone();
        int[] tempDown = down.clone();
        int[] tempBack = back.clone();
        int x;
        for (int i = 0; i < 3; i++) {
            if (i == 0)
                x = 0;
            else if (i == 1)
                x = 7;
            else
                x = 6;
            down[x] = tempFront[x];
            front[x] = tempUp[x];
        }
        back[4] = tempDown[0];
        back[3] = tempDown[7];
        back[2] = tempDown[6];

        up[0] = tempBack[4];
        up[7] = tempBack[3];
        up[6] = tempBack[2];
    }

    public void rotateReverseRight() {
        int[] temp = right.clone();
        for (int i = 0; i < 8; i++) {
            right[i] = temp[(i + 2) % 8];
        }

        int[] tempFront = front.clone();
        int[] tempUp = up.clone();
        int[] tempDown = down.clone();
        int[] tempBack = back.clone();
        int x;
        for (int i = 0; i < 3; i++) {
            if (i == 0)
                x = 2;
            else if (i == 1)
                x = 3;
            else
                x = 4;

            down[x] = tempFront[x];
            front[x] = tempUp[x];
        }
        back[6] = tempDown[2];
        back[7] = tempDown[3];
        back[0] = tempDown[4];

        up[4] = tempBack[0];
        up[3] = tempBack[7];
        up[2] = tempBack[6];
    }

    public void rotateReverseBack() {
        int[] temp = back.clone();
        for (int i = 0; i < 8; i++) {
            back[i] = temp[(i + 2) % 8];
        }

        int[] tempLeft = left.clone();
        int[] tempUp = up.clone();
        int[] tempDown = down.clone();
        int[] tempRight = right.clone();
        up[2] = tempLeft[0];
        up[1] = tempLeft[7];
        up[0] = tempLeft[6];

        left[0] = tempDown[6];
        left[7] = tempDown[5];
        left[6] = tempDown[4];

        down[6] = tempRight[4];
        down[5] = tempRight[3];
        down[4] = tempRight[2];

        right[2] = tempUp[0];
        right[3] = tempUp[1];
        right[4] = tempUp[2];
    }

    public void rotateReverseFront() {
        int[] temp = front.clone();
        for (int i = 0; i < 8; i++) {
            front[i] = temp[(i + 2) % 8];
        }

        int[] tempLeft = left.clone();
        int[] tempUp = up.clone();
        int[] tempDown = down.clone();
        int[] tempRight = right.clone();
        up[6] = tempRight[0];
        up[5] = tempRight[7];
        up[4] = tempRight[6];

        right[0] = tempDown[2];
        right[7] = tempDown[1];
        right[6] = tempDown[0];

        down[2] = tempLeft[4];
        down[1] = tempLeft[3];
        down[0] = tempLeft[2];

        left[2] = tempUp[4];
        left[3] = tempUp[5];
        left[4] = tempUp[6];
    }
}
