package Math;

public abstract class Expr {

    public abstract double evaluate();

    public abstract Expr derive(char var);

    public abstract Expr simplify();
}
