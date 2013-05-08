/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import GUI.DisplejFraction;
import GUI.DisplejNumber;
import GUI.VstupException;
import Math.BinOp;
import Math.Bracers;
import Math.Constant;
import Math.Expr;
import Math.NullSymbol;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author michalblazek
 */
public class MathList<T> extends ArrayList<T> {

    ArrayList<T> list;

    public MathList() {
    }

    public MathList(ArrayList<T> list, Collection<? extends T> c) {
        super(c);
        this.list = list;
    }

    public MathList(ArrayList<T> list, int initialCapacity) {
        super(initialCapacity);
        this.list = list;
    }

    public MathList(ArrayList<T> list) {
        this.list = list;
    }

    public MathList(int initialCapacity) {
        super(initialCapacity);
    }

    public Expr fromMathList() throws IndexOutOfBoundsException, VstupException {
        if (this.textControl() == false && this.size() > 0) {
            throw new VstupException(1);
        }
        MathList<Object> List = new MathList();
        List.addAll(this);
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
        if (List.size() > 0) {
            // Jestliže je první znak řetězce znak '-', bude první čílso záporné.
            if ((List.get(0).toString().charAt(0) == '-') && List.get(0).toString().length() == 1) {
                List.add(1, new Constant(Double.valueOf(List.get(1).toString()) * -1));
                List.remove(0);
                List.remove(1); // odepbere se index 0, to co bylo index 1 je 0, 2 je 1
            }

            if ((List.get(0).toString().charAt(0) == '+') && List.get(0).toString().length() == 1) {
                List.remove(0);
            }
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
            MathList ListP = new MathList();
            for (int j = indexZZ + 1; j != indexKZ; j++) {
                ListP.add(List.get(j));
            }
            for (int j = indexKZ; j != indexZZ - 1; j--) {
                List.remove(j);
            }
            //Vytvoří BinOp z vnitřku závorek
            try {
                List.add(indexZZ, ListP.fromMathList());
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
        int mocnina = 0;
        for (int i = 0; i < List.size(); i++) {
            if (List.get(i).getClass() == "".getClass() && List.get(i).toString().charAt(0) == '^') {
                mocnina++;
            }
        }
        for (int i = 1; mocnina != 0; i += 3) {
            if (List.get(i).getClass().equals(String.class) && (List.get(i).toString().charAt(0) == '^')) {
                try {
                    List.set(i, new BinOp(List.get(i).toString().charAt(0), (Expr) List.get(i - 1), (Expr) List.get(i + 1)));
                    List.remove(i + 1);
                    List.remove(i - 1);
                    i--;
                    mocnina--;
                } catch (IndexOutOfBoundsException e) {
                    // pro psaní hodnot real-time                
                    List.set(i, new BinOp(String.valueOf(List.get(i)).toCharArray()[0], (Expr) List.get(i - 1), new NullSymbol()));
                    List.remove(i - 1);
                    i--;
                    mocnina--;
                }
            } else {
                i--;
            }
            if (i + 3 >= List.size()) {
                i = -2;
            }
        }
        int nasobeniDeleni = 0;
        for (int i = 0; i < List.size(); i++) {
            if (List.get(i).getClass() == "".getClass() && (List.get(i).toString().charAt(0) == '*' || List.get(i).toString().charAt(0) == '/')) {
                nasobeniDeleni++;
            }
        }

        for (int i = 1; nasobeniDeleni != 0; i += 3) {
            if (List.get(i).getClass().equals(String.class) && (List.get(i).toString().charAt(0) == '*' || List.get(i).toString().charAt(0) == '/')) {
                try {
                    List.set(i, new BinOp(List.get(i).toString().charAt(0), (Expr) List.get(i - 1), (Expr) List.get(i + 1)));
                    List.remove(i + 1);
                    List.remove(i - 1);
                    i--;
                    nasobeniDeleni--;
                } catch (IndexOutOfBoundsException e) {
                    // pro psaní hodnot real-time                
                    List.set(i, new BinOp(String.valueOf(List.get(i)).toCharArray()[0], (Expr) List.get(i - 1), new NullSymbol()));
                    List.remove(i - 1);
                    i--;
                    nasobeniDeleni--;
                } catch (ClassCastException cce) {
                    List.set(i, new BinOp(String.valueOf(List.get(i)).toCharArray()[0], (Expr) List.get(i - 1), new NullSymbol()));
                    List.remove(i + 1);
                    List.remove(i - 1);
                    i--;
                    nasobeniDeleni--;
                }
            } else {
                i--;
            }
            if (i + 3 >= List.size()) {
                i = -2;
            }
        }

        for (int i = 1; List.size() != 1; i += 3) {
            if (List.get(i).getClass().equals(String.class)) {
                try {
                    List.set(i, new BinOp(List.get(i).toString().charAt(0), (Expr) List.get(i - 1), (Expr) List.get(i + 1)));
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
                        i = 0;
                    }
                } catch (ClassCastException cce) {
                    if (this.get(i).toString().charAt(0) == '(') {
                        List.set(i, new NullSymbol());
                        List.remove(i - 1);
                    } else {
                        List.set(i, new BinOp(List.get(i).toString().charAt(0), (Expr) List.get(i - 1), new NullSymbol()));
                        List.remove(i + 1);
                        List.remove(i - 1);
                    }
                }
            } else {
                if (List.get(i).getClass().equals(Bracers.class)) {
                    List.remove(i);
                }
            }
            if (i + 3 >= List.size()) {
                i = -2;
            }
        }
        if (List.size() == 1 && List.get(0) == "(") {
            List.set(0, new Constant(0));
        }
        if (List.size() > 0) {
            return new Bracers('(', (Expr) List.get(0), ')');
        } else {
            return new Bracers('(', new Constant(0), ')');
        }
    }

    public MathList<DisplejNumber> normalizuj() {
        MathList<DisplejNumber> vstup = new MathList();

        for (int i = 0; i < this.size() && (this.get(i).getClass().equals(DisplejNumber.class) || this.get(i).getClass().equals(DisplejFraction.class)); i++) {
            vstup.add((DisplejNumber) this.get(i));
        }

        if (vstup.size() > 0) {
            int maxX = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
            for (int i = 0; i < vstup.size(); i++) {
                if (vstup.get(i).getX() < maxX) {
                    maxX = vstup.get(i).getX();
                }
                if (vstup.get(i).getY() > maxY) {
                    maxY = Math.abs(vstup.get(i).getY());
                }
            }
            for (int i = 0; i < vstup.size(); i++) {
                vstup.get(i).setX(vstup.get(i).getX() + Math.abs(maxX));
                vstup.get(i).setY(vstup.get(i).getY() - maxY);
            }
        }
        return vstup;
    }

    public MathList<T> fillColection(String vstup) {
        /**
         * Metoda rozloží vstup String do kolekce ArrayList, rozlišuje čísla a
         * "ostatní znaky".
         */
        boolean cislo = false;
        MathList list = new MathList();
        String cast = null;
        /*Cyklus projde celý string, rozlišuje první a další znaky v řetězci.
         */
        for (int i = 0; vstup.length() > i; i++) {
            if (cislo) {
                cast += vstup.charAt(i);
            } else {
                cast = Character.toString(vstup.charAt(i));
            }
            /* Jestliže je aktuální řetězec číslo, kontroluje následující znak, zdali není také číslo. 
             * Z důvodu vícemístných čísel.
             */
            if ((i + 1 != vstup.length()) && ((Character.isDigit(vstup.charAt(i))) || vstup.charAt(i) == '.') && ((Character.isDigit(vstup.charAt(i + 1))) || vstup.charAt(i + 1) == '.')) {
                cislo = true;
            } else {
                cislo = false;
                list.add(cast);
                cast = null;
            }
        }
        this.addAll(list);
        return this;
    }

    public boolean bracers() {
        /**
         * Jestliže kolekce obsahuje neuzavřené závorky vrátí hodnotu FALSE.
         */
        int zavorkaL = 0;
        int zavorkaP = 0;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).toString().charAt(0) == '(') {
                zavorkaL++;
            }
            if (this.get(i).toString().charAt(0) == ')') {
                zavorkaP++;
            }
        }
        if (zavorkaL == zavorkaP) {
            return true;
        } else {
            return false;
        }
    }

    public boolean textControl() {
        /**
         * Jestliže vstupní kolekce obsahuje nepodporovaný znak vrátí hodnotu
         * FALSE.
         */
        char[] podporovaneZnaky = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '+', '-', '*', '/', '^', '(', ')', '.', ' '};
        boolean vystup = false;
        for (int i = 0; i < this.size(); i++) {
            boolean a = false;
            for (int j = 0; j < podporovaneZnaky.length; j++) {
                if (this.get(i).toString().charAt(0) == podporovaneZnaky[j]) {
                    a = true;
                }
            }
            if (a) {
                vystup = true;
                a = false;
            } else {
                return false;
            }
        }
        return vystup;
    }

    public MathList<DisplejNumber> cleanWhiteSpaceInDisplejNuber() {
        MathList<DisplejNumber> list = new MathList<DisplejNumber>();
        list.add(((DisplejNumber) this.get(0)));
        //Cyklus probíhá dokud i > size, a prvek je DisplejNuber nebo Fraction
        for (int i = 1; i < this.size() && this.size() > 2 && (this.get(i - 1).getClass().equals(DisplejNumber.class) || this.get(i - 1).getClass().equals(DisplejFraction.class)) && (this.get(i).getClass().equals(DisplejNumber.class) || this.get(i).getClass().equals(DisplejFraction.class)); i++) {
            //Prvek je DisplejNumber
            if (this.get(i).getClass().equals(DisplejNumber.class)) {
                //Prvek není o jednu větší nebo stejný
                if (((list.get(i - 1)).getX() + 1 != ((DisplejNumber) this.get(i)).getX()) && (((DisplejNumber) this.get(i - 1)).getX() != ((DisplejNumber) this.get(i)).getX())) {
                    list.add(new DisplejNumber(((DisplejNumber) this.get(i)).getValue(), ((DisplejNumber) this.get(i)).getX() - (((DisplejNumber) this.get(i)).getX() - list.get(i - 1).getX() - 1), ((DisplejNumber) this.get(i)).getY()));
                } else {
                    if ((((DisplejNumber) this.get(i - 1)).getX()) == ((DisplejNumber) this.get(i)).getX()) {
                        list.add(new DisplejNumber(((DisplejNumber) this.get(i)).getValue(), list.get(i - 1).getX(), ((DisplejNumber) this.get(i)).getY()));
                    } else {
                        list.add(new DisplejNumber(((DisplejNumber) this.get(i)).getValue(), ((DisplejNumber) this.get(i)).getX(), ((DisplejNumber) this.get(i)).getY()));
                    }
                }

            }
            //Prvek je DisplejFraction (zlomek)
            if (this.get(i).getClass().equals(DisplejFraction.class)) {
                //Prvek je o jednu větší než předchozí nebo stejný
                if (((list.get(i - 1)).getX() + 1 != ((DisplejFraction) this.get(i)).getX()) && (((DisplejNumber) this.get(i - 1)).getX() != ((DisplejFraction) this.get(i)).getX())) {
                    list.add(new DisplejFraction(((DisplejFraction) this.get(i)).getValue(), ((DisplejFraction) this.get(i)).getX() - (((DisplejFraction) this.get(i)).getX() - list.get(i - 1).getX() - 1), ((DisplejFraction) this.get(i)).getY(), ((DisplejFraction) this.get(i)).getLenght()));
                } else {
                    list.add(new DisplejFraction(((DisplejFraction) this.get(i)).getValue(), ((DisplejFraction) this.get(i)).getX(), ((DisplejFraction) this.get(i)).getY(), ((DisplejFraction) this.get(i)).getLenght()));
                }
            }
        }
        return list;
    }

    public MathList<DisplejNumber> doSpacing() {
        MathList<DisplejNumber> list = new MathList<DisplejNumber>();
        list.add(((DisplejNumber) this.get(0)));
        for (int i = 1; i < this.size(); i++) {
            if (((DisplejNumber) this.get(i - 1)).getY() == ((DisplejNumber) this.get(i)).getY() && ((DisplejNumber) this.get(i - 1)).getX() + 1 == ((DisplejNumber) this.get(i)).getX()) {
                if (this.get(i).getClass().equals(DisplejNumber.class)) {
                    if (this.get(i - 1).getClass().equals(DisplejNumber.class)) {
                        list.add(new DisplejNumber(((DisplejNumber) this.get(i)).getValue(), list.get(i - 1).getX() + list.get(i - 1).getValue().toString().length(), ((DisplejNumber) this.get(i)).getY()));
                    } else {
                        list.add(new DisplejNumber(((DisplejNumber) this.get(i)).getValue(), list.get(i - 1).getX() + ((DisplejFraction) list.get(i - 1)).getLenght(), ((DisplejNumber) this.get(i)).getY()));
                    }
                } else {
                    list.add(new DisplejFraction(((DisplejNumber) this.get(i)).getValue(), ((DisplejNumber) this.get(i-1)).getX() + ((DisplejNumber) this.get(i - 1)).getValue().toString().length(), ((DisplejNumber) this.get(i)).getY(), ((DisplejFraction) this.get(i)).getLenght()));
                }
            } else {
                list.add(((DisplejNumber) this.get(i)));
            }
        }
        return list;
    }
}
