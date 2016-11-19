package sample.games.chess.pieces;

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

    public ArrayList<Point> getValidMoves() {
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
}
