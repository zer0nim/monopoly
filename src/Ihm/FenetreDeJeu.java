package Ihm;

import Jeu.Controleur;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetreDeJeu {

    private JFrame frame = new JFrame();
    private JButton lancerDe;
    private JButton acheter;
    private JButton finDuTour;

    JPanel panelPrincipal;
    private Plateau pl;

    public FenetreDeJeu(Controleur controleur) {
	pl = new Plateau();
	frame = new JFrame();
	frame.setTitle("Partie de Monopoly");
	frame.setSize(900, 800);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	panelPrincipal = new JPanel();
	panelPrincipal.setLayout(new BorderLayout());
	
	panelPrincipal.add(pl.Bouton(), BorderLayout.SOUTH);
	
	panelPrincipal.add(pl.TabJoueur(controleur.getMonopoly().getJoueurs()), BorderLayout.SOUTH);
	
	frame.add(pl);
	frame.setVisible(true);
    }
}
