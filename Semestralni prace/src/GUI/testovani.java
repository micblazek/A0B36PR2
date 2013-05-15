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
        list.fillColection("2x");
        System.out.println(list);
        Expr p = list.fromMathList();
        list.removeAll(list);
        list.addAll(p.getAllBoundingBoxs());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getWidth() == 1 && list.get(i).height ==1) {
                System.out.print(list.get(i));
            }
        }
    }
}
