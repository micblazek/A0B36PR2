package GUI;

import Math.Variable;
import System.MathList;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.plaf.FontUIResource;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author michalblazek
 */
public class VariableFrame extends JFrame {
    public JTable table = new JTable(29, 2);

    public VariableFrame(int x, int y, int width, int hight) {
        this.setName("Variable");
        this.setBounds(x, y, width, hight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        JTable popis = new JTable(1, 2);
        popis.setValueAt("Variable", 0, 0);
        popis.setValueAt("Value", 0, 1);
        popis.setBounds(0, 0, width, 20);
        popis.enable(false);
        this.add(popis);

        table.setBounds(0, 20, width, 470);      
        this.add(popis);
        this.add(table);     
        this.setVisible(true);
        this.pack();
    }
    
    public MathList<Variable> VariableList(){
        MathList<Variable> variable = new MathList<Variable>();
        for (int i = 0; i < table.getRowCount(); i++) {
                if ((table.getValueAt(i, 0) == null) == false) {
                    variable.add(new Variable(table.getValueAt(i, 0).toString().charAt(0), Double.valueOf(table.getValueAt(i, 1).toString())));
                } else {
                    break;
                }
            }
        return variable;
    }
    
    public void setTable(MathList<Variable> list){
        for (int i = 0; i < list.size(); i++) {
            table.setValueAt(list.get(i).name, i, 0);
            table.setValueAt(list.get(i).value, i, 1);
        }
    }
}
