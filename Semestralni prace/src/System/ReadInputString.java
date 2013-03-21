/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import java.util.ArrayList;

/**
 *
 * @author michalblazek
 */
public class ReadInputString {
    
   public static ArrayList naplnKolekci(String vstup) {
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
}
