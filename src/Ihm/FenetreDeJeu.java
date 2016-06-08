package Ihm;

import Jeu.Controleur;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FenetreDeJeu {

    private JFrame frame = new JFrame();
    private JButton lancerDe;
    private JButton acheter;
    private JButton finDuTour;

    private Plateau pl;

    public FenetreDeJeu(Controleur controleur) {
	pl = new Plateau(controleur.getMonopoly().getJoueurs());
	frame = new JFrame();
	frame.setTitle("Partie de Monopoly");
	frame.setSize(900, 800);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	frame.add(pl);
	frame.setVisible(true);
    }
}
