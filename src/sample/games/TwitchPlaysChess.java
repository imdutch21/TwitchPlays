package sample.games;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.utils.Constants;
import sample.views.GenericViews;

/**
 * Created by bart on 12/11/2016.
 */
public class TwitchPlaysChess extends GameBase {
    private EnumChessPiece[][] board = new EnumChessPiece[8][8];


    public TwitchPlaysChess(Scene scene) {
        super(scene, Constants.CHESS);
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        super.onMessage(channel, sender, login, hostname, message);
    }

    @Override
    public void start() {
        super.start();
        populateBoard();
        drawScreen();
    }

    @Override
    public void drawScreen() {
        super.drawScreen();
        GridPane pane = new GridPane();
        pane.add(chessBoard(), 0, 0);
        pane.add(GenericViews.chat(messages), 1, 0);
        scene.setRoot(pane);
    }


    private Pane chessBoard() {
        GridPane grid = new GridPane();
        boolean black = false;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (black)
                    grid.add(GenericViews.createRectangle(Color.BLACK, false, 64, 64), x, y);
                else
                    grid.add(GenericViews.createRectangle(Color.WHITE, false, 64, 64), x, y);
                black = !black;
                if (board[x][y] != null) {
                    Rectangle piece = GenericViews.createRectangle(GenericViews.getColorFromInt(board[x][y].ordinal()), false, 16, 16);
                    GridPane.setHalignment(piece, HPos.CENTER);
                    grid.add(piece, x, y);
                }
            }
            black = !black;
        }
        grid.setStyle("-fx-border-color: BROWN; " +
                "-fx-border-width: 8;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        return grid;
    }

    private void populateBoard() {
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++) {
                if ((x == 0 || x == 7) && (y == 0 | y == 7))
                    board[x][y] = EnumChessPiece.ROOK;
                else if ((x == 1 || x == 6) && (y == 0 | y == 7))
                    board[x][y] = EnumChessPiece.KNIGHT;
                else if ((x == 2 || x == 5) && (y == 0 | y == 7))
                    board[x][y] = EnumChessPiece.BISHOP;
                else if ((x == 3) && (y == 0 | y == 7))
                    board[x][y] = EnumChessPiece.QUEEN;
                else if ((x == 4) && (y == 0 | y == 7))
                    board[x][y] = EnumChessPiece.KING;
                else if (y == 1 || y == 6)
                    board[x][y] = EnumChessPiece.PAWN;
                else
                    board[x][y] = null;
            }
    }
}
