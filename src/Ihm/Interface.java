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
    private JButton lancerDe;
    private JButton acheter;
    private JButton finDuTour;
    private Plateau pl;
    public static JFrame window1; 
    private Controleur controleur;
    private JTextField champJoueur;
    private JComboBox nbJoueurPossible;
    
    private ArrayList<JTextField> champNomjoueurs;
    
    private int nbJoueur = 0;
    
    public Interface(){
	JFrame frame = new JFrame();
	frame.setTitle("Monopoly");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controleur= new Controleur();
	nbJoueurPossible= new JComboBox();
	champNomjoueurs = new ArrayList<>();
	
	setBackground(Color.white);
        initUIComponents();
	
	frame.add(this);
	
	frame.setSize(500, ResponsiveHeight());

	frame.setVisible(true);
    }
    
    private void initUIComponents(){
	this.setLayout(new BorderLayout());

	JLabel logo = new JLabel(new ImageIcon("src/Image/logo2.jpeg"));
	this.add(logo, BorderLayout.NORTH);


	JPanel lancement = new JPanel ();
	lancement.setLayout(new GridLayout (1,2));
	this.add(lancement,BorderLayout.SOUTH);

	jouer = new JButton("Jouer au monopoly");  //crée les joueurs et lance la partie
	lancement.add(jouer);
	jouer.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		int i = 0;
		for (JTextField jc : champNomjoueurs){
		    controleur.getMonopoly().getJoueurs().get(i).setNomJoueur(jc.getText());
		    i++;
		}
		FenetreDeJeu();
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
	ChoixNomJoueurs.setLayout(new GridLayout(nbJoueur,1));
	
        for (int j = 0; j < nbJoueur ; j++) {  //boucle pour ajouter les champs de saisie du nom de joueurSS
	    JLabel prenom = new JLabel("Joueur :");
	    prenom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
	    Font font = new Font("Arial",Font.BOLD,16);
	    prenom.setFont(font);
		
	    ChoixNomJoueurs.add(prenom);
	    champNomjoueurs.add(new JTextField(30)); //ajoute un champ de selection dans la liste champNomjoueurs
	    ChoixNomJoueurs.add(champNomjoueurs.get(champNomjoueurs.size()-1)); //récupere derniers champ de selection de la liste champNomjoueurs et l'ajoute dans le JPanel

	    
	    controleur.getMonopoly().setJoueur(new Joueur("noName", controleur.getMonopoly().getCarreaux().get(0))); //crée un joueur avec nom par defaut
	    }
	    controleur.quiCommence(); //lance le dé pour savoir qui commence
	//------^^^---paneau de selection nom joueur---^^^

	this.add(ChoixNomJoueurs,BorderLayout.CENTER); // ajout de ChoixNomJoueurs dans la fenetre

	    
    }
    
    public int ResponsiveHeight(){ //permet de dimensionner la fenetre selon le nombre de joueurs
	return (nbJoueur*45)+200;
    }
    
    private void FenetreDeJeu(){
       JFrame frame = new JFrame();
       frame.setTitle("Partie de Monopoly");
       frame.setSize(900, 800);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(new Plateau(controleur.getMonopoly().getJoueurs()));
       frame.setVisible(true);
    }
}