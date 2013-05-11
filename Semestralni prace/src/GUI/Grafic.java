/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Math.BinOp;
import Math.Constant;
import Math.Expr;
import Math.Variable;
import System.MathList;
import System.Source;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

/**
 *
 * @author michalblazek
 */
public class Grafic {

    private int vyskaZnaku = 14;
    private int sirkaZnaku = 12;

    public void drawSource(Expr source, int Scrol, JPanel panel, JScrollBar bar, Graphics g) {
        Grafic a = new Grafic();
        a.drawSource(source.toString(), Scrol, panel, bar, g);
    }

    public void drawResult(Expr result, int Scrol, JPanel panel, JScrollBar bar, Graphics g) {
        Grafic a = new Grafic();
        a.drawResult(result.toString(), Scrol, panel, bar, g);
    }

    public void drawSource(String source, int Scrol, JPanel panel, JScrollBar bar, Graphics g) {
        int startY = panel.getHeight() / 4;
        int startX = 40;

        if (source.isEmpty()) {
            source = "0";
        }
        try {
            MathList<BoundingBox> vstup = new MathList();
            vstup.fillColection(source);
            Expr p = vstup.fromMathList();
            MathList<BoundingBox> boundlist = p.getAllBoundingBoxs();

            for (int i = 0; i < boundlist.size(); i++) {
                //g.drawRect(startX + boundlist.get(i).x * sirkaZnaku, startY + boundlist.get(i).y * vyskaZnaku - vyskaZnaku, boundlist.get(i).width * sirkaZnaku , boundlist.get(i).height * vyskaZnaku );
                if (boundlist.get(i).e.getClass().equals(Constant.class) || boundlist.get(i).e.getClass().equals(Variable.class)) {
                    g.drawString(boundlist.get(i).e.toString(), startX + boundlist.get(i).x * sirkaZnaku, startY + boundlist.get(i).y * vyskaZnaku);
                }
                if (boundlist.get(i).e.getClass().equals(BinOp.class)) {
                    if (((BinOp) boundlist.get(i).e).operand == '/') {
                        g.drawLine(startX + boundlist.get(i).x * sirkaZnaku, startY + boundlist.get(i).y * vyskaZnaku + ((BinOp) boundlist.get(i).e).c1.height() * vyskaZnaku - vyskaZnaku / 2, startX + (boundlist.get(i).width + boundlist.get(i).x) * sirkaZnaku,  startY + boundlist.get(i).y * vyskaZnaku + ((BinOp) boundlist.get(i).e).c1.height() * vyskaZnaku - vyskaZnaku / 2);
                    } else {
                        g.drawString(Character.toString(((BinOp) boundlist.get(i).e).operand), startX + boundlist.get(i).x * sirkaZnaku + ((BinOp) boundlist.get(i).e).c1.length() * sirkaZnaku, startY + (boundlist.get(i).y + boundlist.get(i).height / 2) * vyskaZnaku);
                    }
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            if (source.charAt(0) == '-' && source.length() == 1) {
                g.drawString("-", startX, startY);
            } else {
                g.drawString("Chyba", startX, startY);
            }
        } catch (VstupException ex) {
            g.drawString("Chyba vstupu", startX, startY);
        }
    }

    public void drawResult(String result, int Scrol, JPanel panel, JScrollBar bar, Graphics g) {
        int delkaS = result.length();
        int x = panel.getWidth() - (delkaS * sirkaZnaku) - 5;
        int y = panel.getHeight() - 10;
        if (bar.isVisible()) {
            y -= 15;
        }
        g.drawString(result, x, y);
    }
}