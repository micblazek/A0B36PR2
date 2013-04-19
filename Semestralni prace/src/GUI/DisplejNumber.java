/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author michalblazek
 */
public class DisplejNumber {
    private String  value;
    private int x;
    private int y;

    public DisplejNumber(String value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return value+"["+x+", "+y+"]";
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
}
