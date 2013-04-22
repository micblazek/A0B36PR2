/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Math.BinOp;
import Math.Expr;
import System.Source;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

/**
 *
 * @author michalblazek
 */
public class Grafic {
    //final private int fy = 78;

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
        source = "(1/3+3/2)/(9/2+3/2)";
        ArrayList<DisplejNumber> vstup = Grafic.normalizuj(new ArrayList<DisplejNumber>(((BinOp.fromArrayList(new ArrayList(Source.fillColection(source))))).ohodnot()));
        int nejdelsiBunka = sirkaZnaku * Grafic.nejdelsiBunka(vstup);


        for (int i = 0; i < vstup.size(); i++) {
            switch (vstup.get(i).getValue().charAt(0)) {
                case '/':
                    vstup.set(i, new DisplejFraction(vstup.get(i).getValue(), vstup.get(i).getX(), vstup.get(i).getY(), ((DisplejFraction)vstup.get(i)).vytvorDelku(vstup)));
                    System.out.println("čára");
                    int x1 = (int) (vstup.get(i).getX() * nejdelsiBunka + startX + nejdelsiBunka / 2 - vstup.get(i).getValue().length() * sirkaZnaku / 2 - (((DisplejFraction)vstup.get(i)).getLenght()/2.0)*nejdelsiBunka);
                    int x2 = (int) (vstup.get(i).getX() * nejdelsiBunka + startX + nejdelsiBunka / 2 - vstup.get(i).getValue().length() * sirkaZnaku / 2 + (((DisplejFraction)vstup.get(i)).getLenght()/2.0)*nejdelsiBunka);
                    g.drawLine(x1, startY - vstup.get(i).getY() * vyskaZnaku - vyskaZnaku/2,x2, startY - vstup.get(i).getY() * vyskaZnaku - vyskaZnaku/2);
                    break;
                default:
                    g.drawString(vstup.get(i).getValue(), vstup.get(i).getX() * nejdelsiBunka + startX + nejdelsiBunka / 2 - vstup.get(i).getValue().length() * sirkaZnaku / 2, startY - vstup.get(i).getY() * vyskaZnaku);                  
            }
            System.out.println(vstup.get(i).toString());
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

    private int posunSouradnic(Object o, int sirka) {
        return o.toString().length() * sirka;
    }

    public static ArrayList<DisplejNumber> normalizuj(ArrayList<DisplejNumber> vstup) {
        int maxX = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        for (int i = 0; i < vstup.size(); i++) {
            if (vstup.get(i).getX() < maxX) {
                maxX = vstup.get(i).getX();
            }
            if (vstup.get(i).getY() > maxY) {
                maxY = Math.abs(vstup.get(i).getY());
            }
        }
        for (int i = 0; i < vstup.size(); i++) {
            vstup.get(i).setX(vstup.get(i).getX() + Math.abs(maxX));
            vstup.get(i).setY(vstup.get(i).getY() - maxY);
        }
        return vstup;
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