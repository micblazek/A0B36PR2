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

    @Override
    public BoundingBox getBoundingBox() {
        return new BoundingBox(this, 0, 0, 0, 0);
    }

    @Override
    public BoundingBox getBoundingBox(int x, int y) {
        return new BoundingBox(this, x, y, 0, 0);
    }   

    @Override
    public int length() {
        return 0;
    }

    @Override
    public int missingItemInBinOp() {
        return 1;
    }

    @Override
    public int missingItemInBinOp(int hloubka) {
        return 1;
    }

    @Override
    public boolean containNull() {
        return true;
    }

    @Override
    public MathList<Variable> variablesInList() {
        return new MathList<Variable>();
    }

    @Override
    public void changeVariable(Variable v) {       
    }

    @Override
    public int height() {
        return 1;
    }

    @Override
    public MathList<BoundingBox> getAllBoundingBoxs() {
        MathList<BoundingBox> list = new MathList<BoundingBox>();
        list.add(this.getBoundingBox());
        return list;
    }

    @Override
    public MathList<BoundingBox> getAllBoundingBoxs(int x, int y) {
        MathList<BoundingBox> list = new MathList<BoundingBox>();
        list.add(this.getBoundingBox(x, y));
        return list;
    }
}
