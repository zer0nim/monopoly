package Ihm;

import java.awt.*;
import java.awt.event.*;
import Data.*;
import Jeu.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.JComponent;

public class Plateau extends JPanel {

    private JButton lancerDe;
    private JButton acheter;
    private JButton finDuTour;
    private JButton construire;
    private DefaultTableModel model;
    private DefaultTableModel model2;
    private DefaultTableModel model3;
    private Timer timer;

    public String PopupConstruction(ControleurGraphique controleur) {
	ArrayList<String> liste = new ArrayList<>();
	model3 = new DefaultTableModel() {

	    @Override
	    public boolean isCellEditable(int row, int column) {
		return false;//This causes all cells to be not editable
	    }
	};
	JPanel listeConstructions = new JPanel(new BorderLayout());
	JTable table3 = new JTable(model3);

	table3.setBackground(new Color(204, 227, 199));
	table3.getTableHeader().setBackground(new Color(219, 236, 215));

	String[] entetes = {"Numéro", "Carreau", "Nom", "Type", "Nombre", "Prix Achat", "Loyer"};

	model3.setColumnIdentifiers(entetes);

	listeConstructions.add(table3.getTableHeader(), BorderLayout.NORTH);
	listeConstructions.add(table3, BorderLayout.CENTER);

	String[] valeur = new String[7];
	int i = 0;
	for (Object[] cons : controleur.getJoueurCourant().getLiseConstructionsDispo()) {
	    valeur[0] = Integer.toString(i);
	    valeur[1] = Integer.toString(((ProprieteAConstruire) cons[0]).getNumero());
	    valeur[2] = ((ProprieteAConstruire) cons[0]).getNomCarreau();
	    valeur[3] = ((String) cons[1]);
	    if (((String) cons[1]) == "hotel") {
		valeur[4] = "1";
	    } else {
		valeur[4] = Integer.toString(((ProprieteAConstruire) cons[0]).getConstructions().size() + 1);
	    }
	    valeur[5] = Integer.toString(((Integer) cons[4]));
	    valeur[6] = Integer.toString(((Integer) cons[3]));
	    model3.addRow(valeur);
	    i++;
	    liste.add(valeur[0] + ", " + valeur[1] + ", " + valeur[2] + ", " + valeur[3] + ", " + valeur[4] + ", " + valeur[5] + ", " + valeur[6]);
	}
	String[] list = liste.toArray(new String[7]);
	JComboBox jcb = new JComboBox(list);
	listeConstructions.add(jcb, BorderLayout.SOUTH);
	JOptionPane.showConfirmDialog(null, listeConstructions, "Construire", JOptionPane.OK_CANCEL_OPTION);//création de la fenêtre de choix des joueurs
	return ((jcb.getSelectedItem().toString()));
    }

    public JPanel Bouton(ControleurGraphique controleur) {
	JPanel bouttonAction = new JPanel();

	bouttonAction.setLayout(new GridLayout(1, 4));
	this.add(bouttonAction, BorderLayout.SOUTH);

	lancerDe = new JButton("Lancer les dés");
	lancerDe.setBackground(new Color(219, 236, 215));

	bouttonAction.add(lancerDe);
	lancerDe.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		controleur.getInterfacee().getFenetre().setEnabledButton(new Integer[]{0, 0, 0, 1});
		controleur.setAnimationDeVisible();
		controleur.lancerDes();
		timer = new Timer(1500, new ActionListener() {
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
	acheter.setBackground(new Color(219, 236, 215));

	bouttonAction.add(acheter);
	acheter.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		Biens_achetables bienCourant = ((Biens_achetables) controleur.getJoueurCourant().getPositionCourante());
		Joueur joueurCourant = controleur.getJoueurCourant();

		joueurCourant.addPropriété(bienCourant);
		bienCourant.setPropriétaire(joueurCourant);
		joueurCourant.payerArgent(bienCourant.getPrixAchat());

		controleur.setCom("Affichage", new Object[]{controleur.getJoueurCourant().getNomJoueur() + " : Vous venez d'acheter " + controleur.getJoueurCourant().getPositionCourante().getNomCarreau() + "."});
		controleur.getInterfacee().getFenetre().setEnabledButton(new Integer[]{-1, 0, -1, -1});
		controleur.getInterfacee().getFenetre().setInfosJoueurs(controleur);
	    }
	});
	acheter.setEnabled(false);
	construire = new JButton("Construire");
	construire.setBackground(new Color(219, 236, 215));

	bouttonAction.add(construire);
	construire.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		//construire(); passer en object param
	    }
	});
	construire.setEnabled(false);
	finDuTour = new JButton("Fin du tour");
	finDuTour.setBackground(new Color(219, 236, 215));

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

    public void stopTimer() {
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

	table.setBackground(new Color(204, 227, 199));
	table.getTableHeader().setBackground(new Color(219, 236, 215));

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

	panelInfoJoueur.setBackground(new Color(124, 155, 120));

	JPanel infoJoueur = new JPanel();
	infoJoueur.setLayout(new GridLayout(6, 1));

	infoJoueur.setBackground(new Color(124, 155, 120));

	infoJoueur.add(new JLabel("Nom du Joueur: " + j.getNomJoueur()));
	infoJoueur.add(new JLabel("Argent: " + j.getCash()));
	infoJoueur.add(new JLabel("Position Courante: " + j.getPositionCourante().getNumero()));
	infoJoueur.add(new JLabel("Cartes Sortie de Prison: " + j.getCarteLibPrison()));
	infoJoueur.add(new JLabel("En prison: " + ((j.getPrison() == 0) ? "non" : "oui")));

	//----------vvv----logo-joueur----vvv
	String nomImage = j.getPion().toString();

	String path = ("src//Image//Pions//" + nomImage + ".png");

	ImageIcon icon = new ImageIcon(path);
	Image img = icon.getImage();
	BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	Graphics g = bi.createGraphics();
	g.drawImage(img, 0, 0, 20, 14, null);
	ImageIcon newIcon = new ImageIcon(bi);

	panelInfoJoueur.add(new JLabel(newIcon), BorderLayout.CENTER);
	//----------^^^----logo-joueur----^^^

	panelInfoJoueur.add(infoJoueur, BorderLayout.NORTH);

	JPanel propJoueur = new JPanel();
	propJoueur.setLayout(new BorderLayout());

	propJoueur.setBackground(new Color(124, 155, 120));

	propJoueur.add(new JLabel("Propriétés du joueur: "), BorderLayout.NORTH);
	model = new DefaultTableModel() {

	    @Override
	    public boolean isCellEditable(int row, int column) {
		return false;//This causes all cells to be not editable
	    }
	};
	JTable table = new JTable(model) { // met en couleur de fond de case la couleur du groupe corespondant
	    @Override
	    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex,
		    int columnIndex) {
		JComponent component = (JComponent) super.prepareRenderer(renderer, rowIndex, columnIndex);

		for (Biens_achetables bien : j.getPropriétés()) {
		    if (bien.getNomCarreau().equals(getValueAt(rowIndex, 1).toString())) {
			String sCouleur = "default";
			if (bien.getClass().getSimpleName().equals("ProprieteAConstruire")){
			    sCouleur = ((ProprieteAConstruire)bien).getGroupe().getCouleur().toString();
			}
			Color colorGrp;

			switch (sCouleur) {
			    case "bleuFonce":
				colorGrp = new Color(1, 104, 181);
				break;
			    case "orange":
				colorGrp = new Color(245, 144, 2);
				break;
			    case "mauve":
				colorGrp = new Color(212, 115, 212);
				break;
			    case "violet":
				colorGrp = new Color(127, 72, 140);
				break;
			    case "bleuCiel":
				colorGrp = new Color(146, 211, 244);
				break;
			    case "jaune":
				colorGrp = new Color(255, 236, 1);
				break;
			    case "vert":
				colorGrp = new Color(31, 165, 76);
				break;
			    case "rouge":
				colorGrp = new Color(229, 2, 19);
				break;
			    default:
				colorGrp = new Color(204, 227, 199);
				break;
			}
			component.setBackground(colorGrp);
		    }
		}
		return component;
	    }
	};

	table.setBackground(new Color(204, 227, 199));
	table.getTableHeader().setBackground(new Color(219, 236, 215));

	String[] entetes = {"Numéro", "Nom"};
	model.setColumnIdentifiers(entetes);

	String[] valeur = new String[2];

	for (Biens_achetables bien : j.getPropriétés()) {
	    valeur[0] = Integer.toString(bien.getNumero());
	    valeur[1] = bien.getNomCarreau();
	    model.addRow(valeur);

	    String couleur;
	    if (bien.getClass().getSimpleName().equals("ProprieteAConstruire")) {
		couleur = ((ProprieteAConstruire) bien).getGroupe().getCouleur().toString();
	    } else {
		couleur = "default";
	    }
	}

	propJoueur.add(table.getTableHeader(), BorderLayout.CENTER);
	propJoueur.add(table, BorderLayout.SOUTH);

	JPanel consJoueur = new JPanel();
	consJoueur.setLayout(new BorderLayout());

	consJoueur.setBackground(new Color(124, 155, 120));

	consJoueur.add(new JLabel("Constructions du joueur: "), BorderLayout.NORTH);
	model2 = new DefaultTableModel() {

	    @Override
	    public boolean isCellEditable(int row, int column) {
		return false;//This causes all cells to be not editable
	    }
	};
	JTable table2 = new JTable(model2);

	table2.setBackground(new Color(204, 227, 199));
	table2.getTableHeader().setBackground(new Color(219, 236, 215));

	String[] entetes2 = {"Nom", "Type", "Nombre"};
	model2.setColumnIdentifiers(entetes2);

	String[] valeur2 = new String[3];

	for (Biens_achetables bien : j.getPropriétés()) {
	    if (bien.getClass().getSimpleName().contains("ProprieteAConstruire")) {
		if (((ProprieteAConstruire) bien).getNbConstructions() != 0) {
		    valeur2[0] = bien.getNomCarreau();
		    valeur2[1] = ((ProprieteAConstruire) bien).getConstructions().get(0).getType();
		    valeur2[2] = Integer.toString(((ProprieteAConstruire) bien).getConstructions().size());
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

    public JPanel deAnimation() {
	JPanel animation = new JPanel(new BorderLayout());
	Icon icon = new ImageIcon("src/Image/animationDes.gif");

	animation.setBackground(new Color(124, 155, 120));

	JLabel label = new JLabel(icon);
	animation.add(label, BorderLayout.CENTER);
	animation.add(new JLabel(""), BorderLayout.NORTH);
	return animation;
    }

    public JPanel communication(String type, Object[] data) {
	JPanel communication = new JPanel(new BorderLayout());

	communication.setBackground(new Color(124, 155, 120));

	communication.setPreferredSize(new Dimension(communication.getSize().width, 50));
	if (type == "Affichage") {
	    JLabel label = new JLabel((String) data[0], JLabel.CENTER);
	    communication.add(label, BorderLayout.CENTER);
	} else if (type == "DemandePrison") {
	    JLabel label = new JLabel((String) data[0], JLabel.CENTER);
	    JButton buttonYes = new JButton("Utiliser la carte");
	    buttonYes.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    ((AutreCarreau) (((ControleurGraphique) data[1]).getJoueurCourant().getPositionCourante())).libPrisonCarte(((ControleurGraphique) data[1]).getJoueurCourant());
		}
	    });
	    JButton buttonNo = new JButton("Rester en prison");
	    buttonYes.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    ((AutreCarreau) (((ControleurGraphique) data[1]).getJoueurCourant().getPositionCourante())).purgerPeine(((ControleurGraphique) data[1]).getJoueurCourant());
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
