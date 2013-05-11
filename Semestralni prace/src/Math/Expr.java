package Math;

import GUI.BoundingBox;
import System.MathList;
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
            if (((BinOp) this).operand == '/' || ((BinOp) this).operand == '^') {
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
    
    public abstract BoundingBox getBoundingBox();
    
    public abstract BoundingBox getBoundingBox(int x, int y);
    
    public abstract MathList<BoundingBox> getAllBoundingBoxs();
    
    public abstract MathList<BoundingBox> getAllBoundingBoxs(int x, int y);
    
    public abstract int length();
    
    public abstract int height();
    
    public abstract int missingItemInBinOp();
    
    public abstract int missingItemInBinOp(int hloubka);
    
    public abstract boolean containNull();
    
    public abstract MathList<Variable> variablesInList();
    
    public abstract void changeVariable(Variable v);
    
}