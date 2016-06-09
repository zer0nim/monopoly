package Ihm;

import Data.Biens_achetables;
import Data.Joueur;
import Jeu.ControleurGraphique;
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
    private JPanel animationDe;
    
    JPanel panelPrincipal;
    private Plateau pl;

    public FenetreDeJeu(ControleurGraphique controleur){
	pl = new Plateau();
	frame = new JFrame();
	frame.setTitle("Partie de Monopoly");
	frame.setSize(900, 800);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	ihmPlateau = new IhmPlateau(controleur.getMonopoly());

	
	panelPrincipal = new JPanel();
	panelPrincipal.setLayout(new BorderLayout());
	
	panelPrincipal.add(pl.Bouton(controleur), BorderLayout.SOUTH);
		
	jCourant = controleur.getMonopoly().getJoueurs().get(0); //initialise le joueur courant

        animationDe = pl.deAnimation();
        animationDe.setVisible(false);
	
	JPanel PaneauInfosJoueurs = new JPanel();
	PaneauInfosJoueurs.setLayout(new BorderLayout());
	PaneauInfosJoueurs.add(pl.InfoJoueur(jCourant), BorderLayout.NORTH);
        JPanel PaneauIntermediaireInfosJoueurs = new JPanel(new BorderLayout());
        PaneauIntermediaireInfosJoueurs.add(animationDe, BorderLayout.NORTH);
	PaneauIntermediaireInfosJoueurs.add(pl.TabJoueur(controleur.getMonopoly().getJoueurs()), BorderLayout.SOUTH);
        PaneauInfosJoueurs.add(PaneauIntermediaireInfosJoueurs, BorderLayout.SOUTH);
	panelPrincipal.add(PaneauInfosJoueurs, BorderLayout.EAST);

	
	panelPrincipal.add(ihmPlateau.getJc(), BorderLayout.CENTER);
	
	frame.add(panelPrincipal);
	frame.setVisible(true);
	
    }
    
    public void ControlDesTours(ControleurGraphique controleur) {
	deathNote = new ArrayList<>(); //ArrayList qui permet de lister les joueurs à éliminer
	
    	if(controleur.getMonopoly().getJoueurs().size() > 1){ //tant qu'il y a au moins 2 joueurs
            controleur.setJoueurCourant(jCourant); //chaque joueur jous un coup
            if(jCourant.estMort()){ //si le joueur à 0 ou moins de cash
                controleur.getMonopoly().getJoueurs().remove(jCourant);
	    }
            jCourant = controleur.getJoueurCourant();
	}else{
            Ihm.Winner(controleur.getMonopoly().getJoueurs().get(0));
        }
    }

    /**
     * @return the animationDe
     */
    public JPanel getAnimationDe() {
        return animationDe;
    }

    /**
     * @param animationDe the animationDe to set
     */
    public void setAnimationDe(JPanel animationDe) {
        this.animationDe = animationDe;
    }
    
    
}
