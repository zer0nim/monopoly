package Ihm;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class IhmNbJoueur {
    int nbJoueurs = 0;

    public int afficherBoiteDialogue() {

	String[] list = {"2", "3", "4", "5", "6"};
	JComboBox jcb = new JComboBox(list);
	JOptionPane.showMessageDialog( null, jcb, "Nombre de joueurs", JOptionPane.QUESTION_MESSAGE);	

	return(Integer.valueOf((String)jcb.getSelectedItem()));
    }
}
