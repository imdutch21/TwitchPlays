package sample.games.chess;

import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.games.chess.pieces.ChessPieceBase;
import sample.views.GenericViews;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by bartp on 18/11/2016.
 */
public class TwitchPlaysChessUI {
    private Scene scene;
    private TwitchPlaysChess domein;
    public ArrayList<Point> validMoves;
    public Point selected;

    public TwitchPlaysChessUI(Scene scene, TwitchPlaysChess domein) {
        this.scene = scene;
        this.domein = domein;
        this.validMoves = new ArrayList<>();
        selected = null;
    }

    public void drawScreen() {
        GridPane pane = new GridPane();
        pane.add(board(), 0, 0);
        pane.add(GenericViews.chat(domein.messages), 1, 0);
        scene.setRoot(pane);
    }

    private Pane board() {
        GridPane grid = new GridPane();
        boolean black = false;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                boolean isMovable = false;
                for (Point p : validMoves)
                    if (p.getX() == x && p.getY() == y) {
                        isMovable = true;
                        break;
                    }
                if (black)
                    grid.add(GenericViews.createRectangle(Color.BLACK, false, 64, 64), x, y);
                else
                    grid.add(GenericViews.createRectangle(Color.WHITE, false, 64, 64), x, y);
                black = !black;
                if (isMovable) {
                    Rectangle rec = GenericViews.createRectangle(Color.RED, false, 24, 24);
                    GridPane.setHalignment(rec, HPos.CENTER);
                    grid.add(rec, x, y);
                }
                if (domein.board[x][y] != null) {
                    if (selected != null && selected.getY() == y && selected.getX() == x) {
                        Rectangle rec = GenericViews.createRectangle(Color.BLUE, false, 24, 24);
                        GridPane.setHalignment(rec, HPos.CENTER);
                        grid.add(rec, x, y);
                    }
                    Rectangle piece = GenericViews.createRectangle(GenericViews.getColorFromInt(domein.board[x][y].isBlack() ? 1 : 2), false, 16, 16);
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

    public void displayMovablePieces() {
        validMoves.clear();
        for (ChessPieceBase piece : domein.getMovablePieces(domein.board, domein.isBlackTurn())) {
            if (piece.getValidMoves(domein.board, true).size() > 0)
                validMoves.add(new Point(piece.getX(), piece.getY()));
        }
    }

    public void displayValidMoves() {
        validMoves.clear();
        ChessPieceBase piece = domein.board[selected.x][selected.y];
        validMoves.addAll(piece.getValidMoves(domein.board, true));
    }
}
