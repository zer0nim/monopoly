package Ihm;

import Data.Biens_achetables;
import Data.Joueur;
import Jeu.ControleurGraphique;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPanel communcication;
    private JPanel PaneauInfosJoueurs;
    private JPanel PaneauIntermediaireInfosJoueurs;
    private JPanel boutons;
    
    JPanel panelPrincipal;
    private Plateau pl;

    public FenetreDeJeu(ControleurGraphique controleur){ //créer la fenêtre du jeu
	pl = new Plateau();
	frame = new JFrame();
	frame.setTitle("Partie de Monopoly"); //nom de la fenêtre
	frame.setSize(1000, 900); //taille de la fenêtre 1000 de large sur 900 de haut
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	ihmPlateau = new IhmPlateau(controleur.getMonopoly());

	
	panelPrincipal = new JPanel();
	panelPrincipal.setLayout(new BorderLayout());
	
        boutons = pl.Bouton(controleur); //JPanel contenant les boutons "Lancer les dés", "Acheter le bien", "Construire" et "Fin de tour"
        
	panelPrincipal.add(boutons, BorderLayout.SOUTH);
	
	controleur.quiCommence();
	jCourant = controleur.getMonopoly().getJoueurs().get(0); //initialise le joueur courant
        controleur.setJoueurCourant(jCourant);
        
        animationDe = pl.deAnimation();
        animationDe.getComponent(0).setVisible(false);
        
        communcication = pl.communication("Affichage", new Object[]{"Appuer sur \"Lancer les dés\" pour commencer la partie."});
	
	PaneauInfosJoueurs = new JPanel();
	PaneauInfosJoueurs.setLayout(new BorderLayout());
	PaneauInfosJoueurs.add(pl.InfoJoueur(jCourant), BorderLayout.NORTH);
        PaneauIntermediaireInfosJoueurs = new JPanel(new BorderLayout());
        PaneauIntermediaireInfosJoueurs.add(animationDe, BorderLayout.NORTH);
	PaneauIntermediaireInfosJoueurs.add(pl.TabJoueur(controleur.getMonopoly().getJoueurs()), BorderLayout.SOUTH);
        PaneauInfosJoueurs.add(PaneauIntermediaireInfosJoueurs, BorderLayout.SOUTH);
	panelPrincipal.add(PaneauInfosJoueurs, BorderLayout.EAST);

	JPanel panelIntermediaire = new JPanel(new BorderLayout());
        panelIntermediaire.add(ihmPlateau.getJc(), BorderLayout.CENTER);
        panelIntermediaire.add(communcication, BorderLayout.SOUTH);
	panelPrincipal.add(panelIntermediaire, BorderLayout.CENTER);
	
	frame.add(panelPrincipal);
	frame.setVisible(true);
	
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.revalidate();
                frame.repaint();
            }
        });
        timer.start();
    }
    
    public void ControlDesTours(ControleurGraphique controleur) {
	deathNote = new ArrayList<>(); //ArrayList qui permet de lister les joueurs à éliminer
    	if(controleur.getMonopoly().getJoueurs().size() > 1){ //si reste plus de 1 joueur
            setEnabledButton(new Integer[]{1,0,0,0});
            jCourant = controleur.getJoueurCourant();//tant qu'il y a au moins 2 joueurs
            if(jCourant.estMort()){ //si le joueur à 0 ou moins de cash
                controleur.getMonopoly().getJoueurs().remove(jCourant);
	    }
            jCourant = controleur.getJoueurCourant();
	}else{
            //Ihm.Winner(controleur.getMonopoly().getJoueurs().get(0));
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
    public void setResultatDe(JPanel resultat, boolean visible) {
        if(visible){
            animationDe.getComponent(0).setVisible(false);
            animationDe.add(resultat, BorderLayout.NORTH);
        }else{
            animationDe.remove(animationDe.getComponent(1));
            animationDe.getComponent(0).setVisible(true);
        }
    }
    
    public void setCommunication(String type, Object[] data){
        for(int i =0; i < communcication.getComponentCount(); i++){
            communcication.remove(communcication.getComponent(i));
        }
        JPanel com = pl.communication(type, data);
        //communcication.add(com, BorderLayout.CENTER);
        communcication.add(com, BorderLayout.CENTER);
    }
    
    public void setInfosJoueurs(ControleurGraphique controleur){
        PaneauIntermediaireInfosJoueurs.remove(PaneauIntermediaireInfosJoueurs.getComponent(1));
        for(int i =0; i < PaneauInfosJoueurs.getComponentCount(); i++){
            PaneauInfosJoueurs.remove(PaneauInfosJoueurs.getComponent(i));
        }
        JPanel info = new JPanel(new BorderLayout());
        info.add(pl.InfoJoueur(jCourant), BorderLayout.NORTH);
	PaneauIntermediaireInfosJoueurs.add(pl.TabJoueur(controleur.getMonopoly().getJoueurs()), BorderLayout.SOUTH);
        info.add(PaneauIntermediaireInfosJoueurs, BorderLayout.SOUTH);
        PaneauInfosJoueurs.add(info, BorderLayout.CENTER);
    }

    public void setEnabledButton(Integer[] b){
        for(int i = 0; i < 4; i++){
            if(b[i] == 0){
                boutons.getComponent(i).setEnabled(false);
            }else if(b[i] == 1){
                boutons.getComponent(i).setEnabled(true);
            }
        }
        if(b[0] == 1){
                boutons.getComponent(3).setEnabled(false);
        }else if(b[3] == 1){
            boutons.getComponent(0).setEnabled(false);
        }
    }
}
