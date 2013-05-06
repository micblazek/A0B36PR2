/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Math.BinOp;
import Math.Expr;
import System.MathList;
import System.Source;
import System.XCompareMathList;
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
    private int sirkaZnaku = 14;

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
        int startX = 20;

        if (source.isEmpty()) {
            source = "0";
        }
        try {
            MathList<DisplejNumber> vstup = new MathList();
            vstup.fillColection(source);
            Expr p = vstup.fromMathList();
            vstup = p.ohodnot().normalizuj();
            Collections.sort(vstup, new XCompareMathList());                   
            vstup = vstup.cleanWhiteSpaceInDisplejNuber();           
            int nejdelsiBunka = sirkaZnaku * Grafic.nejdelsiBunka(vstup);
            
            //Posunutí pomocí baru
            Expr e = ((new MathList()).fillColection(source).fromMathList());
            if (e.delkaBinOps() * 3 * nejdelsiBunka(vstup) * sirkaZnaku + 20 > panel.getWidth()) {
                startX -= bar.getValue() * 2 * e.delkaBinOps() * nejdelsiBunka * sirkaZnaku / 100;
                bar.setVisible(true);
            } else {
                bar.setVisible(false);
            }

            for (int i = 0; i < vstup.size(); i++) {
                switch (vstup.get(i).getValue().charAt(0)) {
                    case '/':
                        int x1 = (int) (vstup.get(i).getX() * nejdelsiBunka + startX + nejdelsiBunka / 2 - vstup.get(i).getValue().length() * sirkaZnaku / 2 - (((DisplejFraction) vstup.get(i)).getLenght() / 2.0) * nejdelsiBunka) + sirkaZnaku / 2;
                        int x2 = (int) (vstup.get(i).getX() * nejdelsiBunka + startX + nejdelsiBunka / 2 - vstup.get(i).getValue().length() * sirkaZnaku / 2 + (((DisplejFraction) vstup.get(i)).getLenght() / 2.0) * nejdelsiBunka) + sirkaZnaku / 2 - 2;
                        g.drawLine(x1, startY - vstup.get(i).getY() * vyskaZnaku - vyskaZnaku / 2, x2, startY - vstup.get(i).getY() * vyskaZnaku - vyskaZnaku / 2);
                        break;
                    case '^':
                        try {
                            vstup.set(i + 1, new DisplejNumber(vstup.get(i + 1).getValue(), vstup.get(i + 1).getX(), vstup.get(i + 1).getY() - 1));
                        } catch (NumberFormatException nfe) {
                            // když není, nevadí
                        }
                        break;
                    default:
                        g.drawString(vstup.get(i).getValue(), vstup.get(i).getX() * nejdelsiBunka + startX + nejdelsiBunka / 2 - vstup.get(i).getValue().length() * sirkaZnaku / 2, startY - vstup.get(i).getY() * vyskaZnaku);
                }
                // System.out.println(vstup.get(i));
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

    public static int nejdelsiBunka(ArrayList<DisplejNumber> vstup) {
        int nejdelsi = Integer.MIN_VALUE;
        for (int i = 0; i < vstup.size(); i++) {
            if (vstup.get(i).getValue().length() > nejdelsi) {
                nejdelsi = vstup.get(i).getValue().length();
            }
        }
        return nejdelsi;
    }
}