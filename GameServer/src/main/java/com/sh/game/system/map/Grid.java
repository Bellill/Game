package com.sh.game.system.map;

import com.sh.game.system.map.obj.IMapObject;
import com.sh.game.util.GeomUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 格子(更具体的点)
 *
 * @author 张力
 */
public class Grid extends Point {

    private int id;

    // A*寻路相关的属性-总消耗
    private int f;

    // A*寻路相关的属性-h
    private int h;

    // A*寻路相关的属性-g
    private int g;

    // A*寻路相关的属性-父节点
    private Grid p;

    // A*寻路结果相关属性-子节点
    private Grid s;

    private boolean block;

    /**
     * 是否安全点
     */
    private boolean safe;

    /**
     * 是否可挖掘
     */
    private boolean diggable;

    /**
     * 是否正常点
     */
    private boolean normal;

    private boolean event;

    private Grid[] nears;

    private boolean grass;

    private boolean special6;

    private boolean special7;       //神龙皇城 泡点经验3倍区

    private boolean special8;       //神龙皇城 泡点经验2倍区

    /**
     * 格子中的对象
     */
    private Map<Long, IMapObject> objectMap = new ConcurrentHashMap<>();

    public Grid() {

    }

    public Grid(String xy) {
        int _index = xy.indexOf('_');
        this.x = Integer.parseInt(xy.substring(0, _index));
        this.y = Integer.parseInt(xy.substring(_index + 1));
        this.id = GeomUtil.getPointId(x, y);
    }

    public Grid(int x, int y) {
        this.x = x;
        this.y = y;
        this.id = GeomUtil.getPointId(x, y);
    }

    public Grid(int id) {
        this.x = id >>> 16;
        this.y = id & 0xFFFF;
    }

    /**
     * 设置格子的相邻点
     *
     * @param dir   方向
     * @param point 点
     */
    public void putNear(Dir dir, Grid point) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public boolean isDiggable() {
        return diggable;
    }

    public void setDiggable(boolean diggable) {
        this.diggable = diggable;
    }

    public boolean isNormal() {
        return normal;
    }

    public void setNormal(boolean normal) {
        this.normal = normal;
    }

    public Grid[] getNears() {
        return nears;
    }

    public void setNears(Grid[] nears) {
        this.nears = nears;
    }

    public Map<Long, IMapObject> getObjectMap() {
        return objectMap;
    }

    public void setObjectMap(Map<Long, IMapObject> objectMap) {
        this.objectMap = objectMap;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public boolean isEvent() {
        return event;
    }

    public void setEvent(boolean event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Grid[" + this.x + "," + this.y + "]-[" + this.f + "]";
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public Grid getP() {
        return p;
    }

    public void setP(Grid p) {
        this.p = p;
    }

    public Grid getS() {
        return s;
    }

    public void setS(Grid s) {
        this.s = s;
    }

    public boolean isGrass() {
        return grass;
    }

    public void setGrass(boolean grass) {
        this.grass = grass;
    }

    public boolean isSpecial6() {
        return special6;
    }

    public void setSpecial6(boolean special6) {
        this.special6 = special6;
    }

    public boolean isSpecial7() {
        return special7;
    }

    public void setSpecial7(boolean special7) {
        this.special7 = special7;
    }

    public boolean isSpecial8() {
        return special8;
    }

    public void setSpecial8(boolean special8) {
        this.special8 = special8;
    }

    public void clearRouting() {
        p = null;
        s = null;
        f = 0;
        g = 0;
        h = 0;
    }
}
