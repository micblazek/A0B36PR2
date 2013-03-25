/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author michalblazek
 */
public class Source {

    public static String loadFile(String cesta) {
        /*Metoda vrací obsah souboru z adresy cesta 
         */
        try {
            String obsah = "";
            BufferedReader r = new BufferedReader(new FileReader(cesta));
            while ((obsah = r.readLine()) != null) {
                return obsah;
            }
            r.close();
        } catch (FileNotFoundException bx) {
        } catch (IOException ax) {
        }
        return null;
    }

    public static void saveAsFile(String cesta, String retezec) {
        /*Metoda ulozi řetězec "retezec" do souboru s adresou cesta. 
         */
        try {
            FileWriter obsah = new FileWriter(cesta);
            obsah.write(retezec);
            obsah.close();
        } catch (IOException ex) {
        }
    }

    /**
     *
     * @param vstup
     * @return
     */
    public static ArrayList fillColection(String vstup) {
        /**
         * Metoda rozloží vstup String do kolekce ArrayList, rozlišuje čísla a
         * "ostatní znaky".
         */
        boolean cislo = false;
        ArrayList list = new ArrayList();
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
        return list;
    }

    public static boolean bracers(ArrayList List) {
        /**
         * Jestliže kolekce obsahuje neuzavřené závorky vrátí hodnotu FALSE.
         */
        int zavorkaL = 0;
        int zavorkaP = 0;
        for (int i = 0; i < List.size(); i++) {
            if (List.get(i).toString().charAt(0) == '(') {
                zavorkaL++;
            }
            if (List.get(i).toString().charAt(0) == ')') {
                zavorkaP++;
            }
        }
        if (zavorkaL == zavorkaP) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param List
     * @return
     */
    public static boolean textControl(ArrayList List) {
        /**
         * Jestliže vstupní kolekce obsahuje nepodporovaný znak vrátí hodnotu
         * FALSE.
         */
        char[] podporovaneZnaky = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '+', '-', '*', '/', '^', '(', ')', '.'};
        boolean vystup = false;
        for (int i = 0; i < List.size(); i++) {
            boolean a = false;
            for (int j = 0; j < podporovaneZnaky.length; j++) {
                if (List.get(i).toString().charAt(0) == podporovaneZnaky[j]) {
                    a = true;
                }
            }
            if (a) {
                vystup = true;
                a = false;
            } else {
                vystup = false;
            }
        }
        return vystup;
    }
}
