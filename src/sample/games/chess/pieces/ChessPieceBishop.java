package sample.games.chess.pieces;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by bartp on 19/11/2016.
 */
public class ChessPieceBishop extends ChessPieceBase {
    public ChessPieceBishop(int x, int y, boolean isBlack) {
        super(x, y, isBlack);
    }


    @Override
    public ArrayList<Point> getValidMoves(ChessPieceBase[][] board) {
        ArrayList<Point> points = new ArrayList<>();
        int j = Math.min(7 - getX(), 7 - getY());
        int inTheWay = 0;
        for (int i = 1; i <= j; i++) {
            System.out.println(getX() + i + ", " + (getY() + i) + ", " + j);
            int k = isValidPoint(getX() + i, getY() + i, board);
            if (k == 1)
                points.add(new Point(getX() + i, getY() + i));
            else if (k == 2 && inTheWay == 0) {
                inTheWay++;
                points.add(new Point(getX() + i, getY() + i));
            } else
                break;
        }

        inTheWay = 0;
        j = Math.min(7 - getX(), getY());
        for (int i = 1; i <= j; i++) {
            int k = isValidPoint(getX() + i, getY() - +i, board);
            if (k == 1)
                points.add(new Point(getX() + i, getY() - i));
            else if (k == 2 && inTheWay == 0) {
                inTheWay++;
                points.add(new Point(getX() + i, getY() - i));
            } else
                break;
        }

        inTheWay = 0;
        j = Math.min(getX(), 7 - getY());
        for (int i = 1; i <= j; i++) {
            int k = isValidPoint(getX() - i, getY() + i, board);
            if (k == 1)
                points.add(new Point(getX() - i, getY() + i));
            else if (k == 2 && inTheWay == 0) {
                inTheWay++;
                points.add(new Point(getX() - i, getY() + i));
            } else
                break;
        }

        inTheWay = 0;
        j = Math.min(getX(), getY());
        for (int i = 1; i <= j; i++) {
            int k = isValidPoint(getX() - i, getY() - i, board);
            if (k == 1)
                points.add(new Point(getX() - i, getY() - i));
            else if (k == 2 && inTheWay == 0) {
                inTheWay++;
                points.add(new Point(getX() - i, getY() - i));
            } else
                break;
        }
        return points;
    }
}
