/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import System.Source;
import java.util.ArrayList;
import Math.*;
import System.MathList;
import java.util.Collections;

/**
 *
 * @author michalblazek
 */
public class testovani {

    public static void main(String[] args) throws IndexOutOfBoundsException, VstupException {      
        MathList<BoundingBox> list = new MathList();
        list.fillColection("3+1/2");
        System.out.println(list);
        Expr p = list.fromMathList();
        System.out.println(p.getAllBoundingBoxs());



    }
}
