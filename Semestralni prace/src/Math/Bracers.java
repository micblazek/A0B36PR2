/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;
/**
 *
 * @author michalblazek
 */
public class Bracers extends Expr{
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
        return b1+value.toString()+b2;
    }
    
    
}
