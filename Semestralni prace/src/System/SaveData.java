/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author michalblazek
 */
public class SaveData {
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
