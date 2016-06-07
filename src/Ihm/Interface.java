/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ihm;

import Data.Joueur;
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
    private Controleur controleur= new Controleur();
    private JTextField champJoueur;
    private JComboBox nbJoueurPossible= new JComboBox();
    
    private int nbJoueur = 0;
    
    public Interface(){
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
	
	

	JPanel choixJoueur2 = new JPanel();
	this.add(choixJoueur2,BorderLayout.NORTH);

	IhmNbJoueur ihmNbj = new IhmNbJoueur();
	setNbJoueur(ihmNbj.afficherBoiteDialogue());
	    
	    for (int j = 0; j < getNbJoueur() ; j++) {
		choixJoueur2.add(new JLabel("Prenom :"));   //Affiche un indice devant
		champJoueur = new JTextField(30);   //Taille de la fenetre de saisie
		choixJoueur2.add(champJoueur);              //Permet d'affiocher la fenetre de saisie

		//controleur.getMonopoly().setJoueur(new Joueur(champJoueur.toString(), controleur.getMonopoly().getCarreaux().get(0)));
	    }
	    //controleur.quiCommence();



	    choixJoueur.add(choixJoueur2);
	    	this.add(choixJoueur,BorderLayout.CENTER);

	    
    }
    
    
    private void Fenetre(){
       JFrame frame = new JFrame();
       frame.setTitle("Partie de Monopoly");
       frame.setSize(900, 800);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(new Plateau(controleur.getMonopoly().getJoueurs()));
       frame.setVisible(true);
    }

    public int getNbJoueur() {
	return nbJoueur;
    }

    public void setNbJoueur(int nbJoueur) {
	this.nbJoueur = nbJoueur;
    }
}
