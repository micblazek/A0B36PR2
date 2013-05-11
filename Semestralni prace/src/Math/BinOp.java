/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

import GUI.BoundingBox;
import System.MathList;
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
                }
                if ("Math.Constant".equals(x2.getClass().getName())) {
                    if (x2.evaluate() == 0) {
                        return x1;
                    }
                }
                break;
            }
            case '-': {
                if ("Math.Constant".equals(x1.getClass().getName())) {
                    if (x1.evaluate() == 0) {
                        return new BinOp('-', new NullSymbol(), x2);
                    }
                }
                if ("Math.Constant".equals(x2.getClass().getName())) {
                    if (x2.evaluate() == 0) {
                        return x1;
                    }
                }
                break;
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
                if ("Math.Constant".equals(x1.getClass().getName())) {
                    if (x1.evaluate() == 1) {
                        return this;
                    }
                }
                if ("Math.Constant".equals(x2.getClass().getName())) {
                    if (x2.evaluate() == 0) {
                        return this;
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
    public BoundingBox getBoundingBox() {
        if (operand == '/') {
            if (this.c1.toString().length() > this.c2.toString().length()) {
                return new BoundingBox(this, 0, 0, this.length(), this.height());
            } else {
                return new BoundingBox(this, 0, 0, this.length(), this.height());
            }
        } else {
            return new BoundingBox(this, 0, 0, this.length(), this.height());
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
            if (this.c1.length() > this.c2.length()) {
                return this.c1.length();
            } else {
                return this.c2.length();
            }
        } else {
            return c1.length() + c2.length() + 1;
        }
    }

    @Override
    public int missingItemInBinOp() {
        int predpokladanyPocet = (int) Math.pow(2, this.delkaBinOps() + this.hloubkaBinOps());
        int skutecnost = this.missingItemInBinOp(0);
        return predpokladanyPocet - skutecnost;
    }

    @Override
    public int missingItemInBinOp(int pocitadlo) {
        int hloubka = pocitadlo;
        int predpoklad = (int) Math.pow(2, this.delkaBinOps() + this.hloubkaBinOps());
        if (this.getClass().equals(new BinOp().getClass())) {
            hloubka++;
            return this.c1.missingItemInBinOp(hloubka) + this.c2.missingItemInBinOp(hloubka);
        }
        if (this.getClass().equals(new Bracers().getClass())) {
            return this.missingItemInBinOp(pocitadlo);
        }
        return hloubka;
    }

    @Override
    public boolean containNull() {
        if (c1.containNull() || c2.containNull()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public MathList<Variable> variablesInList() {
        MathList<Variable> list = new MathList<Variable>();
        list.addAll(c1.variablesInList());
        list.addAll(c2.variablesInList());
        return list;
    }

    @Override
    public void changeVariable(Variable v) {
        this.c1.changeVariable(v);
        this.c2.changeVariable(v);
    }

    @Override
    public int height() {
        if (operand == '/') {
            return c1.height() + c2.height() + 1;
        } else {
            if (this.c1.height() > this.c2.height()) {
                return this.c1.height();
            } else {
                return this.c2.height();
            }
        }
    }

    @Override
    public MathList<BoundingBox> getAllBoundingBoxs() {
        MathList<BoundingBox> list = new MathList<BoundingBox>();
        list.add(this.getBoundingBox());

        if (c1.height() >= c2.height()) {
            list.addAll(this.c1.getAllBoundingBoxs());
            if (operand != '/') {
                if (this.c2.getClass().equals(BinOp.class)) {
                    if (((BinOp) this.c2).operand == '/') {
                        list.addAll(this.c2.getAllBoundingBoxs(c1.length() + 1, 0));
                    } else {
                        list.addAll(this.c2.getAllBoundingBoxs(c1.length() + 1, (c1.height() - 1) / 2));
                    }
                } else {
                    list.addAll(this.c2.getAllBoundingBoxs(c1.length() + 1, (c1.height() - 1) / 2));
                }
            } else {
                list.addAll(this.c2.getAllBoundingBoxs(0, c1.height() + 1));
            }
        } else {
            list.addAll(this.c1.getAllBoundingBoxs(0, (c2.height() - 1) / 2));
            if (operand != '/') {
                list.addAll(this.c2.getAllBoundingBoxs(c1.length() + 1, 0));
            } else {
                list.addAll(this.c2.getAllBoundingBoxs(0, c1.height() + 1));
            }
        }


        return list;
    }

    @Override
    public MathList<BoundingBox> getAllBoundingBoxs(int x, int y) {
        MathList<BoundingBox> list = new MathList<BoundingBox>();
        list.add(this.getBoundingBox(x, y));

        if (c1.height() > c2.height()) {
            list.addAll(this.c1.getAllBoundingBoxs(x, y));
            if (operand != '/') {
                if (this.c2.getClass().equals(BinOp.class)) {
                    if (((BinOp) this.c2).operand == '/') {
                        list.addAll(this.c2.getAllBoundingBoxs(x + c1.length() + 1, y));
                    } else {
                        list.addAll(this.c2.getAllBoundingBoxs(x + c1.length() + 1, y +(c1.height() - 1) / 2));
                    }
                } else {
                    list.addAll(this.c2.getAllBoundingBoxs(x + c1.length() + 1, y +(c1.height() - 1) / 2));
                }
            } else {
                list.addAll(this.c2.getAllBoundingBoxs(x, y + (c1.height() + 1) / 2));
            }
        } else {
            list.addAll(this.c1.getAllBoundingBoxs(x, y + (c2.height() - 1) / 2));
            if (operand != '/') {
                list.addAll(this.c2.getAllBoundingBoxs(x + c1.length() + 1, y));
            } else {
                list.addAll(this.c2.getAllBoundingBoxs(x, y + c1.height() + 1));
            }
        }
        return list;
    }
}
