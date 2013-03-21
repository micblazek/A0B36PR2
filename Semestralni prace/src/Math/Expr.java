package Math;
 
public abstract class Expr {
 
    abstract double evaluate();
 
    abstract Expr derive(char var);
 
    abstract Expr simplify();

}
