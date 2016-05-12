package Ihm;

import Data.*;
import java.util.Scanner;

public class Ihm {
    
    
    public static void affBiens(Carreau c){
	if(c.getClass().getSimpleName().equals("ProprieteAConstruire") || c.getClass().getSimpleName().equals("Gare") || c.getClass().getSimpleName().equals("Compagnie"))
	    affBiens_achetables((Biens_achetables)c);
    }
    
    public static void affBiens_achetables(Biens_achetables ba){
	System.out.println("------------------------------------------");
	System.out.println("Nom: " + ba.getNomCarreau());
	if(ba.getGroupe() != null)
	    System.out.println("Groupe: " + ba.getGroupe().getCouleur().toString());
	System.out.println("Prix d'Achat: " + ba.getPrixAchat());
	System.out.println("Propriétaire: " + ba.getPropriétaire() + "\n");
    }
    
    public static void affAutreCarreau(AutreCarreau ac){
	    System.out.println("AutreCarreau");
	    //A COMPLETER
    }
    
    
    
    public static boolean propositionAchat(Joueur j, Biens_achetables ba){
	Scanner sc = new Scanner(System.in);
	
	affBiens_achetables(ba);

	System.out.println("Souhaitez vous acheter ?(o/n): ");
	String rep = sc.nextLine();
	while(!rep.equals("o") || !rep.equals("n")){
	    System.out.println("Saisie incorecte !");
	    System.out.println("Souhaitez vous acheter ?(o/n): ");
	    rep = sc.nextLine();
	}
	return(rep.equals("o"));
    }
    
    
}
