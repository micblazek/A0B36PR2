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
        if(((int)constant)==constant){
            return Integer.toString((int)constant);
        }else{
            return Double.toString(constant);
        }       
        //return Double.toString(constant);
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
    public BoundingBox getBoundingBox(){
        return new BoundingBox(this, 0, 0, this.length(), 1);
    }

    @Override
    public BoundingBox getBoundingBox(int x, int y) {
        return new BoundingBox(this, x, y, this.length(), 1);
    }   

    @Override
    public int length() {
        return this.toString().length();
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
      return false;
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
