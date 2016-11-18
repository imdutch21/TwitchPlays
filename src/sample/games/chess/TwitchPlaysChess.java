package sample.games.chess;

import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.games.GameBase;
import sample.utils.Constants;
import sample.views.GenericViews;


/**
 * Created by bart on 12/11/2016.
 */
public class TwitchPlaysChess extends GameBase {
    public EnumChessPiece[][] board = new EnumChessPiece[8][8];
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
                    EnumChessPiece piece = EnumChessPiece.ROOK;
                    piece.isBlack = y == 0;
                    board[x][y] = piece;
                } else if ((x == 1 || x == 6) && (y == 0 | y == 7)) {
                    EnumChessPiece piece = EnumChessPiece.KNIGHT;
                    piece.isBlack = y == 0;
                    board[x][y] = piece;
                } else if ((x == 2 || x == 5) && (y == 0 | y == 7)) {
                    EnumChessPiece piece = EnumChessPiece.BISHOP;
                    piece.isBlack = y == 0;
                    board[x][y] = piece;
                } else if ((x == 3) && (y == 0 | y == 7)) {
                    EnumChessPiece piece = EnumChessPiece.QUEEN;
                    piece.isBlack = y == 0;
                    board[x][y] = piece;
                } else if ((x == 4) && (y == 0 | y == 7)) {
                    EnumChessPiece piece = EnumChessPiece.KING;
                    piece.isBlack = y == 0;
                    board[x][y] = piece;
                } else if (y == 1 || y == 6) {
                    EnumChessPiece piece = EnumChessPiece.PAWN;
                    piece.isBlack = y == 1;
                    board[x][y] = piece;
                } else
                    board[x][y] = null;
            }
    }


    public boolean canMove(int startX, int startY, int endX, int endY) {
        if (startX < 8 && startY < 8 && endX < 8 && endY < 8) {
            EnumChessPiece piece = board[startX][startY];
            if (piece != null) {
                int dY = endY - startX;
                int dX = endX - startX;
                switch (piece) {
                    case PAWN: {
                        if (dX == 0 && (dY == 1 || (dY == 2 && piece.firstMove)) && board[startX][startY + 1] == null) {
                            if (dY == 1)
                                return true;
                            else if (board[startX][startY + 2] == null)
                                return true;
                        } else if ((dX == -1 || dX == 1) && dY == 1 && board[endX][endY] != null && board[endX][endY].isBlack == piece.isBlack) {
                            return true;
                        }
                        break;
                    }
                    case ROOK: {
                        if (dY != 0 && dX == 0) {
                            boolean blocked = false;
                            for (int i = 0; i < dY; i++) {
                                if (board[startX][startY + i] != null) {
                                    blocked = true;
                                }
                            }
                            return blocked;
                        } else if (dY == 0 && dX != 0) {
                            boolean blocked = false;
                            for (int i = 0; i < dX; i++) {
                                if (board[startX + i][startY] != null) {
                                    blocked = true;
                                }
                            }
                            return blocked;
                        }
                    }
                }
            }
        }
        return false;
    }
}
