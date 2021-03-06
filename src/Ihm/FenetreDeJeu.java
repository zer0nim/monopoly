package Ihm;

import Data.Biens_achetables;
import Data.Joueur;
import Jeu.ControleurGraphique;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FenetreDeJeu {

    private JFrame frame = new JFrame();
    private Joueur jCourant;
    private static ArrayList<Joueur> deathNote;
    private IhmPlateau ihmPlateau;
    private JPanel animationDe;
    private JPanel communcication;
    private JPanel PaneauInfosJoueurs;
    private JPanel PaneauIntermediaireInfosJoueurs;
    private JPanel boutons;
    
    JPanel panelPrincipal;
    private ComposantsPlateau pl;

    public FenetreDeJeu(ControleurGraphique controleur){ //créer la fenêtre du jeu
	pl = new ComposantsPlateau();
	frame = new JFrame();
	frame.setTitle("Partie de Monopoly"); //nom de la fenêtre
	frame.setSize(1000, 900); //taille de la fenêtre 1000 de large sur 900 de haut
	
	frame.setMinimumSize(new Dimension(1000,950));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        
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
        
        communcication = pl.communication("Affichage", new Object[]{"Appuyer sur \"Lancer les dés\" pour commencer la partie.", false}, controleur); //init
	
	PaneauInfosJoueurs = new JPanel();
	
	PaneauInfosJoueurs.setBackground(new Color(124,155,120));

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
	
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.revalidate();
                frame.repaint();
            }
        });
        timer.start();
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
    
    public void setCommunication(String type, Object[] data,ControleurGraphique controleur){
        for(int i =0; i < communcication.getComponentCount(); i++){
            communcication.remove(communcication.getComponent(i));
        }
        JPanel com = pl.communication(type, data, controleur);
        //communcication.add(com, BorderLayout.CENTER);
        communcication.add(com, BorderLayout.CENTER);
    }
        
    public void setInfosJoueurs(ControleurGraphique controleur){
        jCourant = controleur.getJoueurCourant();
        PaneauIntermediaireInfosJoueurs.remove(PaneauIntermediaireInfosJoueurs.getComponent(1));
        for(int i =0; i < PaneauInfosJoueurs.getComponentCount(); i++){
            PaneauInfosJoueurs.remove(PaneauInfosJoueurs.getComponent(i));
        }
        JPanel info = new JPanel(new BorderLayout());
	
	info.setBackground(new Color(124,155,120));
	
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
    
    public void closeFrame(){
        frame.setVisible(false);
        frame.dispose();
    }
}
