package sample.games.chess.pieces;

import sample.Main;
import sample.games.chess.TwitchPlaysChess;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by bartp on 19/11/2016.
 */
public class ChessPieceKing extends ChessPieceBase {
    public ChessPieceKing(int x, int y, boolean isBlack) {
        super(x, y, isBlack);
    }

    /*
     ***
     *#*
     ***
     */
    @Override
    public ArrayList<Point> getValidMoves(ChessPieceBase[][] board, boolean mateCheck) {
        ArrayList<Point> points = new ArrayList<>();
        if (isValidPoint(getX(), getY() + 1, board, mateCheck) != 0)
            points.add(new Point(getX(), getY() + 1));
        if (isValidPoint(getX() + 1, getY(), board, mateCheck) != 0)
            points.add(new Point(getX() + 1, getY()));
        if (isValidPoint(getX() - 1, getY(), board, mateCheck) != 0)
            points.add(new Point(getX() - 1, getY()));
        if (isValidPoint(getX(), getY() - 1, board, mateCheck) != 0)
            points.add(new Point(getX(), getY() - 1));


        if (isValidPoint(getX() - 1, getY() + 1, board, mateCheck) != 0)
            points.add(new Point(getX() - 1, getY() + 1));
        if (isValidPoint(getX() + 1, getY() + 1, board, mateCheck) != 0)
            points.add(new Point(getX() + 1, getY() + 1));
        if (isValidPoint(getX() - 1, getY() - 1, board, mateCheck) != 0)
            points.add(new Point(getX() - 1, getY() - 1));
        if (isValidPoint(getX() + 1, getY() - 1, board, mateCheck) != 0)
            points.add(new Point(getX() + 1, getY() - 1));
        return points;
    }

    @Override
    public String toString() {
        return "King: " + getX() + ", " + getY();
    }

    @Override
    public int isValidPoint(int x, int y, ChessPieceBase[][] board, boolean mateCheck) {
        if (x < 8 && y < 8 && x > -1 && y > -1 && (board[x][y] == null || board[x][y].isBlack() != isBlack())) {
            //checks if the king can be hit with this move
            if (mateCheck && Main.currentGame instanceof TwitchPlaysChess) {
                ChessPieceBase[][] tempBoard = new ChessPieceBase[8][8];
                for (int x2 = 0; x2 < 8; x2++)
                    for (int y2 = 0; y2 < 8; y2++)
                        tempBoard[x2][y2] = board[x2][y2];
                tempBoard[getX()][getY()] = null;
                tempBoard[x][y] = this;
                //Point is set to the new location of the king here instead
                Point locationKing = new Point(x, y);
                ArrayList<ChessPieceBase> pieces = ((TwitchPlaysChess) Main.currentGame).getMovablePieces(tempBoard, !isBlack());
                ArrayList<Point> points = new ArrayList<>();
                for (ChessPieceBase piece:pieces){
                    points.addAll(piece.getValidMoves(tempBoard, false));
                }
                if (points.contains(locationKing))
                    return 0;
            }

            if (board[x][y] != null && board[x][y].isBlack() != isBlack())
                return 2;
            else
                return 1;
        }
        else
            return 0;
    }
}
