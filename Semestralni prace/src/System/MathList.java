/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import GUI.VstupException;
import Math.*;
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
                if (Character.isDefined(List.get(1).toString().charAt(0))) {
                    List.add(1, new Constant(Double.valueOf(List.get(1).toString()) * -1));
                    List.remove(0);
                    List.remove(1); // odepbere se index 0, to co bylo index 1 je 0, 2 je 1
                } else {
                    List.add(1, new Variable((List.get(1).toString()).charAt(0), -1));
                    List.remove(0);
                    List.remove(1); // odepbere se index 0, to co bylo index 1 je 0, 2 je 1
                }
            }

            if ((List.get(0).toString().charAt(0) == '+') && List.get(0).toString().length() == 1) {
                List.remove(0);
            }
        }
        // Převede čísla v řetězci na konstanty.
        // PROBLÉM
        for (int i = 0; i < List.size(); i++) {
            if (Character.isDigit(List.get(i).toString().charAt(0)) && (List.get(i).getClass().equals(Constant.class) == false || List.get(i).getClass().equals(Variable.class) == false)) {
                List.set(i, new Constant(Double.valueOf(List.get(i).toString())));
            }
            if (Character.isAlphabetic(List.get(i).toString().charAt(0)) && (List.get(i).getClass().equals(Constant.class) == false || List.get(i).getClass().equals(Variable.class) == false)) {
                List.set(i, new Variable(List.get(i).toString().charAt(0), 1));
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
        
        for (int i = 0; i < List.size(); i++) {
            if(List.get(i).getClass().equals(Variable.class) && List.get(i-1).getClass().equals(Constant.class)){
                List.add(i, "*");
            } 
        }      
        // Jestliže je za nebo před závorkou číslo bude bude mezi číslo a závorku vložen znak násobení.
        for (int i = 0; i < List.size() - 1; i++) {
            if (Character.isDigit(List.get(i).toString().charAt(0)) && Character.isDigit(List.get(i + 1).toString().charAt(0))) {
                List.add(i + 1, "*");
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
                } catch (ClassCastException cce) {
                    List.set(i, new BinOp(String.valueOf(List.get(i)).toCharArray()[0], (Expr) List.get(i - 1), new NullSymbol()));
                    List.remove(i + 1);
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
                        if (List.get(i - 1).toString().equals(" ")) {
                            List.set(i, new BinOp(List.get(i).toString().charAt(0), new NullSymbol(), (Expr) List.get(i + 1)));
                            List.remove(i + 1);
                            List.remove(i - 1);
                        } else {
                            List.set(i, new BinOp(List.get(i).toString().charAt(0), (Expr) List.get(i - 1), new NullSymbol()));
                            List.remove(i + 1);
                            List.remove(i - 1);
                        }
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

    public MathList<T> fillColection(String vstup) {
        /**
         * Metoda rozloží vstup String do kolekce ArrayList, rozlišuje čísla a
         * "ostatní znaky".
         */
        boolean cislo = false;
        boolean promenna = false;
        MathList list = new MathList();
        String cast = "";
        /*Cyklus projde celý string, rozlišuje první a další znaky v řetězci.
         */
        for (int i = 0; vstup.length() > i; i++) {
            if (cislo) {
                cast += vstup.charAt(i);
            } else {
                if (promenna) {
                    cast += vstup.charAt(i);
                } else {
                    cast = Character.toString(vstup.charAt(i));
                }
            }
            /* Jestliže je aktuální řetězec číslo, kontroluje následující znak, zdali není také číslo. 
             * Z důvodu vícemístných čísel.
             */
            if ((i + 1 != vstup.length()) && ((Character.isDigit(vstup.charAt(i))) || vstup.charAt(i) == '.') && ((Character.isDigit(vstup.charAt(i + 1))) || vstup.charAt(i + 1) == '.')) {
                cislo = true;
            } else {
                if ((i + 1 != vstup.length()) && (Character.isAlphabetic(vstup.charAt(i))) && (Character.isAlphabetic(vstup.charAt(i + 1))) && ((Character.isDigit(vstup.charAt(i))) || vstup.charAt(i) == '.') == false && ((Character.isDigit(vstup.charAt(i + 1))) || vstup.charAt(i) == '.') == false) {
                    promenna = true;
                } else {
                    promenna = false;
                    list.add(cast);
                    cast = "";
                }
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
        char[] podporovaneZnaky = {'+', '-', '*', '/', '^', '(', ')', '.', ' '};
        boolean vystup = false;
        for (int i = 0; i < this.size(); i++) {
            boolean a = false;
            for (int j = 0; j < podporovaneZnaky.length; j++) {
                if (this.get(i).toString().charAt(0) == podporovaneZnaky[j]) {
                    a = true;
                }
                if (Character.isAlphabetic(this.get(i).toString().charAt(0)) || Character.isDigit(this.get(i).toString().charAt(0))) {
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

    public MathList reductionDuplicates() {
        for (int i = 0; i < this.size(); i++) {
            MathList sublist = new MathList();
            sublist.addAll(this.subList(i + 1, this.size()));
            for (int j = sublist.size() - 1; j >= 0; j--) {
                if (this.get(i).toString().equals(sublist.get(j).toString())) {
                    this.remove(i + j + 1);

                }
            }

        }
        return this;
    }
}
