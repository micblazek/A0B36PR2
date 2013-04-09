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
        boolean cit = false;
        String jmenovatel = new String();
        boolean jmen = false;

        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).toString().charAt(0)) {
                case '/': {
                    if (citatel.length() > 0) {
                        if ((citatel.charAt(0) == '(') && (jmen == false)) {
                            System.out.print("1");
                            x -= citatel.toString().length() * sirkaZnaku;
                            g.clearRect(x, y - vyskaZnaku, citatel.toString().length() * sirkaZnaku, vyskaZnaku + 3);
                            g.drawString(citatel, x-(list.toString().length()*Scrol)/10, y - (vyskaZnaku / 2));
                        } else {
                            System.out.print("2");
                            x -= list.get(i - 1).toString().length() * sirkaZnaku;
                            g.clearRect(x, y - vyskaZnaku, list.get(i - 1).toString().length() * sirkaZnaku, vyskaZnaku);
                            g.drawString(list.get(i - 1).toString(), x-(list.toString().length()*Scrol)/10, y - (vyskaZnaku / 2));
                        }
                    } else {
                        System.out.print("3");
                        x -= list.get(i - 1).toString().length() * sirkaZnaku;
                        g.clearRect(x, y - vyskaZnaku, list.get(i - 1).toString().length() * sirkaZnaku, vyskaZnaku);
                        g.drawString(list.get(i - 1).toString(), x-(list.toString().length()*Scrol)/10, y - (vyskaZnaku / 2));
                    }
                    System.out.print("4");
                    jmen = true;
                    y += vyskaZnaku / 2;
                    break;
                }
                case ')': {
                    g.drawString(list.get(i).toString(), x-(list.toString().length()*Scrol)/10, y);
                    x += list.get(i).toString().length() * sirkaZnaku;
                    int j = i;
                    while (list.get(j).toString().charAt(0) != '(') {
                        citatel = list.get(j).toString() + citatel.toString();
                        j--;
                    }
                    citatel = '(' + citatel.toString();
                    break;
                }
                default: {
                    if (jmen) {
                        System.out.print("6");
                        if (citatel.length() > 0 && citatel.length() > list.get(i).toString().length()) {
                            System.out.println(list.get(i - 2).toString());
                            g.drawString(list.get(i).toString(), x + citatel.length() * sirkaZnaku / 2 - list.get(i).toString().length() * sirkaZnaku / 2-(list.toString().length()*Scrol)/10, y);
                            System.out.println("x");
                        } else {
                            if (citatel.length() <= list.get(i).toString().length() && citatel.length() != 0) {
                                g.clearRect(x, y - vyskaZnaku * 2, citatel.length() * sirkaZnaku, vyskaZnaku + 3);
                                g.drawString(citatel, x + list.get(i).toString().length() * sirkaZnaku / 2 - citatel.length() * sirkaZnaku / 2-(list.toString().length()*Scrol)/10, y - vyskaZnaku);
                                g.drawString(list.get(i).toString(), x-(list.toString().length()*Scrol)/10, y);
                                System.out.println("z");
                            } else {
                                if (list.get(i - 2).toString().length() < list.get(i).toString().length()) {
                                    g.clearRect(x, y - vyskaZnaku * 2, list.get(i - 2).toString().length() * sirkaZnaku, vyskaZnaku);
                                    g.drawString(list.get(i - 2).toString(), x + list.get(i).toString().length() * sirkaZnaku / 2 - list.get(i - 2).toString().length() * sirkaZnaku / 2-(list.toString().length()*Scrol)/10, y - vyskaZnaku);
                                    g.drawString(list.get(i).toString(), x-(list.toString().length()*Scrol)/10, y);
                                    System.out.println("d");
                                } else {
                                    g.drawString(list.get(i).toString(), x + list.get(i - 2).toString().length() * sirkaZnaku / 2 - list.get(i).toString().length() * sirkaZnaku / 2 -(list.toString().length()*Scrol)/10, y);
                                    System.out.println("y");
                                }
                            }
                        }
                        y -= vyskaZnaku / 2;
                        x += list.get(i).toString().length() * sirkaZnaku;
                        jmen = false;
                        citatel = "";
                    } else {
                        System.out.print("7");
                        g.drawString(list.get(i).toString(), x-(list.toString().length()*Scrol)/10, y);
                        x += list.get(i).toString().length() * sirkaZnaku;
                    }
                }
            }
            if ((panel.getWidth() - 20) < x+list.get(i).toString().length()*sirkaZnaku) {
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
}
