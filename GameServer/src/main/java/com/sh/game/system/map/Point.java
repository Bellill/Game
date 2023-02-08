package com.sh.game.system.map;

import io.protostuff.Tag;

/**
 * 玩家坐标
 *
 * @author lostfinale
 */
public class Point {

    @Tag(1)
    public int x;

    @Tag(2)
    public int y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        if (point != null) {
            x = point.getX();
            y = point.getY();
        }
    }

    public Point(int[] xy) {
        if (xy != null && xy.length >= 2) {
            x = xy[0];
            y = xy[1];
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Point point = (Point) object;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Point[" + x + ", " + y + ']';
    }
}
