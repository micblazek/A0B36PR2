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
public abstract class Grafic {
    public abstract void drawSource(Expr source, int Scrol, JPanel panel, JScrollBar bar, Graphics g, int offsetX, int offsettY);
    
    public abstract void drawSource(String source, int Scrol, JPanel panel, JScrollBar bar, Graphics g, int offsetX, int offsettY);
    
    public abstract void drawResult(Expr result,int Scrol, JPanel panel,JScrollBar bar, Graphics g);
    
    public abstract void drawResult(String result,int Scrol, JPanel panel,JScrollBar bar, Graphics g);
    
}
