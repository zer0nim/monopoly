package Ihm;

import javax.swing.*;

public class IhmNbJoueur {

    int nbJoueurs = 0;

    public static int afficherBoiteDialogue() {

	String[] list = {"2", "3", "4", "5", "6"}; //choix du nombre de joueur
	JComboBox jcb = new JComboBox(list); //Liste de sélection du nombre de joueur
	JOptionPane.showMessageDialog(null, jcb, "Nombre de joueurs", JOptionPane.QUESTION_MESSAGE); //création de la fenêtre de choix des joueurs
	return (Integer.valueOf((String) jcb.getSelectedItem())); //retourne le choix du nombre de joueur
    }
}
