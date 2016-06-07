/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ihm;

import Jeu.Controleur;
import java.awt.Color;
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
    private JButton lancerDe;
    private JButton acheter;
    private JButton finDuTour;
    private Plateau pl;
    public static JFrame window1; 
    private Controleur controleur;
    private JComboBox nbJoueur= new JComboBox();
    
    public Interface (){
        super();
        setBackground(Color.white);
       initUIComponents();
       
        
        
    }
    
    private void initUIComponents(){
    this.setLayout(new BorderLayout());
    
    JLabel logo = new JLabel(new ImageIcon("src/Image/logo2.jpeg"));
    this.add(logo, BorderLayout.NORTH);
    
    
    JPanel lancement = new JPanel ();
    lancement.setLayout(new GridLayout (1,2));
    this.add(lancement,BorderLayout.SOUTH);
    
        jouer = new JButton("Jouer au monopoly");
        lancement.add(jouer);
        jouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fenetre();
               
            }
        });
        annuler = new JButton("Quittez le jeu");
        lancement.add(annuler);
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 System.exit(0);
            }
        });
        JPanel choixJoueur = new JPanel();
        choixJoueur.setLayout(new GridLayout(1,2));
        this.add(choixJoueur,BorderLayout.CENTER);
        
            JPanel choixJoueur2 = new JPanel();
            this.add(choixJoueur2,BorderLayout.NORTH);
        choixJoueur2.add(new JLabel("Nombre de joueur")); 
                 for (int i = 1; i<=6;i++ ){
                    nbJoueur.addItem(i);
                 }
                 choixJoueur2.add(nbJoueur);
             choixJoueur.add(choixJoueur2);
        
       
        
    
}
    private void Fenetre(){
        JFrame frame = new JFrame();
       frame.setTitle("Partie de Monopoly");
       frame.setSize(900, 800);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(new Plateau(controleur.getMonopoly().getJoueurs()));
       frame.setVisible(true);
    }
}
