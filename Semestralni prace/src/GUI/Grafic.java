/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Math.Expr;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

/**
 *
 * @author michalblazek
 */
public class Grafic {

    private int velikostZnaku = 11;

    public void drawSource(Expr source, int Scrol, JPanel panel, JScrollBar bar, Graphics g, int offsetX, int offsettY) {
        Grafic a = new Grafic();
        a.drawSource(source.toString(), Scrol, panel, bar, g, offsetX, offsettY);
    }

    public void drawResult(Expr result, int Scrol, JPanel panel, JScrollBar bar, Graphics g) {
        Grafic a = new Grafic();
        a.drawResult(result.toString(), Scrol, panel, bar, g);
    }

    public void drawSource(String source, int Scrol, JPanel panel, JScrollBar bar, Graphics g, int offsetX, int offsettY) {
        int x = 10 + offsetX;
        int y = ((panel.getHeight()) / 2) + offsettY;
        int radek = 8;
        DisplejString d = new DisplejString(source);
        ArrayList<Object> list = d.parceToArrayList();

        if ((panel.getWidth() - 20) < source.length() * velikostZnaku) {
            bar.setVisible(true);
        } else {
            bar.setVisible(false);
        }

        for (int i = 1; i < list.size(); i += 2) {
            System.out.println("source" + source);
            System.out.println(list.get(0).toString());
            if (Integer.valueOf(list.get(i).toString()) == 0) {
                if (i > 1) {
                    if (list.get(i - 3).toString().length() >= list.get(i - 5).toString().length()) {
                        x += list.get(i - 3).toString().length() * velikostZnaku;
                    } else {
                        x += list.get(i - 5).toString().length() * velikostZnaku;
                    }
                }
                g.drawString(list.get(i - 1).toString(), x - (Scrol * source.length()) / 10, y);
            }
            if (Integer.valueOf(list.get(i).toString()) == 1) {
                x += list.get(i - 3).toString().length() * velikostZnaku;
                //Zlomková čára
                g.drawLine(x - (Scrol * source.length()) / 10, y - 6, x + (list.get(i - 1).toString().length() * velikostZnaku) - (Scrol * source.length()), y - 6);
                //Čitatel
                g.drawString(list.get(i - 1).toString(), x - (Scrol * source.length()) / 10, y - 10);
            }
            if (Integer.valueOf(list.get(i).toString()) == 2) {
                //Zlomková čára
                g.drawLine(x - (Scrol * source.length()) / 10, y - 6, x + (list.get(i - 1).toString().length() * velikostZnaku) - (Scrol * source.length()), y - 6);
                //Jmenovatel
                g.drawString(list.get(i - 1).toString(), x - (Scrol * source.length()) / 10, y + 10);
            }

        }
    }

    public void drawResult(String result, int Scrol, JPanel panel, JScrollBar bar, Graphics g) {
        int delkaS = result.length();
        int x = panel.getWidth() - (delkaS * velikostZnaku) - 5;
        int y = panel.getHeight() - 10;
        if (bar.isVisible()) {
            y -= 15;
        }
        g.drawString(result, x, y);
    }
}
