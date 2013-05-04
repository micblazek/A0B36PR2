/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

import GUI.DisplejFraction;
import GUI.DisplejNumber;
import System.MathList;
import java.util.ArrayList;

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
                List.set(i, new BinOp(String.valueOf(List.get(i)).toCharArray()[0], (Expr) List.get(i - 1), new Bracers('(', new Constant(0), ')')));
                List.remove(i + 1);
                List.remove(i - 1);
                i--;
            } catch (IndexOutOfBoundsException ioobe) {
                // pro psaní hodnot real-time
                List.set(i, new BinOp(String.valueOf(List.get(i)).toCharArray()[0], (Expr) List.get(i - 1), new Constant(Integer.valueOf(1))));
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
                        List.set(i, new Bracers('(', new Constant(0), ')'));
                    } else {
                        List.set(i, new BinOp(String.valueOf(List.get(i)).toCharArray()[0], (Expr) List.get(i - 1), new Constant(Integer.valueOf(0))));
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
            //list.add(new DisplejNumber(Character.toString(this.operand), xGeometrickaRada(delka, postupX), yGeometrickaRada(hloubka, postupY)));
            int delkaZlomkoveCary = 1;
            if (this.c1.delkaBinOps() > this.c2.delkaBinOps()) {
                delkaZlomkoveCary = this.c1.delkaBinOps();
            } else {
                delkaZlomkoveCary = this.c2.delkaBinOps();
            }
            list.add(new DisplejFraction(Character.toString(this.operand), xGeometrickaRada(delka, postupX), yGeometrickaRada(hloubka, postupY), delkaZlomkoveCary));
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
            /*
             * Vytváření souřadnic pro +, -, * a mocninu
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
}
