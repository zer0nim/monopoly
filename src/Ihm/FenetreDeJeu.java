package Ihm;

import Data.Biens_achetables;
import Data.Joueur;
import Jeu.Controleur;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class FenetreDeJeu {

    private JFrame frame = new JFrame();
    private JButton lancerDe;
    private JButton acheter;
    private JButton finDuTour;
    private Joueur jCourant;
    private static ArrayList<Joueur> deathNote;
    private IhmPlateau ihmPlateau;
    
    JPanel panelPrincipal;
    private Plateau pl;

    public FenetreDeJeu(Controleur controleur){
	pl = new Plateau();
	frame = new JFrame();
	frame.setTitle("Partie de Monopoly");
	frame.setSize(900, 800);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	ihmPlateau = new IhmPlateau(controleur.getMonopoly());

	
	panelPrincipal = new JPanel();
	panelPrincipal.setLayout(new BorderLayout());
	
	panelPrincipal.add(pl.Bouton(), BorderLayout.SOUTH);
		
	jCourant = controleur.getMonopoly().getJoueurs().get(0); //initialise le joueur courant
	jCourant.addPropriété((Biens_achetables)controleur.getMonopoly().getCarreaux().get(1));
	jCourant.addPropriété((Biens_achetables)controleur.getMonopoly().getCarreaux().get(3));
	jCourant.addPropriété((Biens_achetables)controleur.getMonopoly().getCarreaux().get(5));
	jCourant.addPropriété((Biens_achetables)controleur.getMonopoly().getCarreaux().get(8));
		jCourant.addPropriété((Biens_achetables)controleur.getMonopoly().getCarreaux().get(9));


	
	JPanel PaneauInfosJoueurs = new JPanel();
	PaneauInfosJoueurs.setLayout(new BorderLayout());
	PaneauInfosJoueurs.add(pl.InfoJoueur(jCourant), BorderLayout.NORTH);
	PaneauInfosJoueurs.add(pl.TabJoueur(controleur.getMonopoly().getJoueurs()), BorderLayout.SOUTH);
	panelPrincipal.add(PaneauInfosJoueurs, BorderLayout.EAST);

	
	panelPrincipal.add(ihmPlateau.getJc(), BorderLayout.CENTER);
	
	frame.add(panelPrincipal);
	frame.setVisible(true);
	
	ControlDesTours(controleur);
    }
    public void ControlDesTours(Controleur controleur) {
	/*deathNote = new ArrayList<>(); //ArrayList qui permet de lister les joueurs à éliminer
	
    	while(controleur.getMonopoly().getJoueurs().size() > 1){ //tant qu'il y a au moins 2 joueurs
	    for (Joueur j : controleur.getMonopoly().getJoueurs()){ //On boucle sur les joueurs encore en jeu
		if (controleur.getMonopoly().getJoueurs().size() > 1){//tant qu'il y a au moins 2 joueurs
		    jCourant = j;
		    controleur.jouerUnCoup(j); //chaque joueur jous un coup
		    if(j.estMort()){ //si le joueur à 0 ou moins de cash
			Ihm.Cimetiere(j); //affiche que le joueur est éliminé
			deathNote.add(j); //ajoute le joueur à la liste d'élimination
		    }
		}
		
	    }
	    for (Joueur j : deathNote){ //pour chaque joueur de la liste d'élimination
		controleur.getMonopoly().getJoueurs().remove(j); //On élimine les joueur du monopoly
	    }
	    deathNote.clear(); //Puis on vide cette list pour le prochain passage
	}
	Ihm.Winner(controleur.getMonopoly().getJoueurs().get(0));
    */}
}
