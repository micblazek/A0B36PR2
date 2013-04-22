/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import java.util.ArrayList;

/**
 *
 * @author michalblazek
 */
public class DisplejFraction extends DisplejNumber {

    private int lenght;

    public DisplejFraction(String value, int x, int y, int lenght) {
        super(value, x, y);
        this.lenght = lenght;
    }

    @Override
    public String toString() {
        return "Zlomková čára [" + this.getX() + ", " + this.getY() + "]{" + lenght + "}";
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }
    

    public int vytvorDelku(ArrayList<DisplejNumber> list) {


        int zacatek = 0;
        int konec = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getX() >= konec) {
                konec = list.get(i).getX()+1;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (this.getY() == list.get(i).getY() && this.getX() != list.get(i).getX()) {
                if (list.get(i).getX() < this.getX() && list.get(i).getX() > zacatek) {
                    zacatek = list.get(i).getX() + 1;
                }
                if (list.get(i).getX() > this.getX() && list.get(i).getX() < konec) {
                    konec = list.get(i).getX();
                }
            }
        }
            return konec - zacatek;
    }
}
