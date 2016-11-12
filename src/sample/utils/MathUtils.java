package sample.utils;

import javafx.scene.shape.Line;

public class MathUtils {
    public static double angleFromLine(Line line) {
        double dX = line.getEndX() - line.getStartX();
        double dY = line.getEndY() - line.getStartY();
        return Math.toDegrees(Math.atan(dY / dX));
    }

    public static double lengthFromLine(Line line) {
        double dX = line.getEndX() - line.getStartX();
        double dY = line.getEndY() - line.getStartY();
        return Math.sqrt(dX * dX + dY * dY);
    }

    public static double intersectLinesX(Line l1, Line l2) {
        double x1 = l1.getStartX();
        double x2 = l1.getEndX();
        double x3 = l2.getStartX();
        double x4 = l2.getEndX();

        double y1 = l1.getStartY();
        double y2 = l1.getEndY();
        double y3 = l2.getStartY();
        double y4 = l2.getEndY();

        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        return ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
    }

    public static double intersectLinesY(Line l1, Line l2) {
        double x1 = l1.getStartX();
        double x2 = l1.getEndX();
        double x3 = l2.getStartX();
        double x4 = l2.getEndX();

        double y1 = l1.getStartY();
        double y2 = l1.getEndY();
        double y3 = l2.getStartY();
        double y4 = l2.getEndY();
        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        return ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;
    }




}
