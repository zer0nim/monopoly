/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ihm;

import static Ihm.Interface.window1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author albertar
 */
public class Plateau extends JPanel {
     
    
    private JButton lancerDe;
    private JButton acheter;
    private JButton finDuTour;
    private DefaultTableModel model ;
    
    
        public Plateau (){
        super();
        setBackground(Color.white);
       Plateau();
       
        
        
    }
    
        public void Plateau(){
            
        this.setLayout(new BorderLayout());
        
        
       JPanel bouttonAction = new JPanel();
       bouttonAction.setLayout(new GridLayout (1,3));
       this.add(bouttonAction,BorderLayout.SOUTH);
       
       lancerDe = new JButton("Lancer le Dé");
        bouttonAction.add(lancerDe);
        lancerDe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
         String message =  "Annuler. ";
                JOptionPane.showMessageDialog(window1 ,message, "Modification",JOptionPane.PLAIN_MESSAGE);
            }
        });
        acheter = new JButton("Acheter le logement");
        bouttonAction.add(acheter);
        acheter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 String message = "Modifications Sauvegardés";                
                JOptionPane.showMessageDialog(window1 ,message, "Modification",JOptionPane.PLAIN_MESSAGE);
            }
        });
        finDuTour = new JButton("Fin du tour");
        bouttonAction.add(finDuTour);
        finDuTour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 String message= "Groupe supprimé. ";
                JOptionPane.showMessageDialog(window1 ,message, "Groupe",JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        JPanel tableauJoueurs = new JPanel();
        this.add(tableauJoueurs,BorderLayout.EAST);
        model = new DefaultTableModel();
        String [] entetes = {"Joueur","Cash"};
        model.setColumnIdentifiers(entetes);
            
            JTable table = new JTable(model);
            tableauJoueurs.add(table.getTableHeader());
            tableauJoueurs.add(table);
         
}
}