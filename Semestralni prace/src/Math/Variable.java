/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

/**
 *
 * @author michalblazek
 */
public class Variable extends Expr{
    public char name;
    public double value;    

    public Variable(char name, double value) {
        this.name = name;
        this.value = value;
    }

    public Variable(char name, Constant value) {
        this.name = name;
        this.value = value.evaluate();
    }

    @Override
    double evaluate() {
        return value;
    }

    @Override
    Expr derive(char var) {
        if(name == var){
            return new Constant(1);
        }else{
            return new Constant(0);
        }
    }

    @Override
    Expr simplify() {
        return new Variable(name, value);
    }

    @Override
    public String toString() {
        return Character.toString(name);
    }
    
    
}
