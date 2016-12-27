package sample.games.chess.pieces;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by bartp on 19/11/2016.
 */
public class ChessPiecePawn extends ChessPieceBase {
    private boolean hasBeenMoved = false;
    public ChessPiecePawn(int x, int y, boolean isBlack) {
        super(x, y, isBlack);
    }


    @Override
    public void moveTo(int x, int y) {
        super.moveTo(x, y);
        hasBeenMoved = true;
    }

    @Override
    public ArrayList<Point> getValidMoves(ChessPieceBase[][] board) {
        ArrayList<Point> points = new ArrayList<>();
        if (isBlack()) {
            if (isValidPoint(getX(), getY() + 1, board) == 1)
                points.add(new Point(getX(), getY() + 1));
            if (isValidPoint(getX(), getY() + 2, board) == 1 && !hasBeenMoved)
                points.add(new Point(getX(), getY() + 2));
            if (isValidPoint(getX() + 1, getY() + 1, board) == 2)
                points.add(new Point(getX() + 1, getY() + 1));
            if (isValidPoint(getX() - 1, getY() + 1, board) == 2)
                points.add(new Point(getX() - 1, getY() + 1));
        } else {
            if (isValidPoint(getX(), getY() - 1, board) == 1)
                points.add(new Point(getX(), getY() - 1));
            if (isValidPoint(getX(), getY() - 2, board) == 1 && !hasBeenMoved)
                points.add(new Point(getX(), getY() - 2));
            if (isValidPoint(getX() + 1, getY() - 1, board) == 2)
                points.add(new Point(getX() + 1, getY() - 1));
            if (isValidPoint(getX() - 1, getY() - 1, board) == 2)
                points.add(new Point(getX() - 1, getY() - 1));
        }
        return points;
    }
}
