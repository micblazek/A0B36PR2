/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Math.Expr;
import java.awt.Graphics;
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
    public void drawSource(Expr source,int Scrol, JPanel panel,JScrollBar bar, Graphics g) {
        Grafic a = new Symbols();
        a.drawSource(source.toString(),Scrol, panel,bar, g);
    }

    @Override
    public void drawResult(Expr result,int Scrol, JPanel panel,JScrollBar bar, Graphics g) {
        Grafic a = new Symbols();
        a.drawResult(result.toString(),Scrol, panel,bar, g);
    }

    @Override
    public void drawSource(String source,int Scrol, JPanel panel,JScrollBar bar, Graphics g) {
        int x = 10;
        int y = 20;        
        
        if((panel.getWidth()-20)<source.length()*velikostZnaku){
            bar.setVisible(true);
        }else{
            bar.setVisible(false);
        }
        g.drawString(source, x-(Scrol*source.length())/10, y);
    }

    @Override
    public void drawResult(String result,int Scrol, JPanel panel,JScrollBar bar, Graphics g) {
        int delkaS = result.length();        
        int x = panel.getWidth() - (delkaS*velikostZnaku)-5;
        int y = panel.getHeight() - 10;
               if(bar.isVisible()){
                   y-=15;
               }
        g.drawString(result, x, y);
    }
}
