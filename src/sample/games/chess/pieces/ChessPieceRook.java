package sample.games.chess.pieces;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by bartp on 19/11/2016.
 */
public class ChessPieceRook extends ChessPieceBase {
    public ChessPieceRook(int x, int y, boolean isBlack) {
        super(x, y, isBlack);
    }


    @Override
    public ArrayList<Point> getValidMoves(ChessPieceBase[][] board) {
        ArrayList<Point> points = new ArrayList<>();
        int inTheWay1 = 0;
        int inTheWay2 = 0;
        int inTheWay3 = 0;
        int inTheWay4 = 0;
        for (int i = 1; i <= 8; i++) {
            int k = isValidPoint(getX() + i, getY(), board);
            if (inTheWay1 == 0)
                if (k == 1)
                    points.add(new Point(getX() + i, getY()));
                else if (k == 2) {
                    inTheWay1++;
                    points.add(new Point(getX() + i, getY()));
                }

            k = isValidPoint(getX() - i, getY(), board);
            if (inTheWay2 == 0)
                if (k == 1)
                    points.add(new Point(getX() - i, getY()));
                else if (k == 2) {
                    inTheWay2++;
                    points.add(new Point(getX() - i, getY()));
                }

            k = isValidPoint(getX(), getY() + i, board);
            if (inTheWay3 == 0)
                if (k == 1)
                    points.add(new Point(getX(), getY() + i));
                else if (k == 2) {
                    inTheWay3++;
                    points.add(new Point(getX(), getY() + i));
                }

            k = isValidPoint(getX(), getY() - i, board);
            if (inTheWay4 == 0)
                if (k == 1)
                    points.add(new Point(getX(), getY() - i));
                else if (k == 2) {
                    inTheWay4++;
                    points.add(new Point(getX(), getY() - i));
                }
        }
        return points;
    }
}
