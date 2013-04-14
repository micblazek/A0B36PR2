/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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

    private int vyskaZnaku = 18;
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
        ArrayList<Object> list = new ArrayList<Object>(Source.fillColection(source));
        int x = 10;
        int y = ((panel.getHeight()) / 2);
        String citatel = new String();
        String jmenovatel = new String();
        boolean jmen = false;
        int jm = 0;

        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).toString().charAt(0)) {
                case '/':
                    /**
                     * Pokud byl nalezen ozávorkovaný čitatel bude vykreslen,
                     * pokud nebyl nalezen bude vykresleno poslední číslo.
                     */
                    if (citatel.length() == 0) {
                        citatel = list.get(i - 1).toString();
                    }
                    x -= posunSouradnic(citatel, sirkaZnaku);
                    g.clearRect(x, y - vyskaZnaku, citatel.toString().length() * sirkaZnaku, vyskaZnaku + 3);
                    g.drawString(citatel, x - (list.toString().length() * Scrol) / 10, y - (vyskaZnaku / 2));
                    jmen = true;
                    y += vyskaZnaku / 2;
                    break;

                case ')':
                    if (jmen == false) {
                        /**
                         * Pokud je citatel výcemístný a ozávorkovaný, bude
                         * vložen do stringu ¨citatel".
                         *
                         * @Příkla (1+2)/xxx
                         */
                        g.drawString(list.get(i).toString(), x - (list.toString().length() * Scrol) / 10, y);
                        x += posunSouradnic(list.get(i), sirkaZnaku);
                        int j = i;
                        while (list.get(j).toString().charAt(0) != '(') {
                            citatel = list.get(j).toString() + citatel.toString();
                            j--;
                        }
                        citatel = '(' + citatel.toString();
                        break;
                    }
                case '(':
                    /**
                     * Pokud je jmenovatel výcemístný a ozávorkovaný, bude
                     * vložen do stringu ¨jmenovatel".
                     *
                     * @Příkla xxx/(1+2)
                     */
                    if (jmen == true && list.size() > i) {
                        jm = i;
                        while (i < list.size()) {
                            jmenovatel += list.get(i).toString();
                            if (list.get(i).toString().charAt(0) == ')') {
                                i++;
                                break;
                            }
                            i++;
                        }
                        i--;
                        jmen = true;
                    }
                default:
                    /**
                     * Vykreslování jmenovatele, v případě že je delší než
                     * čitatel bude čitatel posunut na střed zlomku podle
                     * jmenovatele, pokud je čitatel delší jmenovatel, bude
                     * jmenovatel zapsán na střed zlomku podle čitatele.
                     */
                    if (jmenovatel.length() == 0) {
                        jmenovatel = list.get(i).toString();
                    }

                    if (jmen) {
                        if (citatel.length() > jmenovatel.length()) {
                            //Citatel delší než jmenovatel... posun menšího na střed 
                            g.drawString(jmenovatel, x + citatel.length() * sirkaZnaku / 2 - jmenovatel.length() * sirkaZnaku / 2 - (list.toString().length() * Scrol) / 10, y);
                            x += posunSouradnic(citatel, sirkaZnaku) - (list.toString().length() * Scrol);
                            y -= vyskaZnaku / 2;
                            jmen = false;
                            break;
                        } else {
                            //Jmenovatel delší než citatel... posun menšího na střed 
                            g.clearRect(x, y - vyskaZnaku * 2 + 2, citatel.length() * sirkaZnaku, vyskaZnaku);
                            g.drawString(citatel, x + jmenovatel.length() * sirkaZnaku / 2 - citatel.length() * sirkaZnaku / 2 - (list.toString().length() * Scrol) / 10, y - vyskaZnaku);
                            g.drawString(jmenovatel, x - (list.toString().length() * Scrol) / 10, y);
                            x += posunSouradnic(jmenovatel, sirkaZnaku) - (list.toString().length() * Scrol);
                            y -= vyskaZnaku / 2;
                            jmen = false;
                            break;
                        }
                    } else {
                        g.drawString(list.get(i).toString(), x - (list.toString().length() * Scrol) / 10, y);
                        x += posunSouradnic(list.get(i), sirkaZnaku) - (list.toString().length() * Scrol);

                    }
                    jmenovatel = "";
                    citatel = "";
            }
            if ((panel.getWidth() - 20) < x + list.get(i).toString().length() * sirkaZnaku) {
                bar.setVisible(true);
            } else {
                bar.setVisible(false);
            }
        }
    }

    public void drawResult(String result, int Scrol, JPanel panel, JScrollBar bar, Graphics g) {
        int delkaS = result.length();
        int x = panel.getWidth() - (delkaS * sirkaZnaku) - 5;
        int y = panel.getHeight() - 10;
        if (bar.isVisible()) {
            y -= 15;

            System.out.println("");
        }
        g.drawString(result, x, y);
    }

    private int posunSouradnic(Object o, int sirka) {
        return o.toString().length() * sirka;
    }
}
