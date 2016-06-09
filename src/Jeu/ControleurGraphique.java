package Jeu;
import Ihm.*;
import Data.*;
import static Ihm.Ihm.affJoueur;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControleurGraphique {
    private Monopoly monopoly;
    private Joueur jCourant;
    private boolean appuye;
    private Interface interfacee;
    private int resultD;
    private int resultD2;
    
    public ControleurGraphique(Interface inter){
	this.monopoly = new Monopoly();
        this.interfacee =  inter;
    }

    public void jouerUnCoup() {
        //Ihm.Afficher("");
	resultD = lancerDésAvancer(jCourant);
	resultD2 = lancerDésAvancer(jCourant);
        //int resultD = 2;
        //int resultD2 = 2;
	/*if (!jCourant.estMort()){
	    resultD += resultD2;
            if(jCourant.getPrison() != 0 && resultD == 2 * resultD2){
                jCourant.setEnPrison(0);
                Ihm.Afficher("Vous venez de sortir de prison avec un double dé !");
                jCourant.setPositionCourante(monopoly.getCarreaux().get(((jCourant.getPositionCourante().getNumero() + resultD)-1)%40));
            }
	    affJoueur(jCourant);
	    actionCarreau(jCourant, resultD);
            jCourant.achetterConstruction(true);
            
            if(jCourant.getPrison() == -1){
                jCourant.setEnPrison(0);
                Ihm.Afficher("résultat lancé du dé: " + (resultD - resultD2));
                Ihm.Afficher("résultat lancé du dé: " + resultD2);
                jCourant.setPositionCourante(monopoly.getCarreaux().get(((jCourant.getPositionCourante().getNumero() + resultD)-1)%40));
                affJoueur(jCourant);
                actionCarreau(jCourant, resultD);
                jCourant.achetterConstruction(true);
            }
	}
	if (jCourant.estMort()){ //pas de else il est peut etre mort en jouant
		jCourant.vendrePropriétés();
	}
	if (resultD == 2 * resultD2){ //si double
	    Ihm.Afficher("Double au Dé !");
            jCourant.incrementCompteDoubleDes();
            if (jCourant.getCompteDoubleDes() == 3) {
                jCourant.setPositionCourante(monopoly.getCarreaux().get(10));
                jCourant.setEnPrison(3);
                Ihm.Afficher(jCourant.getNomJoueur() + " est en prison. Il lui reste " + jCourant.getPrison() + " tour(s) en prison.");
                jCourant.resetCompteDoubleDes();
            } else {
                jouerUnCoup();
            }
	} else {
            jCourant.resetCompteDoubleDes();
        }*/
	/*
	System.out.println("\nFin du tour, appuyer sur entrer pour continuer");

	Scanner input = new Scanner(System.in);
	String s = null;
	while ( true )
	{
	   s = input.nextLine();
	   if( !s.equals("\\n") ) 
	      break;
	}*/
        jCourant = setJoueurSuivant(jCourant);
        getInterfacee().getFenetre().ControlDesTours(this);
        
    }

    private void actionCarreau(Joueur j, int resultD){
        if (j.getPositionCourante().getClass().getSimpleName().equals("Gare"))
		((Gare)j.getPositionCourante()).action(j, resultD);
	    else if (j.getPositionCourante().getClass().getSimpleName().equals("Compagnie"))
		((Compagnie)j.getPositionCourante()).action(j, resultD);
	    else if (j.getPositionCourante().getClass().getSimpleName().equals("ProprieteAConstruire"))
		((ProprieteAConstruire)j.getPositionCourante()).action(j, resultD);
	    else
		((AutreCarreau)j.getPositionCourante()).action(j, monopoly.getJoueurs(), monopoly.getCarreaux(), monopoly.getCartesChCo());
    }
    
    private int lancerDésAvancer(Joueur j){
	int ancPos = j.getPositionCourante().getNumero();
        int resultD = LancerDeN(6);
        Ihm.Afficher("résultat lancé du dé: " + resultD);
        if(j.getPrison() >= 0){
            j.setPositionCourante(monopoly.getCarreaux().get(((j.getPositionCourante().getNumero() + resultD)-1)%40));
        }
        if (j.getPositionCourante().getNumero() < ancPos) { //si ça njCourantouvelle position est inférieur à la nouvelle
	    Ihm.Afficher(j.getNomJoueur() + " reçois son Salaire (case départ) sa position etait: " + j.getPositionCourante().getNumero());
            j.recevoirArgent(200); // on ajoute 200 de cash, car il est donc passé par le départ
        }
	return(resultD);
    }
    
    public int LancerDeN(int n){
	Random rand = new Random();
        return rand.nextInt(n)+1; //retourne un entier correspondant à un lancé de dé
    } 
    
    public void creerJoueurs(){
	int nbJoueur = Ihm.nbJoueur();
	for (int j = 0; j < nbJoueur ; j++) {
		monopoly.setJoueur(new Joueur(Ihm.nomJoueur(j+1), monopoly.getCarreaux().get(0)));
	}
	quiCommence();
    }
    
    public void quiCommence(){
	Joueur j = null;
	int resDe, ancRes = 0;
	ArrayList<Joueur> njoueurs = new ArrayList<>();
	ArrayList<Joueur> njoueursMemeScore = new ArrayList<>();

	for (Joueur jCourant : monopoly.getJoueurs()){
	    resDe = LancerDeN(6);
	    resDe += LancerDeN(6);
	    if (resDe >= ancRes){
		if (resDe == ancRes){
		    njoueurs.add(jCourant);
		}
		else{
		    njoueurs.clear();
		    njoueurs.add(jCourant);
		}
		ancRes = resDe;
	    }
	}
	while (njoueurs.size() > 1){
	    ancRes = 0;
	    njoueursMemeScore = (ArrayList<Joueur>)njoueurs.clone();
	    njoueurs.clear();
	    for (Joueur jCourant : njoueursMemeScore){
		resDe = LancerDeN(6);
		resDe += LancerDeN(6);
		if (resDe >= ancRes){
		    if (resDe == ancRes){
			njoueurs.add(jCourant);
		    }
		    else{
			njoueurs.clear();
			njoueurs.add(jCourant);
		    }
		    ancRes = resDe;
		}
	    }
	}
	j = njoueurs.get(0);
	njoueurs.clear();
	int i = 0;
	for (Joueur jCourant : monopoly.getJoueurs()){
		njoueurs.add(monopoly.getJoueurs().get(     (monopoly.getJoueurs().indexOf(j) + i) % monopoly.getJoueurs().size()       ));
		i++;
	}
	monopoly.setJoueurs(njoueurs);
    }
    

    //v--getters setters--v

    public Monopoly getMonopoly() {
        return monopoly;
    }

    public void setMonopoly(Monopoly monopoly) {
        this.monopoly = monopoly;
    }
    
    public void setJoueurCourant(Joueur j){
        jCourant = j;
    }

    /**
     * @return the appuye
     */
    public boolean isAppuye() {
        return appuye;
    }

    /**
     * @param appuye the appuye to set
     */
    public void setAppuye(boolean appuye) {
        this.appuye = appuye;
    }
    
    public Joueur setJoueurSuivant(Joueur j){
        Joueur joueur = monopoly.getJoueurs().get(0);
        for(int i = 0; i < monopoly.getJoueurs().size(); i++){
            if(monopoly.getJoueurs().get(i).equals(j)){
                if(i == monopoly.getJoueurs().size() - 1){
                    joueur = monopoly.getJoueurs().get(0);
                }else{
                    joueur = monopoly.getJoueurs().get(i+1);
                }
            }
        }
        return joueur;
    }
    
    public Joueur getJoueurCourant(){
        return jCourant;
    }

    /**
     * @return the interfacee
     */
    public Interface getInterfacee() {
        return interfacee;
    }
    
    public void setAnimationDeVisible(boolean visible){
        getInterfacee().getFenetre().getAnimationDe().setVisible(visible);
    }
    
    public void setAnimationDe(){
        JPanel de = new JPanel(new BorderLayout());
        JLabel resultat1 = new JLabel(Integer.toString(resultD-resultD2));
        JLabel resultat2 = new JLabel(Integer.toString(resultD2));
        JLabel plus = new JLabel("+");
        de.add(resultat1, BorderLayout.EAST);
        de.add(plus, BorderLayout.CENTER);
        de.add(resultat2, BorderLayout.WEST);
        getInterfacee().getFenetre().setAnimationDe(de);
    }
}