package Ihm;

import Data.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ihm {
    public static void affJoueur(Joueur j){
	System.out.println("------------------Tour--------------------");
	System.out.println("------------------------------------------");

	System.out.println("Joueur: " + j.getNomJoueur());
	System.out.println("Argent: " + j.getCash());
	System.out.println("Position Courante: ");
	Ihm.affBiens(j.getPositionCourante());
    }
	
    public static void affBiens(Carreau c){
	if(c.getClass().getSimpleName().equals("ProprieteAConstruire") || c.getClass().getSimpleName().equals("Gare") || c.getClass().getSimpleName().equals("Compagnie"))
	    affBiens_achetables((Biens_achetables)c);
	else
	    affAutreCarreau((AutreCarreau)c);
    }
    
    public static void affBiens_achetables(Biens_achetables ba){
	System.out.println("-------");
	System.out.println("Numéro: " + ba.getNumero());
	System.out.println("Nom: " + ba.getNomCarreau());
	if(ba.getClass().getSimpleName().equals("ProprieteAConstruire"))
            affGroupe((ProprieteAConstruire)ba);
	System.out.println("Prix d'Achat: " + ba.getPrixAchat());
	if (ba.getPropriétaire() != null)
	    System.out.println("Propriétaire: " + ba.getPropriétaire().getNomJoueur() + "\n");
	System.out.println("-------");
    }
    
    public static void affAutreCarreau(AutreCarreau ac){
	System.out.println("-------");
	System.out.println("Numéro: " + ac.getNumero());
	System.out.println("Nom: " + ac.getNomCarreau() + "\n");
	System.out.println("-------");
    }
    
    public static void affGroupe(ProprieteAConstruire p){
    	System.out.println("Groupe: " + p.getGroupe().getCouleur().toString());
    }

    public static void Cimetiere(Joueur j){
	System.out.println("----------------MORT----------------------------");
	System.out.println("------------------------------------------------");
    	System.out.println("Le Joueur " + j.getNomJoueur() + " est mort ! :(");
	System.out.println("------------------------------------------------");
	System.out.println("------------------------------------------------");
    }
    
    
    public static boolean propositionAchat(Joueur j, Biens_achetables ba){
	Scanner sc = new Scanner(System.in);
	System.out.println("Souhaitez vous acheter ?(oui/non): ");
	String rep = sc.nextLine();
	while(!rep.equals("oui") && !rep.equals("non")){
	    System.out.println("Saisie incorecte !");
	    System.out.println("Souhaitez vous acheter ?(oui/non): ");
	    rep = sc.nextLine();
	}
	return(rep.equals("oui"));
    }
    
    
    
    public static int nbJoueur(){
	Scanner sc = new Scanner(System.in);
	System.out.print("Inscrire le nombre de joueur : ");
	int c = sc.nextInt();
	while (c > 6 || c < 2){
	    System.out.println("Erreur : Nombre de joueur incorrect");
	    System.out.print("Inscrire le nombre de joueur (2/6) : ");
	    c = sc.nextInt();
	}	

	return (c);
    }
    
    public static String nomJoueur(int numero){ // Récupérer le nom du joueur
	Scanner sc = new Scanner(System.in);
	System.out.print("Choisir le nom du joueur " + numero + ": ");
	return sc.nextLine();
    }
    
    public static void Afficher(String msg){
	System.out.println(msg);
    }
}
