package sample.games.chess;

import javafx.scene.Scene;
import sample.games.GameBase;
import sample.games.chess.pieces.*;
import sample.utils.Constants;

import java.util.ArrayList;


/**
 * Created by bart on 12/11/2016.
 */
public class TwitchPlaysChess extends GameBase {
    public ChessPieceBase[][] board = new ChessPieceBase[8][8];
    private TwitchPlaysChessUI ui;


    public TwitchPlaysChess(Scene scene) {
        super(scene, Constants.CHESS);
        ui = new TwitchPlaysChessUI(scene, this);
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        super.onMessage(channel, sender, login, hostname, message);
    }

    @Override
    public void start() {
        super.start();
        populateBoard();
        ui.drawScreen();
    }


    private void populateBoard() {
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++) {
                if ((x == 0 || x == 7) && (y == 0 | y == 7)) {
                    board[x][y] = new ChessPieceRook(x, y, y == 0);
                } else if ((x == 1 || x == 6) && (y == 0 | y == 7)) {
                    board[x][y] = new ChessPieceKnight(x, y, y == 0);
                } else if ((x == 2 || x == 5) && (y == 0 | y == 7)) {
                    board[x][y] = new ChessPieceBishop(x, y, y == 0);
                } else if ((x == 3) && (y == 0 | y == 7)) {
                    board[x][y] = new ChessPieceQueen(x, y, y == 0);
                } else if ((x == 4) && (y == 0 | y == 7)) {
                    board[x][y] = new ChessPieceKing(x, y, y == 0);
                } else if (y == 1 || y == 6) {
                    board[x][y] = new ChessPiecePawn(x, y, y == 1);
                } else
                    board[x][y] = null;
            }
        board[3][5] = new ChessPiecePawn(3, 5, true);
        ui.validMoves.addAll(board[3][5].getValidMoves(board));
    }

    /**
     * Returns all the pieces of a specified colour
     * @param isBlack whether the piece is black or not
     * @return all the pieces that are of the specified colour
     */
    public ArrayList<ChessPieceBase> getMovablePieces(boolean isBlack) {
        ArrayList<ChessPieceBase> pieces = new ArrayList<>();
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++) {
                ChessPieceBase pieceBase = board[x][y];
                if (pieceBase.isBlack() == isBlack)
                    pieces.add(pieceBase);
            }
        return pieces;
    }

    /**
     * Moves a piece from one place to another
     * @param xStart the x coordinate of the current piece
     * @param yStart the y coordinate of the current piece
     * @param xEnd the x coordinate of the destination
     * @param yEnd the y coordinate of the destination
     */
    public void movePiece(int xStart, int yStart, int xEnd, int yEnd) {
        board[xEnd][yEnd] = board[xStart][yStart];
        board[xStart][yStart] = null;
        board[xEnd][yEnd].moveTo(xEnd, yEnd);
        drawScreen();
    }


    @Override
    public void drawScreen() {
        ui.drawScreen();
    }
}
