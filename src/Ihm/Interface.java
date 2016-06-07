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
import java.util.ArrayList;
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
    private JTextField champJoueur;
    private JComboBox nbJoueurPossible;
    
    private ArrayList<JTextField> joueurs;
    
    private int nbJoueur = 0;
    
    public Interface(){
        super();
        controleur= new Controleur();
	nbJoueurPossible= new JComboBox();
	joueurs = new ArrayList<>();
	
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
		int i = 0;
		for (JTextField jc : joueurs){
		    controleur.getMonopoly().getJoueurs().get(i).setNomJoueur(jc.getText());
		    i++;
		}
		Fenetre();
	    }
	});
	
	annuler = new JButton("Quittez le jeu");
	lancement.add(annuler);
	annuler.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
	

	setNbJoueur(IhmNbJoueur.afficherBoiteDialogue());

	JPanel choixJoueur2 = new JPanel();
	choixJoueur2.setLayout(new GridLayout(getNbJoueur(),1));

	this.setSize(500, getNbJoueur()*20);
	
        for (int j = 0; j < getNbJoueur() ; j++) {
	    JLabel prenom = new JLabel("Joueur :");
	    prenom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
	    Font font = new Font("Arial",Font.BOLD,16);
	    prenom.setFont(font);
		
	    choixJoueur2.add(prenom);   //Affiche un indice devant
	    joueurs.add(new JTextField(30));   //Taille de la fenetre de saisie
	    choixJoueur2.add(joueurs.get(joueurs.size()-1));              //Permet d'affiocher la fenetre de saisie

	    
	    controleur.getMonopoly().setJoueur(new Joueur("noName", controleur.getMonopoly().getCarreaux().get(0)));
	    }
	    controleur.quiCommence();

	this.add(choixJoueur2,BorderLayout.CENTER);

	    
    }
    
    public int ResponsiveHeight(){
	return (nbJoueur*45)+200;
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
