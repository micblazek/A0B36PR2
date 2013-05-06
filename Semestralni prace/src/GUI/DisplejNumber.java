/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;

/**
 *
 * @author michalblazek
 */
public class DisplejNumber{

    private String value;
    private int x;
    private int y;

    public DisplejNumber(String value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    public DisplejNumber() {
    }

    @Override
    public String toString() {
        return value + "[" + x + ", " + y + "]";
    }

    public String getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static boolean isExist(int x, int y, ArrayList<DisplejNumber> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getX() == x && list.get(i).getY() == y) {
                return true;
            }
        }
        return false;
    }
}
