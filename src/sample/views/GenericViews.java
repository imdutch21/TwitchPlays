package sample.views;

import javafx.scene.Group;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.utils.MathUtils;

import java.util.ArrayList;

import static sample.utils.Constants.*;


/**
 * Created by bart on 12/11/2016.
 */
public class GenericViews {
    public static Pane createChatView() {
        return new Pane();
    }


    public static Line makeLine(Line l1, Line l2, double angle1, double angle2, double length) {
        return makeLine(l1, l2, angle1, angle2, length, length);
    }

    public static Line makeLine(Line l1, Line l2, double angle1, double angle2, double length1, double length2) {
        angle1 = Math.toRadians(angle1);
        double startY = l1.getStartY() + Math.sin(angle1) * length1;
        double startX = l1.getStartX() + Math.cos(angle1) * length1;

        angle2 = Math.toRadians(angle2);
        double endY = l2.getStartY() + Math.sin(angle2) * length2;
        double endX = l2.getStartX() + Math.cos(angle2) * length2;
        Line l = new Line(startX, startY, endX, endY);
        return new Line(startX, startY, endX, endY);
    }

    public static Line makeLine(double startX, double startY, double angle, double length) {
        double angle1 = Math.toRadians(angle);
        double endY = startY + Math.sin(angle1) * length;
        double endX = startX + Math.cos(angle1) * length;
        Line l = new Line(startX, startY, endX, endY);
        return new Line(startX, startY, endX, endY);
    }

    public static Group fillArea(Line left, Line top, Line right, Line bottom, int color) {
        return fillArea(left, top, right, bottom, color, "");
    }

    public static Group fillArea(Line left, Line top, Line right, Line bottom, int color, String letter) {
        Color shapeColor = null;
        switch (color) {
            case YELLOW:
                shapeColor = Color.YELLOW;
                break;
            case ORANGE:
                shapeColor = Color.ORANGE;
                break;
            case GREEN:
                shapeColor = Color.GREEN;
                break;
            case BLUE:
                shapeColor = Color.BLUE;
                break;
            case RED:
                shapeColor = Color.RED;
                break;
            case WHITE:
                shapeColor = Color.WHITE;
                break;
            default:
                shapeColor = Color.DARKGOLDENROD;
                break;
        }
        double xUL = MathUtils.intersectLinesX(top, left);
        double xUR = MathUtils.intersectLinesX(top, right);
        double xBL = MathUtils.intersectLinesX(bottom, left);
        double xBR = MathUtils.intersectLinesX(bottom, right);

        double yUL = MathUtils.intersectLinesY(top, left);
        double yUR = MathUtils.intersectLinesY(top, right);
        double yBL = MathUtils.intersectLinesY(bottom, left);
        double yBR = MathUtils.intersectLinesY(bottom, right);
        return fillArea(left, top, right, bottom, shapeColor, letter, xUL, xUR, xBL, xBR, yUL, yUR, yBL, yBR);
    }


    /**
     * Fills an area with a colour
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @param color
     * @param letter
     * @param xUL    x up left
     * @param xUR    x up right
     * @param xBL    x bottom left
     * @param xBR    x bottom right
     * @param yUL    y up left
     * @param yUR    y up right
     * @param yBL    y bottom left
     * @param yBR    y bottom right
     * @return
     */
    public static Group fillArea(Line left, Line top, Line right, Line bottom, Color color, String letter, double xUL, double xUR, double xBL, double xBR, double yUL, double yUR, double yBL, double yBR) {
        PerspectiveTransform perspectiveTrasform = new PerspectiveTransform();
        perspectiveTrasform.setUlx(xUL);
        perspectiveTrasform.setUly(yUL);
        perspectiveTrasform.setUrx(xUR);
        perspectiveTrasform.setUry(yUR);
        perspectiveTrasform.setLrx(xBR);
        perspectiveTrasform.setLry(yBR);
        perspectiveTrasform.setLlx(xBL);
        perspectiveTrasform.setLly(yBL);

        Rectangle rectangle = new Rectangle();
        rectangle.setX(xUL);
        rectangle.setY(yUL);
        rectangle.setWidth(280.0);
        rectangle.setHeight(120.0);
        rectangle.setFill(color);
        GridPane g = new GridPane();
        g.add(rectangle, 0, 0);
        g.setEffect(perspectiveTrasform);
        g.setCache(true);

        if (letter.length() > 0) {
            Text t = new Text("    " + letter);
            t.setX(xUL);
            t.setY(yUL);
            t.setFont(new Font(80));
            g.add(t, 0, 0);
        }


        return new Group(g);
    }

    /**
     * creates a chat window with messages from twitch
     * @param messages
     * @return
     */
    public static Pane chat(ArrayList<String> messages) {
        GridPane pane = new GridPane();
        pane.setStyle("-fx-border-color: BLACK;");
        Text text = new Text("Chat                                    ");
        text.setFont(new Font(16));
        pane.add(text, 0, 0);
        int i = 1;
        for (String s : messages) {
            Text t = new Text(s);
            t.setFont(new Font(14));
            pane.add(new Text(s), 0, i);
            i++;
        }

        return pane;
    }

}
