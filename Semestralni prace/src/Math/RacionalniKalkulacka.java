package Math;

import racionalnikalkulacka.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author michalblazek
 */
public class RacionalniKalkulacka {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String vstup = "";
        
        System.out.print("Chcete nahrát zadání ze souboru (y/n)? ");
        switch (scan.next().toString().charAt(0)){
            case 'y':
                System.out.println("Zadejte prosím plnou adresu suboru: ");
                vstup = nactiSoubor(scan.next());
                break;
            case 'n':
                System.out.println("Zadejte rovnici: ");
                vstup = scan.next();
                break;
            default:
                System.out.println("Špatná odpověď!");
        }

        try {
            if (text(naplnKolekci(vstup))) {
                if (zavorky(naplnKolekci(vstup))) {
                    System.out.print(vstup+" = "+vypocet(naplnKolekci(vstup))+"\n");
                    ulozDoSouboru("vystup.txt", Double.toString(vypocet(naplnKolekci(vstup))));

                } else {
                    System.out.println("Chybná syntaxe, zkontroluj závorky.");
                }
            } else {
                System.out.println("Zadání obsahuje nepodporovaný znak.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Chyba vstupních dat, zkontroluj syntaxi.");
        }
    }

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

    public static boolean zavorky(ArrayList List) {
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

    public static boolean text(ArrayList List) {
        /**
         * Jestliže vstupní kolekce pbsahuje nepodporovaný znak vrátí hodnotu
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

    public static double vypocet(ArrayList List) {
        /**
         * Vyhodnotí vstupní kolekci jako matematický zápis, podporuje operace
         * sčítání, odčítání, násobení, dělení, mocnění a vlastnosti závorek.
         */
        double vysledek = 0;
        int vyssioperace = 0;
        int pocetzavorek = 0;
        int pocetmocneni = 0;
        int indexKZ = 0; // Index konečné závorky
        int indexZZ = Integer.MAX_VALUE; // Index začátku závorky
        //Spočítá počet závorek, uzavřených dvojic.
        for (int i = 0; List.size() > i; i++) {
            if (List.get(i).toString().charAt(0) == '(') {
                pocetzavorek++;
            }
        }

        while (pocetzavorek != 0) {
            //Najde index nejvnitřejší konečné závorky.
            for (int i = 0; indexKZ == 0; i++) {
                if (List.get(i).toString().charAt(0) == ')') {
                    indexKZ = i;
                }
            }
            //Najde index nejvnitřejší začínající závorky.
            for (int i = indexKZ; indexZZ == Integer.MAX_VALUE; i--) {
                if (List.get(i).toString().charAt(0) == '(') {
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
            //Zavolá znovu metodu výpočet, jako vstup bude ArrayList vnitřní závorky.
            List.add(indexZZ, Double.toString(vypocet(ListP)));
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
        // Jestliže je první znak řetězce znak '-', bude první čílso záporné.
        if ((List.get(0).toString().charAt(0) == '-') && List.get(0).toString().length() == 1) {
            List.add(1, (Double.toString(Double.valueOf(List.get(1).toString()) * -1)));
            List.remove(0);
            List.remove(1);
        }
        // Cyklus spočítá vyšší operace jako násobení, dělení a operace mocnění.
        for (int i = 0; List.size() > i; i++) {
            if (List.get(i).toString().charAt(0) == '/' || List.get(i).toString().charAt(0) == '*') {
                vyssioperace++;
            }
            if (List.get(i).toString().charAt(0) == '^') {
                pocetmocneni++;
            }
        }
        // Provede oprace mocnění, z kolekce odstraní příslušné položky a nahradí je výsledkem.
        for (int i = List.size() - 1; pocetmocneni != 0; i--) {
            if (List.get(i).toString().charAt(0) == '^') {
                vysledek = Math.pow(Double.valueOf(String.valueOf(List.get(i - 1))), Double.valueOf(String.valueOf(List.get(i + 1))));
                List.remove(i + 1);
                List.remove(i);
                List.add(i, Double.toString(vysledek));
                List.remove(i - 1);
                pocetmocneni--;
            }
        }
        // Provede oprace násobeni a dělení, z kolekce odstraní příslušné položky a nahradí je výsledkem.
        for (int i = List.size() - 1; vyssioperace != 0; i--) {
            switch (List.get(i).toString().charAt(0)) {
                case '*':
                    vysledek = Double.valueOf(String.valueOf(List.get(i - 1))) * Double.valueOf(String.valueOf(List.get(i + 1)));
                    List.remove(i + 1);
                    List.remove(i);
                    List.add(i, Double.toString(vysledek));
                    List.remove(i - 1);
                    vyssioperace--;
                    break;
                case '/':
                    vysledek = Double.valueOf(String.valueOf(List.get(i - 1))) / Double.valueOf(String.valueOf(List.get(i + 1)));
                    List.remove(i + 1);
                    List.remove(i);
                    List.add(i, Double.toString(vysledek));
                    List.remove(i - 1);
                    vyssioperace--;
                    break;
            }
        }
        // Provede oprace sčítání a odčítání, z kolekce odstraní příslušné položky a nahradí je výsledkem.
        for (int i = List.size() - 1; List.size() != 1; i--) {
            if (List.get(i).toString().length() == 1) {
                switch (List.get(i).toString().charAt(0)) {
                    case '+':
                        vysledek = Double.valueOf(String.valueOf(List.get(i - 1))) + Double.valueOf(String.valueOf(List.get(i + 1)));
                        List.remove(i + 1);
                        List.remove(i);
                        List.add(i, Double.toString(vysledek));
                        List.remove(i - 1);
                        break;
                    case '-':
                        vysledek = Double.valueOf(String.valueOf(List.get(i - 1))) - Double.valueOf(String.valueOf(List.get(i + 1)));
                        List.remove(i + 1);
                        List.remove(i);
                        List.add(i, Double.toString(vysledek));
                        List.remove(i - 1);
                        break;
                }
            }
        }
        return Double.valueOf(List.get(0).toString());
    }

    public static String nactiSoubor(String cesta) {
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

    public static void ulozDoSouboru(String cesta, String retezec) {
        /*Metoda ulozi řetězec "retezec" do souboru s adresou cesta. 
         */
        try {
            FileWriter obsah = new FileWriter(cesta);
            obsah.write(retezec);
            obsah.close();
        } catch (IOException ex) {
        }
    }
}