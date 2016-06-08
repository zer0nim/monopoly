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

public class Interface extends JPanel {

    private JButton jouer;
    private JButton annuler;

    private Controleur controleur;
    private JFrame frame;
    private ArrayList<JTextField> champNomjoueurs;
    private int nbJoueur;

    public Interface() {
	frame = new JFrame();
	frame.setTitle("Choix nom joueurs");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	nbJoueur = 0;

	controleur = new Controleur();
	champNomjoueurs = new ArrayList<>();

	setBackground(Color.white);
	initUIComponents();

	frame.add(this);

	frame.setSize(500, ResponsiveHeight());

	frame.setVisible(true);
    }

    private void initUIComponents() {
	this.setLayout(new BorderLayout());

	JLabel logo = new JLabel(new ImageIcon("src/Image/logo2.jpeg"));
	this.add(logo, BorderLayout.NORTH);

	JPanel lancement = new JPanel();
	lancement.setLayout(new GridLayout(1, 2));
	this.add(lancement, BorderLayout.SOUTH);

	jouer = new JButton("Jouer au monopoly");  //crée les joueurs et lance la partie
	lancement.add(jouer);
	jouer.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		int i = 0;
		for (JTextField jc : champNomjoueurs) {
		    controleur.getMonopoly().getJoueurs().get(i).setNomJoueur(jc.getText());
		    i++;
		}
		frame.setVisible(false);
		frame.dispose();
		FenetreDeJeu fenetreJeu = new FenetreDeJeu(controleur);
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

	nbJoueur = (IhmNbJoueur.afficherBoiteDialogue()); //demande a l'utilisateur le nombre de joueurs via un widgetS

	//------vvv---paneau de selection nom joueur---vvv
	JPanel ChoixNomJoueurs = new JPanel();
	ChoixNomJoueurs.setLayout(new GridLayout(nbJoueur, 1));

	for (int j = 0; j < nbJoueur; j++) {  //boucle pour ajouter les champs de saisie du nom de joueurs
	    JLabel prenom = new JLabel("Joueur :");
	    prenom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

	    Font font = new Font("Arial", Font.BOLD, 16); //Police d'écriture Arial en Gras et de taille 16
	    prenom.setFont(font);

	    ChoixNomJoueurs.add(prenom);
	    champNomjoueurs.add(new JTextField(30)); //ajoute un champ de selection dans la liste champNomjoueurs
	    ChoixNomJoueurs.add(champNomjoueurs.get(champNomjoueurs.size() - 1)); //récupere derniers champ de selection de la liste champNomjoueurs et l'ajoute dans le JPanel

	    controleur.getMonopoly().setJoueur(new Joueur("noName", controleur.getMonopoly().getCarreaux().get(0))); //crée un joueur avec nom par defaut
	}
	controleur.quiCommence(); //lance le dé pour savoir qui commence
	//------^^^---paneau de selection nom joueur---^^^

	this.add(ChoixNomJoueurs, BorderLayout.CENTER); // ajout de ChoixNomJoueurs dans la fenetre

    }

    public int ResponsiveHeight() { //permet de dimensionner la fenetre selon le nombre de joueurs
	return (nbJoueur * 45) + 200;
    }
}