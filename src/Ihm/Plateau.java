package Ihm;

import java.awt.*;
import java.awt.event.*;
import Data.*;
import Jeu.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class Plateau extends JPanel {

    private JButton lancerDe;
    private JButton acheter;
    private JButton finDuTour;
    private JButton construire;
    private DefaultTableModel model;
    private DefaultTableModel model2;
    private Timer timer;

    public JPanel Bouton(ControleurGraphique controleur) {
	JPanel bouttonAction = new JPanel();
	bouttonAction.setLayout(new GridLayout(1, 4));
	this.add(bouttonAction, BorderLayout.SOUTH);

	lancerDe = new JButton("Lancer les dés");
	bouttonAction.add(lancerDe);
	lancerDe.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
                controleur.getInterfacee().getFenetre().setEnabledButton(new Integer[]{0,-1,-1,-1});
                controleur.setAnimationDeVisible();
                controleur.lancerDes();
                timer = new Timer(1500, new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controleur.setAnimationDe();
                        controleur.jouerUnCoup();
                        stopTimer();
                    }
                });
                timer.start();
                
	    }
	});
	acheter = new JButton("Acheter le bien");
	bouttonAction.add(acheter);
	acheter.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
                controleur.getJoueurCourant().addPropriété((Biens_achetables) controleur.getJoueurCourant().getPositionCourante());
                controleur.setCom("Affichage", new Object[]{controleur.getJoueurCourant().getNomJoueur() + " : Vous venez d'acheter " + controleur.getJoueurCourant().getPositionCourante().getNomCarreau() + "."});
                controleur.getInterfacee().getFenetre().setEnabledButton(new Integer[]{-1, 0,-1,-1});
                controleur.getInterfacee().getFenetre().setInfosJoueurs(controleur);
	    }
	});
        acheter.setEnabled(false);
        construire = new JButton("Construire");
	bouttonAction.add(construire);
	construire.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    }
	});
        construire.setEnabled(false);
	finDuTour = new JButton("Fin du tour");
	bouttonAction.add(finDuTour);
	finDuTour.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
                controleur.setJoueurSuivant(controleur.getJoueurCourant());
                controleur.getInterfacee().getFenetre().ControlDesTours(controleur);
                controleur.getInterfacee().getFenetre().setInfosJoueurs(controleur);
                controleur.getInterfacee().getFenetre().setCommunication("Affichage", new Object[]{controleur.getJoueurCourant().getNomJoueur() + " : Lancez les dés pour commencer votre tour."});
	    }
	});
        finDuTour.setEnabled(false);
	return (bouttonAction);
    }
    
    public void stopTimer(){
        timer.stop();
    }
    
    public JPanel TabJoueur(ArrayList<Joueur> joueurs) {

	JPanel tableauJoueurs = new JPanel();
	tableauJoueurs.setLayout(new BorderLayout());
	this.add(tableauJoueurs, BorderLayout.EAST);

	model = new DefaultTableModel() {

	    @Override
	    public boolean isCellEditable(int row, int column) {
		return false;//This causes all cells to be not editable
	    }
	};
	JTable table = new JTable(model);

	String[] entetes = {"Joueur", "Cash"};
	model.setColumnIdentifiers(entetes);

	tableauJoueurs.add(table.getTableHeader(), BorderLayout.NORTH);
	tableauJoueurs.add(table, BorderLayout.CENTER);

	String[] valeur = new String[2];
	for (Joueur jCourant : joueurs) {
	    valeur[0] = jCourant.getNomJoueur();
	    valeur[1] = Integer.toString(jCourant.getCash());
	    model.addRow(valeur);
	}

	return (tableauJoueurs);
    }

    public JPanel InfoJoueur(Joueur j) {
	JPanel panelInfoJoueur = new JPanel();
	panelInfoJoueur.setLayout(new BorderLayout());

	JPanel infoJoueur= new JPanel();
	infoJoueur.setLayout(new GridLayout(8, 2));

	
	infoJoueur.add(new JLabel("Nom du Joueur: " + j.getNomJoueur()));
	infoJoueur.add(new JLabel("Argent: " + j.getCash()));
	infoJoueur.add(new JLabel("Position Courante: " + j.getPositionCourante().getNumero()));
	infoJoueur.add(new JLabel("Cartes Sortie de Prison: " + j.getCarteLibPrison()));
	infoJoueur.add(new JLabel("En prison: " + ((j.getPrison() == 0) ? "non" : "oui")));

	panelInfoJoueur.add(infoJoueur, BorderLayout.CENTER);
	
	JPanel propJoueur= new JPanel();
	propJoueur.setLayout(new BorderLayout());
	
	propJoueur.add(new JLabel("Propriétés du joueur: "), BorderLayout.NORTH);
	model = new DefaultTableModel() {

	    @Override
	    public boolean isCellEditable(int row, int column) {
		return false;//This causes all cells to be not editable
	    }
	};
	JTable table = new JTable(model);

	String[] entetes = {"Numéro", "Nom"};
	model.setColumnIdentifiers(entetes);

	String[] valeur = new String[2];

	for (Biens_achetables bien : j.getPropriétés()) {
	    valeur[0] = Integer.toString(bien.getNumero());
	    valeur[1] = bien.getNomCarreau();
	    model.addRow(valeur);
	}
	
	propJoueur.add(table.getTableHeader(), BorderLayout.CENTER);
	propJoueur.add(table, BorderLayout.SOUTH);
        
        JPanel consJoueur= new JPanel();
	consJoueur.setLayout(new BorderLayout());
	
	consJoueur.add(new JLabel("Constructions du joueur: "), BorderLayout.NORTH);
	model2 = new DefaultTableModel() {

	    @Override
	    public boolean isCellEditable(int row, int column) {
		return false;//This causes all cells to be not editable
	    }
	};
	JTable table2 = new JTable(model2);

	String[] entetes2 = {"Nom", "Type", "Nombre"};
	model2.setColumnIdentifiers(entetes2);

	String[] valeur2 = new String[3];

	for (Biens_achetables bien : j.getPropriétés()) {
            if(bien.getClass().getSimpleName().contains("ProprieteAConstruire")){
                if(((ProprieteAConstruire)bien).getNbConstructions() != 0){
                    valeur2[0] = bien.getNomCarreau();
                    valeur2[1] = ((ProprieteAConstruire)bien).getConstructions().get(0).getType();
                    valeur2[2] = Integer.toString(((ProprieteAConstruire)bien).getConstructions().size());
                }
            }
	    model2.addRow(valeur2);
	}
	
	consJoueur.add(table2.getTableHeader(), BorderLayout.CENTER);
	consJoueur.add(table2, BorderLayout.SOUTH);
	
        JPanel panelInter = new JPanel(new BorderLayout());
        consJoueur.setAutoscrolls(true);
        propJoueur.setAutoscrolls(true);
        propJoueur.setBorder(new EmptyBorder(10, 0, 20, 0));
        consJoueur.setBorder(new EmptyBorder(20, 0, 10, 0));
        panelInter.add(propJoueur, BorderLayout.CENTER);
        panelInter.add(consJoueur, BorderLayout.SOUTH);
	panelInfoJoueur.add(panelInter, BorderLayout.SOUTH);

	return (panelInfoJoueur);
    }
    
    public JPanel deAnimation(){
        JPanel animation = new JPanel(new BorderLayout());
        Icon icon = new ImageIcon("src/Image/animationDes.gif");
        JLabel label = new JLabel(icon);
        animation.add(label, BorderLayout.CENTER);
        animation.add(new JLabel(""), BorderLayout.NORTH);
        return animation;
    }
    
    public JPanel communication(String type, Object[] data){
        JPanel communication = new JPanel(new BorderLayout());
        communication.setPreferredSize(new Dimension(communication.getSize().width, 50));
        if(type == "Affichage"){
            JLabel label = new JLabel((String)data[0], JLabel.CENTER);
            communication.add(label, BorderLayout.CENTER);
        }else if(type == "DemandePrison") {
            JLabel label = new JLabel((String)data[0], JLabel.CENTER);
            JButton buttonYes = new JButton("Utiliser la carte");
            buttonYes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                       ((AutreCarreau)(((ControleurGraphique)data[1]).getJoueurCourant().getPositionCourante())).libPrisonCarte(((ControleurGraphique)data[1]).getJoueurCourant());
                }
            });
            JButton buttonNo = new JButton("Rester en prison");
            buttonYes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((AutreCarreau)(((ControleurGraphique)data[1]).getJoueurCourant().getPositionCourante())).purgerPeine(((ControleurGraphique)data[1]).getJoueurCourant());
                }
            });
            buttonYes.setBorder(new EmptyBorder(2, 0, 5, 20));
            buttonNo.setBorder(new EmptyBorder(2, 20, 5, 0));
            JPanel PanelInter = new JPanel(new BorderLayout());
            PanelInter.add(buttonYes, BorderLayout.EAST);
            PanelInter.add(buttonNo, BorderLayout.WEST);
            communication.add(label, BorderLayout.CENTER);
            communication.add(PanelInter, BorderLayout.SOUTH);
        }

        return communication;
    }
}
