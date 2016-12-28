package sample.games.chess.pieces;

import sample.Main;
import sample.games.chess.TwitchPlaysChess;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by bartp on 19/11/2016.
 */
public class ChessPieceBase {

    private int x;
    private int y;
    private boolean isBlack;

    public ChessPieceBase(int x, int y, boolean isBlack) {
        this.x = x;
        this.y = y;
        this.isBlack = isBlack;
    }


    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ArrayList<Point> getValidMoves(ChessPieceBase[][] board, boolean mateCheck) {
        return null;
    }


    public boolean isBlack() {
        return isBlack;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    /**
     * Returns whether the current selected spot is a place that a piece can stand
     *
     * @param x     x coordinate
     * @param y     y coordinate
     * @param board the current board
     * @return 0 if it's not a valid spot,
     * 1 if it's a valid empty spot,
     * 2 if it's a valid spot that's occupied by an enemy piece
     */
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
                Point locationKing;
                if (isBlack())
                    locationKing = ((TwitchPlaysChess) Main.currentGame).getLocationBlackKing();
                else
                    locationKing = ((TwitchPlaysChess) Main.currentGame).getLocationWhiteKing();
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

    @Override
    public String toString() {
        return super.toString();
    }
}
