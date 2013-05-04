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
public class NullSymbol extends Expr {

    @Override
    public String toString() {
        return " ";
    }
    
    @Override
    public double evaluate() {
        return 0;
    }

    @Override
    public Expr derive(char var) {
        return new NullSymbol();
    }

    @Override
    public Expr simplify() {
        return new NullSymbol();
    }

    @Override
    public MathList<DisplejNumber> ohodnot() {
        MathList<DisplejNumber> list = new MathList<DisplejNumber>();
        list.add(new DisplejNumber(this.toString(), 0, 0));
        return list;
    }

    @Override
    public MathList<DisplejNumber> ohodnot(ArrayList<Character> postupX, int delka, ArrayList<Character> postupY, int hloubka) {
        MathList<DisplejNumber> list = new MathList<DisplejNumber>();
        list.add(new DisplejNumber(this.toString(), BinOp.xGeometrickaRada(delka, postupX), BinOp.yGeometrickaRada(hloubka, postupY)));
        return list;
    }
}