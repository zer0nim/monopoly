package Ihm;

import java.awt.*;
import java.awt.event.*;
import Data.*;
import Jeu.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Plateau extends JPanel {

    private JButton lancerDe;
    private JButton acheter;
    private JButton finDuTour;
    private DefaultTableModel model;

    public JPanel Bouton(ControleurGraphique controleur) {
	JPanel bouttonAction = new JPanel();
	bouttonAction.setLayout(new GridLayout(1, 3));
	this.add(bouttonAction, BorderLayout.SOUTH);

	lancerDe = new JButton("Lancer le Dé");
	bouttonAction.add(lancerDe);
	lancerDe.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
                controleur.jouerUnCoup();
	    }
	});
	acheter = new JButton("Acheter le logement");
	bouttonAction.add(acheter);
	acheter.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    }
	});
	finDuTour = new JButton("Fin du tour");
	bouttonAction.add(finDuTour);
	finDuTour.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    }
	});
	return (bouttonAction);
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
	
	propJoueur.add(new JLabel("biens du joueur: "), BorderLayout.NORTH);
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
	
	panelInfoJoueur.add(propJoueur, BorderLayout.SOUTH);

	return (panelInfoJoueur);
    }
    
    public JPanel deAnimation(){
        JPanel animation = new JPanel(new BorderLayout());
        Icon icon = new ImageIcon("src/Image/animationDes.gif");
        JLabel label = new JLabel(icon);
        animation.add(label, BorderLayout.CENTER);
        return animation;
    }
}
