/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

import GUI.BoundingBox;
import GUI.DisplejFraction;
import GUI.DisplejNumber;
import System.MathList;
import System.XCompareMathList;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author michalblazek
 */
public class BinOp extends Expr {

    public char operand;
    public Expr c1;
    public Expr c2;

    public BinOp(char operand, Expr constant1, Expr constant2) {
        this.operand = operand;
        this.c1 = constant1;
        this.c2 = constant2;
    }

    public BinOp() {
    }

    @Override
    public double evaluate() {
        switch (operand) {
            case '+': {
                return c1.evaluate() + c2.evaluate();
            }
            case '-': {
                return c1.evaluate() - c2.evaluate();
            }
            case '*': {
                return c1.evaluate() * c2.evaluate();
            }
            case '/': {
                return c1.evaluate() / c2.evaluate();
            }
            case '^': {
                return Math.pow(c1.evaluate(), c2.evaluate());
            }
            default: {
                return Double.NaN;
            }
        }
    }

    @Override
    public Expr derive(char var) {
        switch (operand) {
            case '+': {
                return new BinOp('+', c1.derive(var), c2.derive(var));
            }
            case '-': {
                return new BinOp('-', c1.derive(var), c2.derive(var));
            }
            case '*': {
                return new BinOp('+', new BinOp('*', c1, c2.derive(var)), new BinOp('*', c2, c1.derive(var)));
            }
            case '/': {
                return new BinOp('/', new BinOp('-', new BinOp('*', c1.derive(var), c2), new BinOp('*', c1, c2.derive(var))), new BinOp('*', c2, c2));
            }
            case '^': {
                if (c1.derive(var).evaluate() == 1) {
                    return new BinOp('*', new BinOp('^', new BinOp('*', c2, c1), new Constant(c2.evaluate())), c1.derive(var));
                } else {
                    return c1.derive(var);
                }
            }
            default: {
                return new Constant(Double.NaN);
            }
        }

    }

    @Override
    public Expr simplify() {
        Expr x1, x2;

        x1 = c1.simplify();
        x2 = c2.simplify();

        switch (operand) {
            case '+': {
                if ("Math.Constant".equals(x1.getClass().getName())) {
                    if (x1.evaluate() == 0) {
                        return x2;
                    }
                    if ("Math.Constant".equals(x2.getClass().getName())) {
                        if (x2.evaluate() == 0) {
                            return x1;
                        }
                    }
                }
                break;
            }
            case '-': {
                if ("Math.Constant".equals(x1.getClass().getName())) {
                    if (x1.evaluate() == 0) {
                        return x2;
                    }
                    if ("Math.Constant".equals(x2.getClass().getName())) {
                        if (x2.evaluate() == 0) {
                            return x1;
                        }
                    }
                    break;
                }
            }
            case '*': {
                if ("Math.Constant".equals(x1.getClass().getName())) {
                    if (x1.evaluate() == 0) {
                        return new Constant(0);
                    }
                }
                if ("Math.Constant".equals(x2.getClass().getName())) {
                    if (x2.evaluate() == 0) {
                        return new Constant(0);

                    }
                }
                if ("Math.Constant".equals(x1.getClass().getName())) {
                    if (x1.evaluate() == 1) {
                        return x2;

                    }
                }
                if ("Math.Constant".equals(x2.getClass().getName())) {
                    if (x2.evaluate() == 1) {
                        return x1;
                    }
                }

                break;

            }
            case '/': {
                if ("Math.Constant".equals(x1.getClass().getName())) {
                    if (x1.evaluate() == 0) {
                        return new Constant(0);
                    }
                }
                if ("Math.Constant".equals(x2.getClass().getName())) {
                    if (x2.evaluate() == 1) {
                        return x1;
                    }
                }
            }
            case '^': {
                if ("Math.Constant".equals(x1.getClass().getName())) {
                    if (x1.evaluate() == 0) {
                        return new Constant(0);
                    }
                }
                if ("Math.Constant".equals(x1.getClass().getName())) {
                    if (x1.evaluate() == 1) {
                        return new Constant(1);
                    }
                }
                if ("Math.Constant".equals(x2.getClass().getName())) {
                    if (x2.evaluate() == 1) {
                        return x1;
                    }
                }
                if ("Math.Constant".equals(x2.getClass().getName())) {
                    if (x2.evaluate() == 0) {
                        return new Constant(1);
                    }
                }
            }


        }
        if ("Math.Constant".equals(x2.getClass().getName()) && "Math.Constant".equals(x1.getClass().getName())) {
            return new Constant(new BinOp(operand, x1, x2).evaluate());
        } else {
            return new BinOp(operand, x1, x2);
        }
    }

    @Override
    public String toString() {
        return c1.toString() + operand + c2.toString();
    }

    public static Expr fromArrayList(ArrayList List) throws ArrayIndexOutOfBoundsException {
        if (List.size() == 1 && List.get(0).getClass() == BinOp.class) {
            return (Expr) List.get(0);
        }

        int pocetzavorek = 0;
        int indexKZ = 0; // Index konečné závorky
        int indexZZ = Integer.MAX_VALUE; // Index začátku závorky

        //Spočítá počet závorek, uzavřených dvojic.
        for (int i = 0; List.size() > i; i++) {
            if (List.get(i).toString().charAt(0) == '(' && List.get(i).getClass() != Bracers.class) {
                pocetzavorek++;
            }
        }

        // Jestliže je první znak řetězce znak '-', bude první čílso záporné.
        if ((List.get(0).toString().charAt(0) == '-') && List.get(0).toString().length() == 1) {
            List.add(1, new Constant(Double.valueOf(List.get(1).toString()) * -1));
            List.remove(0);
            List.remove(1); // odepbere se index 0, to co bylo index 1 je 0, 2 je 1
        }
        if ((List.get(0).toString().charAt(0) == '+') && List.get(0).toString().length() == 1) {
            List.remove(0);
        }
        // Převede čísla v řetězci na konstanty.
        // PROBLÉM
        for (int i = 0; i < List.size(); i++) {
            if (Character.isDigit(List.get(i).toString().charAt(0)) && List.get(i).getClass().equals(Constant.class) == false) {
                List.set(i, new Constant(Double.valueOf(List.get(i).toString())));
            }
        }

        while (pocetzavorek != 0) {
            //Najde index nejvnitřejší konečné závorky.
            for (int i = 0; indexKZ == 0; i++) {
                try {
                    if (List.get(i).toString().charAt(0) == ')') {
                        indexKZ = i;
                    }
                } catch (IndexOutOfBoundsException ix) {
                    // Pro případ real-time tvoření, jestliže je zadaná jenom jedna část závorek, bude nakonec přidána mezera a označena joko koncová závorka
                    indexKZ = i;
                    List.add(" ");
                }
            }
            //Najde index nejvnitřejší začínající závorky.
            for (int i = indexKZ; indexZZ == Integer.MAX_VALUE; i--) {
                if (List.get(i).toString().charAt(0) == '(' && List.get(i).getClass() != Bracers.class) {
                    indexZZ = i;
                }
            }
            //Vytvoří nový ArrayList obsahujicí řetězec nejvnitřejší závorky.
            ArrayList ListP = new ArrayList();
            for (int j = indexZZ + 1; j != indexKZ; j++) {
                ListP.add(List.get(j));
            }
            for (int j = indexKZ; j != indexZZ - 1; j--) {
                List.remove(j);
            }


            //Vytvoří BinOp z vnitřku závorek
            //List.add(indexZZ, BinOp.fromArrayList(ListP));
            try {
                List.add(indexZZ, BinOp.fromArrayList(ListP));
            } catch (IndexOutOfBoundsException e) {
                List.add("(");
            }
            ListP.clear();
            pocetzavorek--;
            indexKZ = 0;
            indexZZ = Integer.MAX_VALUE;
        }
        // Jestliže je za nebo před závorkou číslo bude bude mezi číslo a závorku vložen znak násobení.
        for (int i = 0; i < List.size() - 1; i++) {
            if (Character.isDigit(List.get(i).toString().charAt(0)) && Character.isDigit(List.get(i + 1).toString().charAt(0))) {
                List.add(i + 1, '*');
            }
        }
        /**
         * Vlastní výpočet, vložený řetězec skládá na class BinOp podle
         * matematických zákonů. Nejprve se provede násobení a dělení a poté
         * teprve sčítání a odčítání.
         */
        for (int i = 0; i < List.size(); i++) {
            if (List.get(i).getClass() == "".getClass() && (List.get(i).toString().charAt(0) == '^')) {
                List.set(i, new BinOp(String.valueOf(List.get(i)).toCharArray()[0], (Expr) List.get(i - 1), (Expr) List.get(i + 1)));
                List.remove(i + 1);
                List.remove(i - 1);
                i--;
            }
        }

        for (int i = 0; i < List.size(); i++) {
            try {
                if (List.get(i).getClass() == "".getClass() && (List.get(i).toString().charAt(0) == '*' || List.get(i).toString().charAt(0) == '/')) {
                    List.set(i, new BinOp(String.valueOf(List.get(i)).toCharArray()[0], (Expr) List.get(i - 1), (Expr) List.get(i + 1)));
                    List.remove(i + 1);
                    List.remove(i - 1);
                    i--;
                }
            } catch (ClassCastException cce) {
                List.set(i, new BinOp(String.valueOf(List.get(i)).toCharArray()[0], (Expr) List.get(i - 1), new Bracers('(', new NullSymbol(), ')')));
                List.remove(i + 1);
                List.remove(i - 1);
                i--;
            } catch (IndexOutOfBoundsException ioobe) {
                // pro psaní hodnot real-time
                List.set(i, new BinOp(String.valueOf(List.get(i)).toCharArray()[0], (Expr) List.get(i - 1), new NullSymbol()));
                List.remove(i - 1);
                i--;
            }
        }

        for (int i = 0; i < List.size(); i++) {
            if (List.get(i).getClass() == "".getClass()) {
                try {
                    List.set(i, new BinOp(String.valueOf(List.get(i)).toCharArray()[0], (Expr) List.get(i - 1), (Expr) List.get(i + 1)));
                    List.remove(i + 1);
                    List.remove(i - 1);
                    i--;
                } catch (IndexOutOfBoundsException e) {
                    // pro psaní hodnot real-time
                    if (List.get(i).toString().charAt(0) == '(') {
                        List.set(i, new Bracers('(', new NullSymbol(), ')'));
                    } else {
                        List.set(i, new BinOp(String.valueOf(List.get(i)).toCharArray()[0], (Expr) List.get(i - 1), new NullSymbol()));
                        List.remove(i - 1);
                        i--;
                    }

                }

            }
        }
        return new Bracers('(', (Expr) List.get(0), ')');
    }

    @Override
    public MathList<DisplejNumber> ohodnot() {
        int hloubka = this.hloubkaBinOps();
        int delka = this.delkaBinOps();
        MathList<Character> postupX = new MathList<Character>();
        MathList<Character> postupY = new MathList<Character>();
        return this.ohodnot(postupX, delka, postupY, hloubka);
    }

    @Override
    public MathList<DisplejNumber> ohodnot(ArrayList<Character> postupX, int delka, ArrayList<Character> postupY, int hloubka) {
        MathList<DisplejNumber> list = new MathList<DisplejNumber>();

        if (this.operand == '/') {
            /* 
             * Vytváření zlomku = posun Y souřadnice
             * Přidá operand dělení mezi čitatele a jmenovatele
             */
            int delkaZlomkoveCary = 1;
            int xZlomek = 0;
            if (this.c1.length() > this.c2.length()) {
                //delkaZlomkoveCary = this.c1.delkaBinOps()*4-1;
//                delkaZlomkoveCary = (int) (2 * Math.pow(2, this.c1.delkaBinOps()) - 1);
                delkaZlomkoveCary = c1.length();
                MathList<DisplejNumber> m = c1.ohodnot(postupX, delka, postupY, hloubka);
                Collections.sort(m, new XCompareMathList());
                xZlomek = m.get(0).getX();
                
                //xZlomek = xGeometrickaRada(delka, postupX)-c1.missingItemInBinOp();
            } else {
                MathList<DisplejNumber> m = c2.ohodnot(postupX, delka, postupY, hloubka);
                Collections.sort(m, new XCompareMathList());
                xZlomek = m.get(0).getX();
                delkaZlomkoveCary = c2.length();
               // xZlomek = xGeometrickaRada(delka, postupX)-c2.missingItemInBinOp();
//                delkaZlomkoveCary = (int) (2 * Math.pow(2, this.c2.delkaBinOps()) - 1);
            }
//            if((c2.length()==0 || c2.length()<c1.length()) && c1.getClass().equals(BinOp.class)){
//                if(((BinOp)c1).c1.missingItemInBinOp()<((BinOp)c1).c2.missingItemInBinOp()){
//                    xZlomek = xGeometrickaRada(delka, postupX)-((BinOp)c1).c1.missingItemInBinOp();
//                }else{
//                    xZlomek = xGeometrickaRada(delka, postupX)-((BinOp)c1).c2.missingItemInBinOp();
//                }
//            }
            
            list.add(new DisplejFraction(Character.toString(this.operand), xZlomek, yGeometrickaRada(hloubka, postupY), delkaZlomkoveCary));
            //System.out.println(new DisplejFraction(Character.toString(this.operand), xZlomek, yGeometrickaRada(hloubka, postupY), delkaZlomkoveCary));
            //list.add(new DisplejFraction(Character.toString(this.operand), xGeometrickaRada(delka, postupX)-this.missingItemInBinOp(), yGeometrickaRada(hloubka, postupY), delkaZlomkoveCary));
            /*
             * Vytváření čitatele
             */
            if (this.c1.getClass().equals(new BinOp().getClass()) || (this.c1.getClass().equals(new Bracers().getClass()))) {
                postupY.add('+');
                if (this.c1.getClass().equals(new BinOp().getClass())) {
                    //Když je čitatel další operace, bude rekurzivně zavolaná tatáž metoda
                    list.addAll(((BinOp) this.c1).ohodnot(postupX, delka, postupY, hloubka));
                } else {
                    list.addAll(((Bracers) this.c1).ohodnot(postupX, delka, postupY, hloubka));
                }
            } else {
                //Když čitatel není operace přiřadí mu souřednice Y o 1 vyší než operandu dělení
                postupY.add('+');
                list.add(new DisplejNumber(this.c1.toString(), xGeometrickaRada(delka, postupX), yGeometrickaRada(hloubka, postupY)));
            }
            postupY.remove(postupY.size() - 1);
            /*
             * Vytváření jmenovatele
             */
            if (this.c2.getClass().equals(new BinOp().getClass()) || (this.c2.getClass().equals(new Bracers().getClass()))) {
                postupY.add('-');
                if (this.c2.getClass().equals(new BinOp().getClass())) {
                    //Když je jmenovatel další operace, bude rekurzivně zavolaná tatáž metoda
                    list.addAll(((BinOp) this.c2).ohodnot(postupX, delka, postupY, hloubka));
                } else {
                    list.addAll(((Bracers) this.c2).ohodnot(postupX, delka, postupY, hloubka));
                }
            } else {
                //Když jmenovatel není operace přiřadí mu souřednice Y o 1 vyší než operandu dělení
                postupY.add('-');
                list.add(new DisplejNumber(this.c2.toString(), xGeometrickaRada(delka, postupX), yGeometrickaRada(hloubka, postupY)));
            }
            postupY.remove(postupY.size() - 1);
        } else {
            if (this.operand == '^') {
                /*
                 * Vytváření mocněnce
                 */
                if (this.c1.getClass().equals(new BinOp().getClass()) || (this.c1.getClass().equals(new Bracers().getClass()))) {
                    postupY.add('-');
                    if (this.c1.getClass().equals(new BinOp().getClass())) {
                        //Když je mocněnec další operace, bude rekurzivně zavolaná tatáž metoda
                        list.addAll(((BinOp) this.c1).ohodnot(postupX, delka, postupY, hloubka));
                    } else {
                        list.addAll(((Bracers) this.c1).ohodnot(postupX, delka, postupY, hloubka));
                    }
                } else {
                    //Když mocněnec není operace přiřadí mu souřednice Y o 1 nižší než nocnitely
                    postupY.add('-');
                    list.add(new DisplejNumber(this.c1.toString(), xGeometrickaRada(delka, postupX), yGeometrickaRada(hloubka, postupY)));
                }
                postupY.remove(postupY.size() - 1);

                list.add(new DisplejNumber("^", 0, 0));
                /*
                 * Vytváření mocnitele
                 */
                if (this.c2.getClass().equals(new BinOp().getClass()) || (this.c2.getClass().equals(new Bracers().getClass()))) {
                    postupY.add('+');
                    postupX.add('+');
                    if (this.c2.getClass().equals(new BinOp().getClass())) {
                        //Když je jmenovatel další operace, bude rekurzivně zavolaná tatáž metoda
                        list.addAll(((BinOp) this.c2).ohodnot(postupX, delka, postupY, hloubka));
                    } else {
                        list.addAll(((Bracers) this.c2).ohodnot(postupX, delka, postupY, hloubka));
                    }
                } else {
                    //Když jmenovatel není operace přiřadí mu souřednice Y o 1 vyší mocněnci
                    postupY.add('+');
                    postupX.add('+');
                    list.add(new DisplejNumber(this.c2.toString(), xGeometrickaRada(delka, postupX), yGeometrickaRada(hloubka, postupY)));
                }
                postupY.remove(postupY.size() - 1);
                postupX.remove(postupX.size() - 1);
            } else {
                /*
                 * Vytváření souřadnic pro +, -, *
                 */
                list.add(new DisplejNumber(Character.toString(this.operand), xGeometrickaRada(delka, postupX), yGeometrickaRada(hloubka, postupY)));
                /*
                 * Vytváření souřadnic nalevo od operátoru
                 */
                if (this.c1.getClass().equals(new BinOp().getClass()) || (this.c1.getClass().equals(new Bracers().getClass()))) {
                    postupX.add('-');
                    if (this.c1.getClass().equals(new BinOp().getClass())) {
                        // Když je číslo nalevo od znaménka operace, bude rekurzivně zavolaná tatáž metoda
                        list.addAll(((BinOp) this.c1).ohodnot(postupX, delka, postupY, hloubka));
                    } else {
                        list.addAll(((Bracers) this.c1).ohodnot(postupX, delka, postupY, hloubka));
                    }
                } else {
                    //Když číslo nalevo od znaménka není operace přiřadí mu souřednice X o 1 menší než operandu 
                    postupX.add('-');
                    list.add(new DisplejNumber(this.c1.toString(), xGeometrickaRada(delka, postupX), yGeometrickaRada(hloubka, postupY)));
                }
                postupX.remove(postupX.size() - 1);
                /*
                 * Vytváření souřadnic napravo od operátoru
                 */
                if (this.c2.getClass().equals(new BinOp().getClass()) || (this.c2.getClass().equals(new Bracers().getClass()))) {
                    postupX.add('+');
                    if (this.c2.getClass().equals(new BinOp().getClass())) {
                        //Když je jmenovatel další operace, bude rekurzivně zavolaná tatáž metoda
                        list.addAll(((BinOp) this.c2).ohodnot(postupX, delka, postupY, hloubka));
                    } else {
                        list.addAll(((Bracers) this.c2).ohodnot(postupX, delka, postupY, hloubka));
                    }
                } else {
                    //Když jmenovatel není operace přiřadí mu souřednice Y o 1 vyší než operandu dělení
                    postupX.add('+');
                    list.add(new DisplejNumber(this.c2.toString(), xGeometrickaRada(delka, postupX), yGeometrickaRada(hloubka, postupY)));
                }
                postupX.remove(postupX.size() - 1);
            }
        }
        return list;
    }

    protected static int yGeometrickaRada(int hloubka, ArrayList<Character> prubeh) {
        int g = 0;
        hloubka--;
        for (int i = 0; i < prubeh.size(); i++) {
            if (prubeh.get(i) == '+' || prubeh.get(i) == '-') {
                g += Double.valueOf(prubeh.get(i) + Double.toString(Math.pow(2, hloubka)));
                hloubka--;
            } else {
                break;
            }
        }
        return g;
    }

    protected static int xGeometrickaRada(int delka, ArrayList<Character> prubeh) {
        int g = 0;
        delka--;
        for (int i = 0; i < prubeh.size(); i++) {
            if (prubeh.get(i) == '+' || prubeh.get(i) == '-') {
                g += Double.valueOf(prubeh.get(i) + Double.toString(Math.pow(2, delka)));
                delka--;
            } else {
                break;
            }
        }
        return g;
    }

    @Override
    public BoundingBox getBoundingBox() {
        if (operand == '/') {
            if (this.c1.toString().length() > this.c2.toString().length()) {
                return new BoundingBox(this, 0, 0, this.c1.length(), 2);
            } else {
                return new BoundingBox(this, 0, 0, this.c2.length(), 2);
            }
        } else {
            return new BoundingBox(this, 0, 0, this.length(), 1);
        }
    }

    @Override
    public BoundingBox getBoundingBox(int x, int y) {
        if (operand == '/') {
            if (this.c1.toString().length() > this.c2.toString().length()) {
                return new BoundingBox(this, x, y, this.c1.length(), 2);
            } else {
                return new BoundingBox(this, x, y, this.c2.length(), 2);
            }
        } else {
            return new BoundingBox(this, x, y, this.length(), 1);
        }
    }

    @Override
    public int length() {
        if (operand == '/') {
            if (this.c1.toString().length() > this.c2.toString().length()) {
                return this.c1.length();
            } else {
                return this.c2.length();
            }
        }else{
            return c1.length()+c2.length()+1;
        }
    }

    @Override
    public int missingItemInBinOp() {
        int predpokladanyPocet = (int) Math.pow(2, this.delkaBinOps()+this.hloubkaBinOps());
        int skutecnost = this.missingItemInBinOp(0);
        return predpokladanyPocet - skutecnost;
    }

    @Override
    public int missingItemInBinOp(int pocitadlo) {
        int hloubka = pocitadlo;
        int predpoklad = (int) Math.pow(2, this.delkaBinOps()+this.hloubkaBinOps());
        if (this.getClass().equals(new BinOp().getClass())) {
            hloubka++;
            return this.c1.missingItemInBinOp(hloubka) + this.c2.missingItemInBinOp(hloubka);
        }
        if(this.getClass().equals(new Bracers().getClass())){
            return this.missingItemInBinOp(pocitadlo);
        }
        return hloubka;
    }
    @Override
    public boolean containNull(){
        if(c1.containNull() || c2.containNull()){
            return true;
        }else{
            return false;
        }
    }
}
