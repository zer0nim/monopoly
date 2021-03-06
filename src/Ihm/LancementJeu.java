package Ihm;

import Data.*;
import Jeu.ControleurGraphique;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

public class LancementJeu extends JPanel { //Fenetre de lancement du jeu (selection nbs joueurs, noms, pions)

    private JButton jouer;
    private JButton annuler;
    private ArrayList<JComboBox> champDePions;
    
    private ControleurGraphique controleur;
    private JFrame frame;
    private ArrayList<JTextField> champNomjoueurs;
    private int nbJoueur;
    
    private Enumeration.Pions ItemType;
    private FenetreDeJeu fenetre;

    public LancementJeu() {
	frame = new JFrame();
	frame.setTitle("Choix nom joueurs");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	nbJoueur = 0;

	controleur = new ControleurGraphique(this);
	champNomjoueurs = new ArrayList<>();
	champDePions = new ArrayList<>();

	setBackground(Color.white);
	initUIComponents();

	frame.add(this);

	frame.setSize(500, ResponsiveHeight());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

	frame.setVisible(true);
    }

    public static int afficherBoiteDialogue() {

	String[] list = {"2", "3", "4", "5", "6"}; //choix du nombre de joueur
	JComboBox jcb = new JComboBox(list); //Liste de sélection du nombre de joueur
	JOptionPane.showMessageDialog(null, jcb, "Nombre de joueurs", JOptionPane.QUESTION_MESSAGE);//création de la fenêtre de choix des joueurs
	return (Integer.valueOf((String) jcb.getSelectedItem())); //retourne le choix du nombre de joueur
    }
    
    private void initUIComponents() {
	this.setLayout(new BorderLayout());

	JLabel logo = new JLabel(new ImageIcon("src/Image/logo2.jpeg"));
	this.add(logo, BorderLayout.NORTH);

	JPanel lancement = new JPanel();
	lancement.setLayout(new GridLayout(1, 2));
	this.add(lancement, BorderLayout.SOUTH);

	jouer = new JButton("Jouer au monopoly");  //crée les joueurs et lance la partie
	lancement.add(jouer);
	jouer.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		int i = 0;
		ArrayList<Integer> indPionsSelect = new ArrayList<>();
		JComboBox jcb;
		
		jcb = champDePions.get(i);
		indPionsSelect.add(jcb.getSelectedIndex());
		
		while (champDePions.size()-1 > i && !indPionsSelect.contains( champDePions.get(i+1).getSelectedIndex() )) { //boucle de vérification pas deux fois le meme pion
		    i++; //incrémente i
		    
		    jcb = champDePions.get(i);
		    		    indPionsSelect.add(jcb.getSelectedIndex());

		}
		
		if (i == champDePions.size()-1){ //si pas deux fois le meme pion
		    
		    if (verifNoms(champNomjoueurs)){ //si noms ok
			i = 0;
			for (JComboBox jc : champDePions) {			
			    ((Joueur)(controleur.getMonopoly().getJoueurs().get(i))).setPion(ItemType.values()[jc.getSelectedIndex()] );
			    i++;
			}

			i = 0;
			for (JTextField jc : champNomjoueurs) {
			    controleur.getMonopoly().getJoueurs().get(i).setNomJoueur(jc.getText());
			    i++;
			}
			frame.setVisible(false);
			frame.dispose();
			fenetre = new FenetreDeJeu(controleur);
		    }
		    else{  //si noms pas ok
			JOptionPane.showMessageDialog(frame,"Les noms doivent être différents et non nuls !");
		    }
		}
		else{ //si deux fois le meme pion ne rien faire
		    JOptionPane.showMessageDialog(frame,"Des joueurs possedent le même pion !");
		}
	    }
	});

	annuler = new JButton("Quittez le jeu");
	lancement.add(annuler);
	annuler.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});

	nbJoueur = (afficherBoiteDialogue()); //demande a l'utilisateur le nombre de joueurs via un widgetS

	//------vvv---paneau de selection nom joueur---vvv
	JPanel ChoixInfoJoueurs = new JPanel();
	ChoixInfoJoueurs.setLayout(new GridLayout(nbJoueur, 1));

	for (int j = 0; j < nbJoueur; j++) {  //boucle pour ajouter les champs de saisie du nom de joueurs
	    JLabel prenom = new JLabel("Joueur :");
	    prenom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

	    Font font = new Font("Arial", Font.BOLD, 16); //Police d'écriture Arial en Gras et de taille 16
	    prenom.setFont(font);
	    ChoixInfoJoueurs.add(prenom);
	    
	    
	    champNomjoueurs.add(new JTextField(30)); //Champ de saisie des noms
	    ChoixInfoJoueurs.add(champNomjoueurs.get(champNomjoueurs.size() - 1));
	    
	    champDePions.add(new JComboBox(ItemType.values())); //Champ de selection des pions
	    ChoixInfoJoueurs.add(champDePions.get(champDePions.size() - 1));
	
	    controleur.getMonopoly().setJoueur(new Joueur("noName", controleur.getMonopoly().getCarreaux().get(0), controleur)); //crée un joueur avec nom par defaut
	}
	controleur.quiCommence(); //lance le dé pour savoir qui commence
	//------^^^---paneau de selection nom joueur---^^^

	this.add(ChoixInfoJoueurs, BorderLayout.CENTER); // ajout de ChoixNomJoueurs dans la fenetre

    }
    
    public boolean verifNoms(ArrayList<JTextField> champNomj){
	boolean estOk = true;
	for(JTextField texteCourant : champNomj){ //Pour chaque ChampNomj
	    if(texteCourant.getText().equals("")){ //Si un champ nom est vide
		estOk = false;
	    }
	}
	Set<String> set = new HashSet<>(); //permet de ne pas écrire les valeurs double
	for(JTextField texteCourant : champNomj){
	    set.add(texteCourant.getText());
	}
	
	if(set.size() < champNomj.size()){ //Si des joueurs on le même nom
	    estOk = false;
	}
	
	
	return(estOk);
    }
    
    public int ResponsiveHeight() { //permet de dimensionner la fenetre selon le nombre de joueurs
	return (nbJoueur * 45) + 200;
    }
    
    public FenetreDeJeu getFenetre(){
        return fenetre;
    }
}