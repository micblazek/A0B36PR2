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
public class Constant extends Expr {

    public double constant;

    public Constant(double constant) {
        this.constant = constant;
    }

    public Constant() {
    }

    @Override
    public double evaluate() {
        return this.constant;
    }

    @Override
    public Expr derive(char var) {
        return new Constant(0);
    }

    @Override
    public Expr simplify() {
        return new Constant(this.constant);
    }

    @Override
    public String toString() {
      //return String.format("%.3f", constant);
        
            return Double.toString(constant);    
    }

    @Override
    public MathList<DisplejNumber> ohodnot() {
        MathList<DisplejNumber> list = new MathList<DisplejNumber>();
        list.add(new DisplejNumber(Double.toString(this.constant), 0, 0));
        return list;
    }

    @Override
    public MathList<DisplejNumber> ohodnot(ArrayList<Character> postupX, int delka, ArrayList<Character> postupY, int hloubka) {
        MathList<DisplejNumber> list = new MathList<DisplejNumber>();
        list.add(new DisplejNumber(Double.toString(this.constant), BinOp.xGeometrickaRada(delka, postupX), BinOp.yGeometrickaRada(hloubka, postupY)));
        return list;
    }
    }
