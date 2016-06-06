/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author albertar
 */
public class Interface extends JPanel {
    private JButton jouer;
    private JButton annuler;
    public static JFrame window1; 
    
    public Interface (){
        super();
        setBackground(Color.white);
       initUIComponents();
        
        
    }
    
    private void initUIComponents(){
    this.setLayout(new BorderLayout());
    
    JLabel logo = new JLabel(new ImageIcon("src/Image/logo2.jpeg"));
    this.add(logo, BorderLayout.NORTH);
    
    JLabel plateau = new JLabel(new ImageIcon("src/Image/assassins-plateau-600.jpg"));
    this.add(plateau, BorderLayout.CENTER);
    
    JPanel lancement = new JPanel ();
    lancement.setLayout(new GridLayout (1,2));
    this.add(lancement,BorderLayout.SOUTH);
    
        jouer = new JButton("Jouer au monopoly");
        lancement.add(jouer);
        jouer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message= "Groupe supprimé. ";
               
            }
        });
        annuler = new JButton("Quittez le jeu");
        lancement.add(annuler);
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 System.exit(0);
            }
        });
    
}
    
}
