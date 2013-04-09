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
                    if (citatel.length() > 0) {
                        x -= posunSouradnic(citatel, sirkaZnaku);
                        g.clearRect(x, y - vyskaZnaku, citatel.toString().length() * sirkaZnaku, vyskaZnaku + 3);
                        g.drawString(citatel, x - (list.toString().length() * Scrol) / 10, y - (vyskaZnaku / 2));
                    } else {
                        x -= posunSouradnic(list.get(i - 1), sirkaZnaku);
                        g.clearRect(x, y - vyskaZnaku, list.get(i - 1).toString().length() * sirkaZnaku, vyskaZnaku);
                        g.drawString(list.get(i - 1).toString(), x - (list.toString().length() * Scrol) / 10, y - (vyskaZnaku / 2));
                    }
                    jmen = true;
                    y += vyskaZnaku / 2;
                    break;

                case ')':
                    if (jmen == false) {
                        //Pokud je citatel výcemístný a ozávorkovaný, bude vložen do stringu citatel.
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
                        System.out.println(jmenovatel);
                        jmen = true;
                    }

                default:
                    if (jmen) {
                        if (citatel.length() > 0) {
                            if (jmenovatel.length() > 0) {
                                //Čitatel i jmenovatel jsou ozávorkovaní
                                if (citatel.length() > jmenovatel.length()) {
                                    //Citatel delší než jmenovatel... posun menšího na střed 
                                    g.drawString(jmenovatel, x + citatel.length() * sirkaZnaku / 2 - jmenovatel.length() * sirkaZnaku / 2 - (list.toString().length() * Scrol) / 10, y);
                                    x += posunSouradnic(citatel, sirkaZnaku) - (list.toString().length() * Scrol);
                                    y -= vyskaZnaku / 2;
                                    jmen = false;
                                    jmenovatel = "";
                                    break;
                                } else {
                                    //Jmenovatel delší než citatel... posun menšího na střed 
                                    g.clearRect(x, y - vyskaZnaku * 2 + 2, citatel.length() * sirkaZnaku, vyskaZnaku);
                                    g.drawString(citatel, x + jmenovatel.length() * sirkaZnaku / 2 - citatel.length() * sirkaZnaku / 2 - (list.toString().length() * Scrol) / 10, y - vyskaZnaku);
                                    g.drawString(jmenovatel.toString(), x - (list.toString().length() * Scrol) / 10, y);
                                    x += posunSouradnic(jmenovatel, sirkaZnaku) - (list.toString().length() * Scrol);
                                    y -= vyskaZnaku / 2;
                                    jmen = false;
                                    jmenovatel = "";
                                    break;
                                }
                            }

                            if (citatel.length() > list.get(i).toString().length()) {
                                //posune jmenovatel na střed pokud je menší než čitatel (ozávorkovaný)
                                g.drawString(list.get(i).toString(), x + citatel.length() * sirkaZnaku / 2 - list.get(i).toString().length() * sirkaZnaku / 2 - (list.toString().length() * Scrol) / 10, y);
                                x += posunSouradnic(citatel, sirkaZnaku) - (list.toString().length() * Scrol);
                                y -= vyskaZnaku / 2;
                                jmen = false;
                                break;
                            } else {
                                //posune čitatel (ozávorkovaný) na střed pokud je menší než jmenovatel
                                g.clearRect(x, y - vyskaZnaku * 2 + 2, citatel.length() * sirkaZnaku, vyskaZnaku);
                                g.drawString(citatel, x + list.get(i).toString().length() * sirkaZnaku / 2 - citatel.length() * sirkaZnaku / 2 - (list.toString().length() * Scrol) / 10, y - vyskaZnaku);
                                g.drawString(list.get(i).toString(), x - (list.toString().length() * Scrol) / 10, y);
                                x += posunSouradnic(list.get(i), sirkaZnaku) - (list.toString().length() * Scrol);
                                y -= vyskaZnaku / 2;
                                jmen = false;
                                break;
                            }
                        } else {
                            if (jmenovatel.length() > 0) {
                                System.out.println(jm);
                                if (list.get(jm - 2).toString().length() > jmenovatel.length()) {
                                    //Jmenovatel je ozávorkovaný, a karší než čitatel
                                    System.out.println("2 citatel>jmenovatel");
                                    g.drawString(jmenovatel, x + list.get(jm - 2).toString().length() * sirkaZnaku / 2 - jmenovatel.length() * sirkaZnaku / 2 - (list.toString().length() * Scrol) / 10, y);
                                    x += posunSouradnic(list.get(jm - 2).toString(), sirkaZnaku) - (list.toString().length() * Scrol);
                                    y -= vyskaZnaku / 2;
                                    jmen = false;
                                    jmenovatel = "";
                                    break;
                                } else {
                                    System.out.println("2 citatel<jmenovatel");
                                    g.clearRect(x, y - vyskaZnaku * 2 + 2, list.get(jm - 2).toString().length() * sirkaZnaku, vyskaZnaku);
                                    g.drawString(list.get(jm - 2).toString(), x + jmenovatel.length() * sirkaZnaku / 2 - list.get(jm - 2).toString().length() * sirkaZnaku / 2 - (list.toString().length() * Scrol) / 10, y - vyskaZnaku);
                                    g.drawString(jmenovatel.toString(), x - (list.toString().length() * Scrol) / 10, y);
                                    x += posunSouradnic(jmenovatel, sirkaZnaku) - (list.toString().length() * Scrol);
                                    y -= vyskaZnaku / 2;
                                    jmen = false;
                                    jmenovatel = "";
                                    break;
                                }
                            }

                            if (list.get(i - 2).toString().length() < list.get(i).toString().length()) {
                                //posune čitatel na střed pokud je menší než jmenovatel
                                g.clearRect(x, y - vyskaZnaku * 2, list.get(i - 2).toString().length() * sirkaZnaku, vyskaZnaku);
                                g.drawString(list.get(i - 2).toString(), x + list.get(i).toString().length() * sirkaZnaku / 2 - list.get(i - 2).toString().length() * sirkaZnaku / 2 - (list.toString().length() * Scrol) / 10, y - vyskaZnaku);
                                g.drawString(list.get(i).toString(), x - (list.toString().length() * Scrol) / 10, y);
                                x += posunSouradnic(list.get(i), sirkaZnaku) - (list.toString().length() * Scrol);
                                y -= vyskaZnaku / 2;
                                jmen = false;
                                break;
                            } else {
                                //posune jmenovatel na střed pokud je menší než čitatel
                                g.drawString(list.get(i).toString(), x + list.get(i - 2).toString().length() * sirkaZnaku / 2 - list.get(i).toString().length() * sirkaZnaku / 2 - (list.toString().length() * Scrol) / 10, y);
                                x += posunSouradnic(list.get(i - 2), sirkaZnaku) - (list.toString().length() * Scrol);
                                y -= vyskaZnaku / 2;
                                jmen = false;
                                break;
                            }
                        }




                    } else {
                        System.out.println("DEF");
                        g.drawString(list.get(i).toString(), x - (list.toString().length() * Scrol) / 10, y);
                        x += posunSouradnic(list.get(i), sirkaZnaku) - (list.toString().length() * Scrol);
                    }
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
        }
        g.drawString(result, x, y);
    }

    private int posunSouradnic(Object o, int sirka) {
        return o.toString().length() * sirka;
    }
}
