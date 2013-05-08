/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import GUI.DisplejNumber;
import java.util.Comparator;

/**
 *
 * @author michalblazek
 */
public class YCompareMathList implements Comparator<DisplejNumber>{

    @Override
    public int compare(DisplejNumber o1, DisplejNumber o2) {
        Integer a = o1.getY();
        Integer b = o2.getY();
        return a.compareTo(b);
    }


    
}
