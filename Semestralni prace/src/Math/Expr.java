package Math;

import GUI.DisplejNumber;
import java.util.ArrayList;

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
        if(this.getClass().equals(new Bracers().getClass())){
            return ((Bracers) this).value.hloubkaBinOps(zanoreni);
        }
        return zanoreni;
    }
    
    public int delkaBinOps() {
        int delka = this.delkaBinOps(0);
        return delka;
    }

    private int delkaBinOps(int pocitadlo) {
        int delka = pocitadlo;
            
        if (this.getClass().equals(new Bracers().getClass())) {
            //delka+=2;
            return ((Bracers) this).value.delkaBinOps(delka);
        }
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
    
    public abstract ArrayList<DisplejNumber> ohodnot();
    
    public abstract ArrayList<DisplejNumber> ohodnot(ArrayList<Character> postupX, int delka, ArrayList<Character> postupY, int hloubka);
}