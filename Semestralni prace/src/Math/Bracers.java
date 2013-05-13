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
    public BoundingBox getBoundingBox() {
        return new BoundingBox(this, 0, 0, this.length(), value.getBoundingBox().getHeight());
    }

    @Override
    public BoundingBox getBoundingBox(int x, int y) {
        return new BoundingBox(this, x, y, this.length(), value.getBoundingBox().getHeight());
    }

    @Override
    public int length() {
        return value.length();
    }

    @Override
    public int missingItemInBinOp() {
        return value.missingItemInBinOp();
    }

    @Override
    public int missingItemInBinOp(int hloubka) {
        return value.missingItemInBinOp(hloubka);
    }

    @Override
    public boolean containNull() {
        return value.containNull();
    }

    @Override
    public MathList<Variable> variablesInList() {
        return value.variablesInList();
    }

    @Override
    public void changeVariable(Variable v) {
        this.value.changeVariable(v);
    }

    @Override
    public int height() {
        return value.height();
    }

    @Override
    public MathList<BoundingBox> getAllBoundingBoxs() {
        return value.getAllBoundingBoxs();
    }

    @Override
    public MathList<BoundingBox> getAllBoundingBoxs(int x, int y) {
        return value.getAllBoundingBoxs(x, y);
    }
}
