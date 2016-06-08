package Ihm;

import javax.swing.*;

public class IhmNbJoueur {

    int nbJoueurs = 0;

    public static int afficherBoiteDialogue() {

	String[] list = {"2", "3", "4", "5", "6"};
	JComboBox jcb = new JComboBox(list);
	JOptionPane.showMessageDialog(null, jcb, "Nombre de joueurs", JOptionPane.QUESTION_MESSAGE);

	return (Integer.valueOf((String) jcb.getSelectedItem()));
    }
}
