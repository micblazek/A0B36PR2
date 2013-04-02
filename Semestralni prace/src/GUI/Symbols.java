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
public class Symbols extends Grafic {

    public Symbols() {
    }
    private int velikostZnaku = 11;

    @Override
    public void drawSource(Expr source, int Scrol, JPanel panel, JScrollBar bar, Graphics g, int offsetX, int offsettY) {
        Grafic a = new Symbols();
        a.drawSource(source.toString(), Scrol, panel, bar, g, offsetX, offsettY);
    }

    @Override
    public void drawResult(Expr result, int Scrol, JPanel panel, JScrollBar bar, Graphics g) {
        Grafic a = new Symbols();
        a.drawResult(result.toString(), Scrol, panel, bar, g);
    }

    @Override
    public void drawSource(String source, int Scrol, JPanel panel, JScrollBar bar, Graphics g, int offsetX, int offsettY) {
        int x = 10 + offsetX;
        int y = ((panel.getHeight()) / 2) + offsettY;
        int radek = 8;
        System.out.println(source);
       // source = "5+3*//1\t2\t";
        DisplejString d = new DisplejString(source);
        ArrayList<Object> list = d.parceToArrayList();

        if ((panel.getWidth() - 20) < source.length() * velikostZnaku) {
            bar.setVisible(true);
        } else {
            bar.setVisible(false);
        }
        
        for (int i = 1; i < list.size(); i += 2) {
            System.out.println("source"+source);
            System.out.println(list.get(0).toString());
            if (Integer.valueOf(list.get(i).toString()) == 0) {
                g.drawString(list.get(i-1).toString(), x - (Scrol * source.length()) / 10, y);
            }
            if (Integer.valueOf(list.get(i).toString()) == 1) {
                g.drawString(list.get(i-1).toString(), x - (Scrol * source.length()) / 10, y-20);
            }
            if (Integer.valueOf(list.get(i).toString()) == 2) {
                g.drawString(list.get(i-1).toString(), x- (Scrol * source.length()) / 10, y+20);
            }

        }
        

//        int startZlomekX = 0;
//        int konecZlomekX;
//        int yCitatel = 0;
//        int yJmenovatel = 0;
//        boolean jmenovatel = false;
//        boolean citatel = false;
//        boolean a = true;
//        int prvni = 0;
//        for (int i = 0; i < source.length(); i++) {
//            if ((i > 1) && ((source.charAt(i) == '/') && (source.charAt(i - 1) == '/'))) {
//                // Souřadnice zlomku
//                startZlomekX = x + ((i - 1) * velikostZnaku);
//                konecZlomekX = x + (i * velikostZnaku);
//                yCitatel = y - 8;
//                yJmenovatel = y + 8;
//                // Nakreslí zlomkovou čáru               
//                g.drawLine(startZlomekX, y - 7, konecZlomekX, y - 7);
//
//                //Vyřadí z řetězce identifikátor zlomku
//                if (source.length() > i + 1) {
//                    source = source.substring(0, i - 1) + source.substring(i + 1, source.length());
//                    prvni = i;
//                } else {
//                    source = source.substring(0, i - 1);
//                }
//                //Posune i zpět o znaky vyřazené jako identifikátor zlomku
//                i -= 2;
//                citatel = true;
//            }
//            // Pozná konec zlomku
//            if (jmenovatel && (source.charAt(i) == '\t')) {
//                System.out.println("konec");
//                citatel = false;
//                jmenovatel = false;
//            }
//            // Pozná konec čitatele a začátek jmenovatele
//            if (citatel && (source.charAt(i) == '\t')) {
//                source = source.substring(0, i - 1) + source.substring(i + 1, source.length());
//                citatel = false;
//                jmenovatel = true;
//            }
//            //Zápis Citetele
//            if (citatel) {
//                //První znak 
//                
//                g.drawString(source.substring(prvni, i), startZlomekX, yCitatel);
//                if (source.length() > i + 1) {
//                    source = source.substring(0, i - 1) + source.substring(i + 1, source.length());
//                } else {
//                    source = source.substring(0, i - 1);
//                }
//                System.out.println("citatel");
//            }
//            // Záis jmenovatele
//            if (jmenovatel) {
//                String ct = "666";
//                //this.drawCitatel(startZlomekX-x, 0, ct, this, Scrol, panel, bar, g);
//                System.out.println("jmenovatel");
//            }
//            //Výpis dat mimo zlomeke
//            if (i + 1 == source.length()) {
//                g.drawString(source, x - (Scrol * source.length()) / 10, y);
//            }
//        }
//
//        //g.drawString(source, x-(Scrol*source.length())/10, y);
    }

    public void drawCitatel(int ZlomkovaCaraX, int ZlomkovaCaraY, String Citatel, Grafic k, int Scrol, JPanel panel, JScrollBar bar, Graphics g) {
        g.drawLine(ZlomkovaCaraX, ZlomkovaCaraY, ZlomkovaCaraX + (velikostZnaku * Citatel.length()), ZlomkovaCaraY);
        k.drawSource(Citatel, Scrol, panel, bar, g, ZlomkovaCaraX, ZlomkovaCaraY - 40);
    }

    @Override
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
