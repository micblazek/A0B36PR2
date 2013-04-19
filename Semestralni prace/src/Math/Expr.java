package Math;

public abstract class Expr {

    public abstract double evaluate();

    public abstract Expr derive(char var);

    public abstract Expr simplify();

    public int hloubkaBinOps() {
        int zanoreni = this.hloubkaBinOps(0);
        return zanoreni;
    }

    private int hloubkaBinOps(int pocitadlo) {
        int zanoreni = pocitadlo;

        if (this.getClass().equals(new BinOp().getClass())) {
            if (((BinOp) this).operand == '/') {
                zanoreni++;
                if (((BinOp) this).c1.hloubkaBinOps(zanoreni) > ((BinOp) this).c2.hloubkaBinOps(zanoreni)) {
                    return ((BinOp) this).c1.hloubkaBinOps(zanoreni);
                }
                return ((BinOp) this).c2.hloubkaBinOps(zanoreni);
            }else{
                 if (((BinOp) this).c1.hloubkaBinOps(zanoreni) > ((BinOp) this).c2.hloubkaBinOps(zanoreni)) {
                    return ((BinOp) this).c1.hloubkaBinOps(zanoreni);
                }
                return ((BinOp) this).c2.hloubkaBinOps(zanoreni);
            }
        }
        return zanoreni;
    }
    
    public int delkaBinOps() {
        int delka = this.delkaBinOps(0);
        return delka;
    }

    private int delkaBinOps(int pocitadlo) {
        int delka = pocitadlo;

        if (this.getClass().equals(new BinOp().getClass())) {
            if (((BinOp) this).operand == '+' || ((BinOp) this).operand == '-' || ((BinOp) this).operand == '*' || ((BinOp) this).operand == '^') {
                delka++;
                if (((BinOp) this).c1.delkaBinOps(delka) > ((BinOp) this).c2.delkaBinOps(delka)) {
                    return ((BinOp) this).c1.delkaBinOps(delka);
                }
                return ((BinOp) this).c2.delkaBinOps(delka);
            } else{
                if (((BinOp) this).c1.delkaBinOps(delka) > ((BinOp) this).c2.delkaBinOps(delka)) {
                    return ((BinOp) this).c1.delkaBinOps(delka);
                }
                return ((BinOp) this).c2.delkaBinOps(delka);
            }
        }
        return delka;
    }

    public int xPredchoziDelka() {
        return xPredchoziDelka(0);
    }
    
    public int xPredchoziDelka(int offset) {
        if (this.getClass().equals(new Constant().getClass())) {
            return Double.toString(((Constant)this).constant).length();
        }

        if (this.getClass().equals(new BinOp().getClass())) {
                if (((BinOp) this).operand == '/') {
                    if (((BinOp) this).c1.xPredchoziDelka(offset) > ((BinOp) this).c2.xPredchoziDelka(offset)) {
                        return ((BinOp) this).c1.xPredchoziDelka(offset);
                    } else {
                        return ((BinOp) this).c2.xPredchoziDelka(offset);
                    }
                } else {
                    if (((BinOp) this).c1.getClass().equals(new Constant().getClass()) && ((BinOp) this).c2.getClass().equals(new Constant().getClass())) {
                        return ((BinOp) this).c1.toString().length() + ((BinOp) this).c2.toString().length() + 1 + offset;
                    }
                    if (((BinOp) this).c1.getClass().equals(new BinOp().getClass()) && ((BinOp) this).c2.getClass().equals(new BinOp().getClass())) {              
                        return ((BinOp) this).c1.xPredchoziDelka(offset) + ((BinOp) this).c2.xPredchoziDelka(offset) + 1;
                    }
                    if (((BinOp) this).c1.getClass().equals(new BinOp().getClass()) && ((BinOp) this).c2.getClass().equals(new Constant().getClass())) {
                        return ((BinOp) this).c1.xPredchoziDelka(offset) + ((BinOp) this).c2.toString().length() + 1;
                    }
                    if (((BinOp) this).c1.getClass().equals(new Constant().getClass()) && ((BinOp) this).c2.getClass().equals(new BinOp().getClass())) {
                        
                        return ((BinOp) this).c1.toString().length() + ((BinOp) this).c2.xPredchoziDelka(offset)+ 1;
                    }
                }      
        }
        return -1;
    }
}
