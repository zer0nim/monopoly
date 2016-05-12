package Ihm;

import Data.*;
import java.util.Scanner;

public class Ihm {
    
     
    public static void affBiens_achetables(Biens_achetables ba){
	System.out.println("Nom: " + ba.getNomCarreau());
	System.out.println("Groupe: " + ba.getGroupe());
	System.out.println("Prix d'Achat: " + ba.getPrixAchat());
	System.out.println("Prix de Passage: " + ba.getPrixPassage());
	System.out.println("Propriétaire: " + ba.getPropriétaire());
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
