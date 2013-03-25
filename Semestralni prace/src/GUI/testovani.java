/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Math.*;
import System.Source;
import java.util.ArrayList;

/**
 *
 * @author michalblazek
 */
public class testovani {

    public static void main(String[] args) {
        String s = "2+3^2";
        ArrayList<Object> list = Source.fillColection(s);
        Expr e = BinOp.fromArrayList(list);
        System.out.println(e + " = " + e.evaluate());


    }
}
