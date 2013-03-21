/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author michalblazek
 */
public class ReadData {
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
}
