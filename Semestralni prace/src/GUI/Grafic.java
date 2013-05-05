/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Math.BinOp;
import Math.Expr;
import System.MathList;
import System.Source;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
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
    private int sirkaZnaku = 11;

    public void drawSource(Expr source, int Scrol, JPanel panel, JScrollBar bar, Graphics g) {
        Grafic a = new Grafic();
        a.drawSource(source.toString(), Scrol, panel, bar, g);
    }

    public void drawResult(Expr result, int Scrol, JPanel panel, JScrollBar bar, Graphics g) {
        Grafic a = new Grafic();
        a.drawResult(result.toString(), Scrol, panel, bar, g);
    }

    public void drawSource(String source, int Scrol, JPanel panel, JScrollBar bar, Graphics g) {
        int startX = 20;
        int startY = panel.getHeight() / 4;

        if (source.isEmpty()) {
            source = "0";
        }
        try {
            MathList<DisplejNumber> vstup = ((new MathList()).fillColection(source).fromMathList()).ohodnot().normalizuj();
            int nejdelsiBunka = sirkaZnaku * Grafic.nejdelsiBunka(vstup);

            for (int i = 0; i < vstup.size(); i++) {
                switch (vstup.get(i).getValue().charAt(0)) {
                    case '/':
                        //vstup.set(i, new DisplejFraction(vstup.get(i).getValue(), vstup.get(i).getX(), vstup.get(i).getY(), ((DisplejFraction) vstup.get(i)).vytvorDelku(vstup)));
                        System.out.println("čára");
                        int x1 = (int) (vstup.get(i).getX() * nejdelsiBunka + startX + nejdelsiBunka / 2 - vstup.get(i).getValue().length() * sirkaZnaku / 2 - (((DisplejFraction) vstup.get(i)).getLenght() / 2.0) * nejdelsiBunka);
                        int x2 = (int) (vstup.get(i).getX() * nejdelsiBunka + startX + nejdelsiBunka / 2 - vstup.get(i).getValue().length() * sirkaZnaku / 2 + (((DisplejFraction) vstup.get(i)).getLenght() / 2.0) * nejdelsiBunka);
                        g.drawLine(x1, startY - vstup.get(i).getY() * vyskaZnaku - vyskaZnaku / 2, x2, startY - vstup.get(i).getY() * vyskaZnaku - vyskaZnaku / 2);
                        break;
                    default:
                        g.drawString(vstup.get(i).getValue(), vstup.get(i).getX() * nejdelsiBunka + startX + nejdelsiBunka / 2 - vstup.get(i).getValue().length() * sirkaZnaku / 2, startY - vstup.get(i).getY() * vyskaZnaku);
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            g.drawString("Chyba", startX, startY);
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