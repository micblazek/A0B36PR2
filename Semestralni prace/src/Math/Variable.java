/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

import GUI.BoundingBox;
import System.MathList;

/**
 *
 * @author michalblazek
 */
public class Variable extends Expr implements Comparable<Variable>{

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
    public BoundingBox getBoundingBox() {
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

    @Override
    public MathList<Variable> variablesInList() {
        MathList<Variable> list = new MathList<Variable>();
        list.add(this);
        return list;
    }

    @Override
    public int compareTo(Variable o) {
        return this.toString().compareTo(o.toString());
    }

    @Override
    public void changeVariable(Variable v) {
        if(((Character)this.name).equals(((Character)v.name))){
            this.value = v.value;
        }    
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
