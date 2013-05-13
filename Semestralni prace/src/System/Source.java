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
}
