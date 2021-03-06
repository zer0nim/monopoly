package Jeu;

import Ihm.*;
import Data.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class ControleurGraphique {
    private Monopoly monopoly;
    private Joueur jCourant;
    private boolean appuye;
    private LancementJeu interfacee;
    private int resultD;
    private int resultD2;
    private boolean test = false;
    
    public ControleurGraphique(LancementJeu inter){
	this.monopoly = new Monopoly(this);
        this.interfacee =  inter;
    }
    
    public void lancerDes(){
        resultD = lancerDésAvancer(jCourant);
	resultD2 = lancerDésAvancer(jCourant);
    }
    public void jouerUnCoup() {
        if(test){
            test = false;
            /*for(Carreau car : getMonopoly().getCarreaux()){
                if(car.getClass().getSimpleName().equals("ProprieteAConstruire")){
                    jCourant.addPropriété((Biens_achetables)car);
                    ((Biens_achetables)car).setPropriétaire(jCourant);
                }
            }*/
            jCourant.setPositionCourante(monopoly.getCarreaux().get(10));
            jCourant.setEnPrison(3);
            interfacee.getFenetre().setEnabledButton(new Integer[]{0,0,0,1});
            jCourant.setCarteLibPrison(1);
        }
	if (!jCourant.estMort()){
	    resultD += resultD2;
            if(jCourant.getPrison() != 0 && resultD == 2 * resultD2){
                jCourant.setEnPrison(0);
                setCom("Affichage", new Object[]{jCourant.getNomJoueur() + " vient de sortir de prison avec un double dé.", true});
                jCourant.setPositionCourante(monopoly.getCarreaux().get(((jCourant.getPositionCourante().getNumero() + resultD)-1)%40));
            }
	    //affJoueur(jCourant);
	    actionCarreau(jCourant, resultD);
            getInterfacee().getFenetre().setInfosJoueurs(this);
            construire(jCourant);
            if(jCourant.getPrison() == -1){
                IhmText.Afficher("Il est libéré");
                jCourant.setEnPrison(0);
                jCourant.setPositionCourante(monopoly.getCarreaux().get(((jCourant.getPositionCourante().getNumero() + resultD)-1)%40));
                actionCarreau(jCourant, resultD);
                construire(jCourant);
            }
	}
	if (jCourant.estMort()){ //pas de else il est peut etre mort en jouant
		jCourant.vendrePropriétés();
		
		String nomJMort = jCourant.getNomJoueur();
		JOptionPane winPane = new JOptionPane();
		winPane.showMessageDialog(null, ("Le Joueur " + nomJMort + " est Mort !"), "Décès", JOptionPane.INFORMATION_MESSAGE);
                monopoly.removeJoueur(jCourant);
                if(monopoly.getJoueurs().size() == 1){
                    String nomWinner = monopoly.getJoueurs().get(0).getNomJoueur();
                    //winPane.showMessageDialog(null, ("Le Joueur " + nomWinner + " a Gagné !"), "Fin du Jeu", JOptionPane.INFORMATION_MESSAGE);
                    String options[] = {"Recommencer une partie", "Quitter le jeu"};
                    int retour = winPane.showOptionDialog(null, ("Le Joueur " + nomWinner + " a Gagné !"), "Fin du Jeu",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, null);
                             if( retour==JOptionPane.CLOSED_OPTION || retour == 1){
                                 System.exit(0);
                             }else{
                                 interfacee.getFenetre().closeFrame();
                                 LancementJeu ihmf = new LancementJeu();
                             }
                }
	}
        interfacee.getFenetre().setInfosJoueurs(this);
	if (resultD == 2 * resultD2){ //si double
            jCourant.incrementCompteDoubleDes();
            if (jCourant.getCompteDoubleDes() == 3) {
                setCom("Affichage",new Object[]{jCourant.getNomJoueur()+ " : Vous avez fait 3 doubles dés à la suite. Allez en prison.", true});
                jCourant.setPositionCourante(monopoly.getCarreaux().get(10));
                jCourant.setEnPrison(3);
                jCourant.resetCompteDoubleDes();
                interfacee.getFenetre().setEnabledButton(new Integer[]{0,0,0,1});
            } else {
                setCom("Affichage",new Object[]{jCourant.getNomJoueur()+ " : Vous avez fait un double dé. Vous pouvez jouer un nouveau tour.", true});
                interfacee.getFenetre().setEnabledButton(new Integer[]{1,-1,-1,0});
            }
	} else {
            jCourant.resetCompteDoubleDes();
        }
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


        
    }

    public void actionCarreau(Joueur j, int resultD){
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
        if(j.getPrison() == 0){
            j.setPositionCourante(monopoly.getCarreaux().get(((j.getPositionCourante().getNumero() + resultD)-1)%40));
        }
        if (j.getPositionCourante().getNumero() < ancPos) { //si ça njCourantouvelle position est inférieur à la nouvelle
            setCom("Affichage",new Object[]{jCourant.getNomJoueur()+ " : Vous venez de passer par la case départ. Vous gagnez donc 200€.", true});
            j.recevoirArgent(200); // on ajoute 200 de cash, car il est donc passé par le départ
        }
	return(resultD);
    }
    
    public int LancerDeN(int n){
	Random rand = new Random();
        return rand.nextInt(n)+1; //retourne un entier correspondant à un lancé de dé
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
    public void construire(Joueur j){
        ArrayList<Integer> comptes = setCompteConstructions();
        boolean maisons = false;
        boolean hotels = false;
        if(comptes.get(0) < 32){
            maisons = true;
        }
        if(comptes.get(1) < 12){
            hotels = true;
        }
        j.achetterConstruction(maisons, hotels);
    }

    //v--getters setters--v

    public Monopoly getMonopoly() {
        return monopoly;
    }

    public void setMonopoly(Monopoly monopoly) {
        this.monopoly = monopoly;
    }
    
    public ArrayList<Integer> setCompteConstructions(){
        int compteMaison = 0;
        int compteHotel = 0;
        for(Joueur j : monopoly.getJoueurs()){
            for(Biens_achetables prop : j.getPropriétés()){
                if(prop.getClass().getSimpleName().contains("ProprieteAConstruire")){
                    for(Construction cons : ((ProprieteAConstruire)prop).getConstructions()){
                        if(cons.getType() == "maison"){
                            compteMaison+= 1;
                        }else{
                            compteHotel+= 1;
                        }
                    }
                }
            }
        }
        ArrayList<Integer> comptes = new ArrayList<>();
        comptes.add(compteMaison);
        comptes.add(compteHotel);
        return comptes;
    }
    
    
    
    public void setJoueurCourant(Joueur j){
        jCourant = j;
    }

    public boolean isAppuye() {
        return appuye;
    }

    public void setAppuye(boolean appuye) {
        this.appuye = appuye;
    }
    
    public void setJoueurSuivant(Joueur j){
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
        jCourant = joueur;
    }
    
    public Joueur getJoueurCourant(){
        return jCourant;
    }

    /**
     * @return the interfacee
     */
    public LancementJeu getInterfacee() {
        return interfacee;
    }
    
    public void setAnimationDeVisible(){
        getInterfacee().getFenetre().setResultatDe(new JPanel(), false);
    }
    
    public void setAnimationDe(){
        JPanel de = new JPanel(new BorderLayout());

	de.setBackground(new Color(124,155,120));

        JLabel resultat = new JLabel(Integer.toString(resultD) + " + " + Integer.toString(resultD2) + " = " + Integer.toString(resultD + resultD2), JLabel.CENTER);
        resultat.setFont(new Font(resultat.getFont().getName(), resultat.getFont().getStyle(), 30));
        de.add(resultat, BorderLayout.CENTER);
        getInterfacee().getFenetre().setResultatDe(de, true);
    }
    
    public void setCom(String type, Object[] data){
        interfacee.getFenetre().setCommunication(type, data, this);
    }
    
    public int getResultatD(){
        return resultD;
    }
}
