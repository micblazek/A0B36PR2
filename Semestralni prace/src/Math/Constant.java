/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

/**
 *
 * @author michalblazek
 */
public class Constant extends Expr{
    public double constant;

    public Constant(double constant) {
        this.constant = constant;
    }

    public Constant() {
    }
    

    @Override
    double evaluate() {
        return this.constant;
    }

    @Override
    Expr derive(char var) {
        return new Constant(0);
    }

    @Override
    Expr simplify() {
        return new Constant(this.constant);
    }

    @Override
    public String toString() {
        return String.format("%.3f",constant);
    }
    
    
}