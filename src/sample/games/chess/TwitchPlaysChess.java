package sample.games.chess;

import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import sample.games.GameBase;
import sample.games.chess.pieces.*;
import sample.utils.Constants;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by bart on 12/11/2016.
 */
public class TwitchPlaysChess extends GameBase {
    public ChessPieceBase[][] board = new ChessPieceBase[8][8];
    private TwitchPlaysChessUI ui;
    private Point locationBlackKing, locationWhiteKing;
    private boolean blackTurn = true;


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
                    if (y == 0) {
                        locationBlackKing = new Point(x, y);
                    } else {
                        locationWhiteKing = new Point(x, y);
                    }
                    board[x][y] = new ChessPieceKing(x, y, y == 0);
                } else if (y == 1 || y == 6) {
                    board[x][y] = new ChessPiecePawn(x, y, y == 1);
                } else
                    board[x][y] = null;
            }
        ui.displayMovablePieces();
    }

    /**
     * Returns all the pieces of a specified colour
     *
     * @param isBlack whether the piece is black or not
     * @return all the pieces that are of the specified colour
     */
    public ArrayList<ChessPieceBase> getMovablePieces(ChessPieceBase[][] board, boolean isBlack) {
        ArrayList<ChessPieceBase> pieces = new ArrayList<>();
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++) {
                ChessPieceBase pieceBase = board[x][y];
                if (pieceBase != null)
                    if (pieceBase.isBlack() == isBlack)
                        pieces.add(pieceBase);
            }
        return pieces;
    }

    /**
     * Moves a piece from one place to another
     *
     * @param xStart the x coordinate of the current piece
     * @param yStart the y coordinate of the current piece
     * @param xEnd   the x coordinate of the destination
     * @param yEnd   the y coordinate of the destination
     */
    public void movePiece(int xStart, int yStart, int xEnd, int yEnd) {
        board[xEnd][yEnd] = board[xStart][yStart];
        board[xStart][yStart] = null;
        board[xEnd][yEnd].moveTo(xEnd, yEnd);
        if (board[xEnd][yEnd] instanceof ChessPieceKing) {
            if (board[xEnd][yEnd].isBlack()) {
                locationBlackKing = new Point(xEnd, yEnd);
            } else {
                locationWhiteKing = new Point(xEnd, yEnd);
            }
        }

        drawScreen();
    }


    @Override
    public void drawScreen() {
        ui.drawScreen();
    }

    public Point getLocationBlackKing() {
        return locationBlackKing;
    }

    public Point getLocationWhiteKing() {
        return locationWhiteKing;
    }

    public boolean isBlackTurn() {
        return blackTurn;
    }

    @Override
    public void handleMouseClick(MouseButton button, double x, double y) {
        if (x > 8 && y > 8 && x <= 520 && y <= 520) {
            int gridX = (int) ((x - 8) / 64);
            int gridY = (int) ((y - 8) / 64);
            if (ui.validMoves != null) {
                if (ui.selected == null) {
                    for (Point p : ui.validMoves)
                        if (p.getX() == gridX && p.getY() == gridY) {
                            ui.selected = new Point(gridX, gridY);
                            ui.displayValidMoves();
                            ui.drawScreen();
                            break;
                        }
                } else {
                    boolean validClick = false;
                    for (Point p : ui.validMoves) {
                        if (p.getX() == gridX && p.getY() == gridY) {
                            movePiece(ui.selected.x, ui.selected.y, gridX, gridY);
                            blackTurn = !blackTurn;
                            ui.selected = null;
                            ui.displayMovablePieces();
                            ui.drawScreen();
                            validClick = true;
                            break;
                        }
                    }
                    if (!validClick){
                        ui.selected = null;
                        ui.displayMovablePieces();
                        ui.drawScreen();
                    }
                }
            }
        }
    }
}
