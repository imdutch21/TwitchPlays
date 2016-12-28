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

    /*
    *   *
     * *
      #
     * *
    *   *
     */
    @Override
    public ArrayList<Point> getValidMoves(ChessPieceBase[][] board, boolean mateCheck) {
        ArrayList<Point> points = new ArrayList<>();
        int inTheWay1 = 0;
        int inTheWay2 = 0;
        int inTheWay3 = 0;
        int inTheWay4 = 0;
        for (int i = 1; i <= 8; i++) {
            int k = isValidPoint(getX() + i, getY() + i, board, mateCheck);
            if (inTheWay1 == 0)
                if (k == 1)
                    points.add(new Point(getX() + i, getY() + i));
                else if (k == 2) {
                    inTheWay1++;
                    points.add(new Point(getX() + i, getY() + i));
                } else
                    inTheWay1++;

            k = isValidPoint(getX() - i, getY() + i, board, mateCheck);
            if (inTheWay2 == 0)
                if (k == 1)
                    points.add(new Point(getX() - i, getY() + i));
                else if (k == 2) {
                    inTheWay2++;
                    points.add(new Point(getX() - i, getY() + i));
                } else
                    inTheWay2++;

            k = isValidPoint(getX() - i, getY() - i, board, mateCheck);
            if (inTheWay3 == 0)
                if (k == 1)
                    points.add(new Point(getX() - i, getY() - i));
                else if (k == 2) {
                    inTheWay3++;
                    points.add(new Point(getX() - i, getY() - i));
                } else
                    inTheWay3++;

            k = isValidPoint(getX() + i, getY() - i, board, mateCheck);
            if (inTheWay4 == 0)
                if (k == 1)
                    points.add(new Point(getX() + i, getY() - i));
                else if (k == 2) {
                    inTheWay4++;
                    points.add(new Point(getX() + i, getY() - i));
                } else
                    inTheWay4++;
        }
        return points;
    }

    @Override
    public String toString() {
        return "Bishop: " + getX() + ", " + getY();
    }
}
