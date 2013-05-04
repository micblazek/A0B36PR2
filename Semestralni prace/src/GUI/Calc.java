/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Math.*;
import System.MathList;
import System.Source;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author michalblazek
 */
public class Calc extends javax.swing.JFrame {

    public String vstup = new String();
    //public String zadani = new String();
    public String vysledek = new String();
    public int poziceScrolu = 0;

    /**
     * Creates new form main
     */
    public Calc() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn0 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btnClean = new javax.swing.JButton();
        btnRovnase = new javax.swing.JButton();
        btnSmazat = new javax.swing.JButton();
        btnDesetinaTecka = new javax.swing.JButton();
        Operace = new javax.swing.JPanel();
        btnDeleni = new javax.swing.JButton();
        btnPlus = new javax.swing.JButton();
        btnNasobeni = new javax.swing.JButton();
        btnMocnina = new javax.swing.JButton();
        btnZavorkaL = new javax.swing.JButton();
        btnZavorkaP = new javax.swing.JButton();
        btnOdmocnina = new javax.swing.JButton();
        btnMinus = new javax.swing.JButton();
        Displej = new javax.swing.JPanel(){
            public void paint(Graphics g){
                super.paint(g);
                kresli(g);
            }
        };
        DisplejScrollBar = new javax.swing.JScrollBar();
        txtVstup = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        Menu = new javax.swing.JMenu();
        mNacist = new javax.swing.JMenuItem();
        mUlozit = new javax.swing.JMenuItem();
        mKonec = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Racionální kalkulačka");
        setAlwaysOnTop(true);
        setLocationByPlatform(true);

        btn0.setText("0");
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btn1.setText("1");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btn2.setText("2");
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btn3.setText("3");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btn4.setText("4");
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btn5.setText("5");
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btn6.setText("6");
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btn7.setText("7");
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btn8.setText("8");
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btn9.setText("9");
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btnClean.setText("CA");
        btnClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanActionPerformed(evt);
            }
        });

        btnRovnase.setText("=");
        btnRovnase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRovnaseActionPerformed(evt);
            }
        });

        btnSmazat.setText("C");
        btnSmazat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSmazatActionPerformed(evt);
            }
        });

        btnDesetinaTecka.setText(".");
        btnDesetinaTecka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        Operace.setBackground(new java.awt.Color(204, 204, 204));
        Operace.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnDeleni.setText("/");
        btnDeleni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btnPlus.setText("+");
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btnNasobeni.setText("*");
        btnNasobeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btnMocnina.setText("Mocnina");
        btnMocnina.setActionCommand("^");
        btnMocnina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btnZavorkaL.setText("(");
        btnZavorkaL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btnZavorkaP.setText(")");
        btnZavorkaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btnOdmocnina.setText("Odmocnina");
        btnOdmocnina.setActionCommand("^(1/");
        btnOdmocnina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        btnMinus.setText("-");
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumAction(evt);
            }
        });

        org.jdesktop.layout.GroupLayout OperaceLayout = new org.jdesktop.layout.GroupLayout(Operace);
        Operace.setLayout(OperaceLayout);
        OperaceLayout.setHorizontalGroup(
            OperaceLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(OperaceLayout.createSequentialGroup()
                .addContainerGap()
                .add(OperaceLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(OperaceLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(OperaceLayout.createSequentialGroup()
                            .add(btnNasobeni, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(btnDeleni, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(OperaceLayout.createSequentialGroup()
                            .add(btnPlus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(btnMinus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(OperaceLayout.createSequentialGroup()
                            .add(btnZavorkaL, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(btnZavorkaP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(btnMocnina, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 106, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(btnOdmocnina, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 106, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        OperaceLayout.setVerticalGroup(
            OperaceLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(OperaceLayout.createSequentialGroup()
                .add(OperaceLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnPlus)
                    .add(btnMinus))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(OperaceLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnNasobeni)
                    .add(btnDeleni))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(OperaceLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnZavorkaL)
                    .add(btnZavorkaP))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnMocnina)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(btnOdmocnina)
                .addContainerGap())
        );

        Displej.setBackground(new java.awt.Color(255, 255, 255));
        Displej.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Displej.setFocusCycleRoot(true);
        Displej.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        DisplejScrollBar.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
        DisplejScrollBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                DisplejScrollBarMouseReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout DisplejLayout = new org.jdesktop.layout.GroupLayout(Displej);
        Displej.setLayout(DisplejLayout);
        DisplejLayout.setHorizontalGroup(
            DisplejLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(DisplejScrollBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DisplejLayout.setVerticalGroup(
            DisplejLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, DisplejLayout.createSequentialGroup()
                .add(0, 140, Short.MAX_VALUE)
                .add(DisplejScrollBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        DisplejScrollBar.getAccessibleContext().setAccessibleDescription("");

        txtVstup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVstupActionPerformed(evt);
            }
        });
        txtVstup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVstupKeyReleased(evt);
            }
        });

        Menu.setText("Soubor");

        mNacist.setText("Načíst data...");
        mNacist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mNacistActionPerformed(evt);
            }
        });
        Menu.add(mNacist);

        mUlozit.setText("Uložit jako...");
        mUlozit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mUlozitActionPerformed(evt);
            }
        });
        Menu.add(mUlozit);

        mKonec.setText("Konec");
        mKonec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mKonecActionPerformed(evt);
            }
        });
        Menu.add(mKonec);

        jMenuBar1.add(Menu);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(1, 1, 1)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                        .add(layout.createSequentialGroup()
                                            .add(btn4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(btn5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(btn6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(layout.createSequentialGroup()
                                            .add(btn7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(btn8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(5, 5, 5)
                                            .add(btn9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(layout.createSequentialGroup()
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(layout.createSequentialGroup()
                                                    .add(btn1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                    .add(btn2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                                .add(layout.createSequentialGroup()
                                                    .add(btn0, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                    .add(btnDesetinaTecka, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(btnSmazat, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(btn3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                    .add(btnRovnase, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 106, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(btnClean, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(Operace, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(txtVstup))
                    .add(Displej, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(txtVstup, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(Displej, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 18, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(2, 2, 2)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(btn9)
                            .add(btn8)
                            .add(btn7))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(btn6)
                            .add(btn5)
                            .add(btn4))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(btn3)
                            .add(btn2)
                            .add(btn1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(btnSmazat)
                            .add(btnDesetinaTecka)
                            .add(btn0))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(btnClean)
                            .add(btnRovnase)))
                    .add(Operace, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 171, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(14, 14, 14))
        );

        Displej.getAccessibleContext().setAccessibleName("");

        getAccessibleContext().setAccessibleName("Kalkulačka");
        getAccessibleContext().setAccessibleDescription("Já to spočítám !!!");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NumAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumAction
        vstup += evt.getActionCommand();
        //zadani += evt.getActionCommand();       
        txtVstup.setText(vstup);
        Displej.repaint();
    }//GEN-LAST:event_NumAction

    private void btnCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanActionPerformed
        vstup = "";
        //zadani = "";
        vysledek = "";
        txtVstup.setText(vstup);
        Displej.repaint();
    }//GEN-LAST:event_btnCleanActionPerformed

    private void btnRovnaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRovnaseActionPerformed
        if (zlomek && vstup.charAt(vstup.length() - 1) != ')') {
            vstup += ')';
        }
        MathList input;
        input = new MathList().fillColection(vstup);
        //input = new ArrayList(Source.fillColection(vstup));
        try {
            if (input.textControl()) {
                if (input.bracers()) {
                    vysledek = Double.toString(BinOp.fromArrayList(input).evaluate());
                    Displej.repaint();
                } else {
                    vysledek = "Chybná syntaxe, zkontroluj závorky.";
                    Displej.repaint();
                }
            } else {
                vysledek = "V zadání je nepodporovaný znak.";
                Displej.repaint();
            }
        } catch (NumberFormatException ex) {
            vysledek = "Chyba vstupních dat.";
            Displej.repaint();
        } catch (ArrayIndexOutOfBoundsException ey) {
            vysledek = "Chyba vstupních dat.";
            Displej.repaint();
        } catch (ClassCastException ez) {
            vysledek = "Chyba vstupních dat.";
            Displej.repaint();
        }
    }//GEN-LAST:event_btnRovnaseActionPerformed

    private void btnSmazatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSmazatActionPerformed
        try {
            vstup = vstup.substring(0, vstup.length() - 1);
            txtVstup.setText(vstup);
            Displej.repaint();
//            if ((zadani.charAt(zadani.length() - 1) == zadani.charAt(zadani.length() - 2)) && zadani.length() - 1 == '/') {
//                zadani = zadani.substring(0, zadani.length() - 2);
//                Displej.repaint();
//            } else {
//                zadani = zadani.substring(0, zadani.length() - 1);
//                Displej.repaint();
//            }

        } catch (StringIndexOutOfBoundsException ex) {
            vstup = "";
            txtVstup.setText(vstup);
           // zadani = "";
            Displej.repaint();
        }
    }//GEN-LAST:event_btnSmazatActionPerformed

    private void mUlozitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mUlozitActionPerformed
//        JFileChooser uloz = new JFileChooser();
//        int n = uloz.showOpenDialog(uloz);
//        Source.saveAsFile(uloz.getSelectedFile().toString(), txfVstup.getText() + " = " + txfVystup.getText());
    }//GEN-LAST:event_mUlozitActionPerformed

    private void mNacistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mNacistActionPerformed
//        JFileChooser nacti = new JFileChooser();
//        int n = nacti.showOpenDialog(nacti);
//        txfVstup.setText(Source.loadFile(nacti.getSelectedFile().toString()));
//        ArrayList input;
//        input = new ArrayList(Source.fillColection(txfVstup.getText()));
//
//        try {
//            if (Source.textControl(input)) {
//                if (Source.bracers(input)) {
//                    txfVystup.setText(Double.toString(BinOp.fromArrayList(input).evaluate()));
//                } else {
//                    txfVystup.setText("Chybná syntaxe, zkontroluj závorky.");
//                }
//            } else {
//                txfVystup.setText("V zadání je nepodporovaný znak.");
//            }
//        } catch (NumberFormatException ex) {
//            txfVystup.setText("Chyby vstupních dat, zkontroluj syntaxi.");
//        } catch (ArrayIndexOutOfBoundsException ey) {
//            txfVystup.setText("Chyby vstupních dat.");
//        }
    }//GEN-LAST:event_mNacistActionPerformed

    private void mKonecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mKonecActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mKonecActionPerformed

    private void DisplejScrollBarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DisplejScrollBarMouseReleased
        poziceScrolu = DisplejScrollBar.getValue();
        Displej.repaint();
    }//GEN-LAST:event_DisplejScrollBarMouseReleased
    boolean jmenovatel = false;
    boolean zlomek = false;
    private void txtVstupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVstupActionPerformed
        vstup = txtVstup.getText();
        //zadani = vstup;
        Displej.repaint();
    }//GEN-LAST:event_txtVstupActionPerformed

    private void txtVstupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVstupKeyReleased
        vstup = txtVstup.getText();
        //zadani = vstup;
        Displej.repaint();
    }//GEN-LAST:event_txtVstupKeyReleased

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Calc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calc().setVisible(true);
            }
        });

    }

    public void kresli(Graphics g) {
        Grafic a = new Grafic();
        a.drawSource(vstup, poziceScrolu, Displej, DisplejScrollBar, g);
        a.drawResult(vysledek, poziceScrolu, Displej, DisplejScrollBar, g);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Displej;
    private javax.swing.JScrollBar DisplejScrollBar;
    private javax.swing.JMenu Menu;
    private javax.swing.JPanel Operace;
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnDeleni;
    private javax.swing.JButton btnDesetinaTecka;
    private javax.swing.JButton btnMinus;
    private javax.swing.JButton btnMocnina;
    private javax.swing.JButton btnNasobeni;
    private javax.swing.JButton btnOdmocnina;
    private javax.swing.JButton btnPlus;
    private javax.swing.JButton btnRovnase;
    private javax.swing.JButton btnSmazat;
    private javax.swing.JButton btnZavorkaL;
    private javax.swing.JButton btnZavorkaP;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mKonec;
    private javax.swing.JMenuItem mNacist;
    private javax.swing.JMenuItem mUlozit;
    private javax.swing.JTextField txtVstup;
    // End of variables declaration//GEN-END:variables
}
