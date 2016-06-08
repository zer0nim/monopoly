package Ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Data.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Plateau extends JPanel {

    private JButton lancerDe;
    private JButton acheter;
    private JButton finDuTour;
    private DefaultTableModel model;
    private Joueur j;
    
    private JFrame window1 = new JFrame();

    public Plateau(ArrayList<Joueur> joueurs) {
	super();
	setBackground(Color.white);
	this.setLayout(new BorderLayout());
	Bouton();
	TabJoueur(j, joueurs);
	InfoJoueur(j);

    }

    public void Bouton() {
	JPanel bouttonAction = new JPanel();
	bouttonAction.setLayout(new GridLayout(1, 3));
	this.add(bouttonAction, BorderLayout.SOUTH);

	lancerDe = new JButton("Lancer le Dé");
	bouttonAction.add(lancerDe);
	lancerDe.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		String message = "Annuler. ";
		JOptionPane.showMessageDialog(window1, message, "Modification", JOptionPane.PLAIN_MESSAGE);
	    }
	});
	acheter = new JButton("Acheter le logement");
	bouttonAction.add(acheter);
	acheter.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		String message = "Modifications Sauvegardés";
		JOptionPane.showMessageDialog(window1, message, "Modification", JOptionPane.PLAIN_MESSAGE);
	    }
	});
	finDuTour = new JButton("Fin du tour");
	bouttonAction.add(finDuTour);
	finDuTour.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		String message = "Groupe supprimé. ";
		JOptionPane.showMessageDialog(window1, message, "Groupe", JOptionPane.PLAIN_MESSAGE);
	    }
	});
    }

    public void TabJoueur(Joueur j, ArrayList<Joueur> joueurs) {

	JPanel tableauJoueurs = new JPanel();
	tableauJoueurs.setLayout(new GridLayout(2, 1));
	this.add(tableauJoueurs, BorderLayout.EAST);

	model = new DefaultTableModel();
	String[] entetes = {"Joueur", "Cash"};
	model.setColumnIdentifiers(entetes);

	JTable table = new JTable(model);
	tableauJoueurs.add(table.getTableHeader());
	tableauJoueurs.add(table);

	String[] valeur = new String[2];
	for (Joueur jCourant : joueurs) {
	    valeur[0] = jCourant.getNomJoueur();
	    valeur[1] = Integer.toString(jCourant.getCash());
	    model.addRow(valeur);
	}

    }

    public void InfoJoueur(Joueur j) {
	JPanel infoJoueur = new JPanel();
	infoJoueur.setLayout(new GridLayout(8, 2));
	this.add(infoJoueur, BorderLayout.WEST);

	infoJoueur.add(new JLabel("Nom du Joueur"));
    }
}
