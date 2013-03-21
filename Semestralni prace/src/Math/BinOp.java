/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

/**
 *
 * @author michalblazek
 */
public class BinOp extends Expr {

    public char operand;
    public Expr constant1;
    public Expr constant2;

//    public BinOp(char operand, Constant constant1, Constant constant2) {
//        this.operand = operand;
//        this.constant1 = constant1;
//        this.constant2 = constant2;
//    }
    public BinOp(char operand, Expr constant1, Expr constant2) {
        this.operand = operand;
        this.constant1 = constant1;
        this.constant2 = constant2;
    }

    @Override
    double evaluate() {
        switch (operand) {
            case '+': {
                return constant1.evaluate() + constant2.evaluate();
            }
            case '-': {
                return constant1.evaluate() - constant2.evaluate();
            }
            case '*': {
                return constant1.evaluate() * constant2.evaluate();
            }
            case '/': {
                return constant1.evaluate() / constant2.evaluate();
            }
            default: {
                return Double.NaN;
            }
        }
    }

    @Override
    Expr derive(char var) {
        switch (operand) {
            case '+': {
                return new BinOp('+', constant1.derive(var), constant2.derive(var));
            }
            case '-': {
                return new BinOp('-', constant1.derive(var), constant2.derive(var));
            }
            case '*': {
                return new BinOp('+', new BinOp('*', constant1, constant2.derive(var)), new BinOp('*', constant2, constant1.derive(var)));
            }
            case '/': {
                return new BinOp('/', new BinOp('-', new BinOp('*', constant1.derive(var), constant2), new BinOp('*', constant1, constant2.derive(var))), new BinOp('*', constant2, constant2));
            }
            default: {
                return new Constant(Double.NaN);
            }
        }

    }

    @Override
    Expr simplify() {
        Expr x1, x2;

        x1 = constant1.simplify();
        x2 = constant2.simplify();

        switch (operand) {
            case '+': {
                if ("du1.Constant".equals(x1.getClass().getName())) {
                    if (x1.evaluate() == 0) {
                        return x2;
                    }
                    if ("du1.Constant".equals(x2.getClass().getName())) {
                        if (x2.evaluate() == 0) {
                            return x1;
                        }
                    }
                }
                break;
            }
            case '-': {
                if ("du1.Constant".equals(x1.getClass().getName())) {
                    if (x1.evaluate() == 0) {
                        return x2;
                    }
                    if ("du1.Constant".equals(x2.getClass().getName())) {
                        if (x2.evaluate() == 0) {
                            return x1;
                        }
                    }
                    break;
                }
            }
            case '*': {
                if ("du1.Constant".equals(x1.getClass().getName())) {
                    if (x1.evaluate() == 0) {
                        return new Constant(0);
                    }
                }
                if ("du1.Constant".equals(x2.getClass().getName())) {
                    if (x2.evaluate() == 0) {
                        return new Constant(0);

                    }
                }
                if ("du1.Constant".equals(x1.getClass().getName())) {
                    if (x1.evaluate() == 1) {
                        return x2;

                    }
                }
                if ("du1.Constant".equals(x2.getClass().getName())) {
                    if (x2.evaluate() == 1) {
                        return x1;
                    }
                }

                break;

            }
            case '/': {
            }

        }
        if("du1.Constant".equals(x2.getClass().getName()) && "du1.Constant".equals(x1.getClass().getName())){
            return new Constant(new BinOp(operand, x1, x2).evaluate());
        }else{
        return new BinOp(operand, x1, x2);
        }
    }

    @Override
    public String toString() {
        return "(" + constant1.toString() + " " + operand + " " + constant2.toString() + ")";
    }
}
