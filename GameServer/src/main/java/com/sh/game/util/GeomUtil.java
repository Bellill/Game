package com.sh.game.util;

import com.sh.game.system.map.Dir;
import com.sh.game.system.map.Grid;
import com.sh.game.system.map.Point;

public class GeomUtil {

    /**
     * 自己周围八方向的偏移量
     */
    public static final int[] EIGHT_DIR_OFFSET = new int[] { -1, -1, 0, -1, 1, -1, 1, 0, 1, 1, 0, 1, -1, 1, -1, 0 };

    public static int getPointId(int x, int y) {
        return (x << 16) | y;
    }

    public static Dir getDir(Point fromPoint, Point toPoint) {
        return getDir(fromPoint.getX(), fromPoint.getY(), toPoint.getX(), toPoint.getY());
    }

    public static Dir getDir(int fx, int fy, int tx, int ty) {
        int colDiff = tx - fx;
        int rowDiff = ty - fy;
        if (colDiff > 0) {
            if (rowDiff > 0) {
                if (rowDiff == colDiff) {
                    return Dir.RIGHT_BOTTOM;
                } else if (rowDiff > colDiff) {
                    return Dir.BOTTOM;
                } else {
                    return Dir.RIGHT;
                }
            } else if (rowDiff < 0) {
                if (rowDiff + colDiff == 0) {
                    return Dir.RIGHT_TOP;
                } else if (rowDiff + colDiff > 0) {
                    return Dir.RIGHT;
                } else {
                    return Dir.TOP;
                }
            } else {
                return Dir.RIGHT;
            }
        } else if (colDiff == 0) {
            if (ty > fy) {
                return Dir.BOTTOM;
            } else if (ty == fy) {
                // 两个点在同一个位置上
                return Dir.NONE;
            } else {
                return Dir.TOP;
            }
        } else {
            if (rowDiff > 0) {
                if (rowDiff + colDiff == 0) {
                    return Dir.LEFT_BOTTOM;
                } else if (rowDiff + colDiff > 0) {
                    return Dir.BOTTOM;
                } else {
                    return Dir.LEFT;
                }
            } else if (rowDiff < 0) {
                if (rowDiff == colDiff) {
                    return Dir.LEFT_TOP;
                } else if (rowDiff > colDiff) {
                    return Dir.LEFT;
                } else {
                    return Dir.TOP;
                }
            } else {
                return Dir.LEFT;
            }
        }
    }

    public static final int distance(Point a, Point b) {
        if (a == null || b == null) {
            return 0;
        }
        return distance(a.getX(), a.getY(), b.getX(), b.getY());
    }

    public static final int distance(int x1, int y1, int x2, int y2) {
        return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    public static final int distance2(Point a, Point b) {
        if (a == null || b == null) {
            return 0;
        }
        return distance2(a.getX(), a.getY(), b.getX(), b.getY());
    }

    public static final int distance2(int x1, int y1, int x2, int y2) {
        return (int) Math.round(Math.hypot(x1 - x2, y1 - y2));
    }

    public static final int distance3(Point a, Point b) {
        if (a == null || b == null) {
            return 0;
        }
        return distance3(a.getX(), a.getY(), b.getX(), b.getY());
    }

    public static final int distance3(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static Grid getNextGridByDir(Grid grid, Dir dir) {
        return getNextGridByDir(grid, dir, true);
    }

    public static Grid getNextGridByDir(Grid grid, Dir dir, boolean nonBlock) {

        if (grid == null) {
            return null;
        }
        if (dir == Dir.NONE) {
            return null;
        }
        Grid[] nears = grid.getNears();
        Grid ret = nears[dir.getIndex()];
        if (ret == null) {
            return null;
        }

        if (ret.isBlock() && nonBlock) {
            return null;
        }

        return ret;
    }

    public static int[] countDirectionAddition(Dir dir) {
        int[] add = new int[2];
        switch (dir) {
            case TOP:
                add[1] = -1;
                break;
            case RIGHT_TOP:
                add[1] = -1;
                add[0] = 1;
                break;
            case RIGHT:
                add[0] = 1;
                break;
            case RIGHT_BOTTOM:
                add[1] = 1;
                add[0] = 1;
                break;
            case BOTTOM:
                add[1] = 1;
                break;
            case LEFT_BOTTOM:
                add[1] = 1;
                add[0] = -1;
                break;
            case LEFT:
                add[0] = -1;
                break;
            case LEFT_TOP:
                add[1] = -1;
                add[0] = -1;
                break;
            case NONE:
                add[1] = 0;
                add[0] = 0;
                break;
        }

        return add;
    }
}
