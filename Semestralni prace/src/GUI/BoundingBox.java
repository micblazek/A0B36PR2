/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Math.Expr;

/**
 *
 * @author michalblazek
 */
public class BoundingBox {
    Expr e;
    int x;
    int y;
    int width;
    int height;

    public BoundingBox(Expr e, int x, int y, int width, int height) {
        this.e = e;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return e.toString()+" ["+x+", "+y+"], ("+width+", "+height+")\n";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Expr getE() {
        return e;
    }

    public void setE(Expr e) {
        this.e = e;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    } 
}
