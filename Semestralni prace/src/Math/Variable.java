/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

import GUI.BoundingBox;
import GUI.DisplejNumber;
import System.MathList;
import java.util.ArrayList;

/**
 *
 * @author michalblazek
 */
public class Variable extends Expr {

    public char name;
    public double value;

    public Variable() {
    }
    public Variable(char name, double value) {
        this.name = name;
        this.value = value;
    }

    public Variable(char name, Constant value) {
        this.name = name;
        this.value = value.evaluate();
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public Expr derive(char var) {
        if (name == var) {
            return new Constant(1);
        } else {
            return new Constant(0);
        }
    }

    @Override
    public Expr simplify() {
        return new Variable(name, value);
    }

    @Override
    public String toString() {
        return Character.toString(name);
    }

    @Override
    public MathList<DisplejNumber> ohodnot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MathList<DisplejNumber> ohodnot(ArrayList<Character> postupX, int delka, ArrayList<Character> postupY, int hloubka) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BoundingBox getBoundingBox() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BoundingBox getBoundingBox(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int length() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int missingItemInBinOp() {
        return 0;
    }

    @Override
    public int missingItemInBinOp(int hloubka) {
        return 0;
    }

    @Override
    public boolean containNull() {
        return false;
    }
  
}
