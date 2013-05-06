/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

import GUI.DisplejNumber;
import System.MathList;
import java.util.ArrayList;

/**
 *
 * @author michalblazek
 */
public class Bracers extends Expr {

    public char b1;
    public Expr value;
    public char b2;

    public Bracers(char b1, Expr value, char b2) {
        this.b1 = b1;
        this.value = value;
        this.b2 = b2;
    }

    public Bracers() {
    }

    @Override
    public double evaluate() {
        return value.evaluate();
    }

    @Override
    public Expr derive(char var) {
        return value.derive(var);
    }

    @Override
    public Expr simplify() {
        return value.simplify();
    }

    @Override
    public String toString() {
        return b1 + value.toString() + b2;
    }

    @Override
    public MathList<DisplejNumber> ohodnot() {
        if (this.value.getClass() == BinOp.class) {
            return ((BinOp) this.value).ohodnot();
        } else {
            if (this.value.getClass() == Constant.class) {
                return ((Constant) this.value).ohodnot();
            }else
                return ((Bracers) this.value).ohodnot();
        }
    }

    @Override
    public MathList<DisplejNumber> ohodnot(ArrayList<Character> postupX, int delka, ArrayList<Character> postupY, int hloubka) {
         if (this.value.getClass() == BinOp.class) {
            return ((BinOp) this.value).ohodnot(postupX, delka, postupY, hloubka);
        } else {
            if (this.value.getClass() == Constant.class) {
                return ((Constant) this.value).ohodnot(postupX, delka, postupY, hloubka);
            }else
                return ((Bracers) this.value).ohodnot();
        }
    }
}
