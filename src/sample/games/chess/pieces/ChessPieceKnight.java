package sample.games.chess.pieces;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by bartp on 19/11/2016.
 */
public class ChessPieceKnight extends ChessPieceBase{
    public ChessPieceKnight(int x, int y, boolean isBlack) {
        super(x, y, isBlack);
    }


    @Override
    public ArrayList<Point> getValidMoves(ChessPieceBase[][] board) {
        ArrayList<Point> points = new ArrayList<>();
        if (isValidPoint(getX() + 2, getY() + 1, board))
            points.add(new Point(getX() + 2, getY() + 1));
        if (isValidPoint(getX() + 2, getY() - 1, board))
            points.add(new Point(getX() + 2, getY() - 1));
        if (isValidPoint(getX() - 2, getY() + 1, board))
            points.add(new Point(getX() - 2, getY() + 1));
        if (isValidPoint(getX() - 2, getY() - 1, board))
            points.add(new Point(getX() - 2, getY() - 1));

        if (isValidPoint(getX() + 1, getY() + 2, board))
            points.add(new Point(getX() + 1, getY() + 2));
        if (isValidPoint(getX() + 1, getY() - 2, board))
            points.add(new Point(getX() + 1, getY() - 2));
        if (isValidPoint(getX() - 1, getY() + 2, board))
            points.add(new Point(getX() - 1, getY() + 2));
        if (isValidPoint(getX() - 1, getY() - 2, board))
            points.add(new Point(getX() - 1, getY() - 2));
        return points;
    }
}
