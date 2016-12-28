package sample.games.chess.pieces;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by bartp on 19/11/2016.
 */
public class ChessPieceKnight extends ChessPieceBase {
    public ChessPieceKnight(int x, int y, boolean isBlack) {
        super(x, y, isBlack);
    }

    /*
         * *
        *   *
          #
        *   *
         * *
    */
    @Override
    public ArrayList<Point> getValidMoves(ChessPieceBase[][] board, boolean mateCheck) {
        ArrayList<Point> points = new ArrayList<>();
        if (isValidPoint(getX() + 2, getY() + 1, board, mateCheck) != 0)
            points.add(new Point(getX() + 2, getY() + 1));
        if (isValidPoint(getX() + 2, getY() - 1, board, mateCheck) != 0)
            points.add(new Point(getX() + 2, getY() - 1));
        if (isValidPoint(getX() - 2, getY() + 1, board, mateCheck) != 0)
            points.add(new Point(getX() - 2, getY() + 1));
        if (isValidPoint(getX() - 2, getY() - 1, board, mateCheck) != 0)
            points.add(new Point(getX() - 2, getY() - 1));

        if (isValidPoint(getX() + 1, getY() + 2, board, mateCheck) != 0)
            points.add(new Point(getX() + 1, getY() + 2));
        if (isValidPoint(getX() + 1, getY() - 2, board, mateCheck) != 0)
            points.add(new Point(getX() + 1, getY() - 2));
        if (isValidPoint(getX() - 1, getY() + 2, board, mateCheck) != 0)
            points.add(new Point(getX() - 1, getY() + 2));
        if (isValidPoint(getX() - 1, getY() - 2, board, mateCheck) != 0)
            points.add(new Point(getX() - 1, getY() - 2));
        return points;
    }


    @Override
    public String toString() {
        return "Knight: " + getX() + ", " + getY();
    }
}
