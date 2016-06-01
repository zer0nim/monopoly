package Data;

import java.util.ArrayList;

public class AutreCarreau extends Carreau{

    public AutreCarreau(int numero, String nomCarreau, int info) {
	super(numero, nomCarreau);
    }
    
    public void action(Joueur j, ArrayList<Joueur> joueurs, ArrayList<Carreau> carreaux, ChanceCommu cartesChCo){
	
	switch (getNomCarreau()) {
	    case "Chance":
		
		System.out.print("A Faire ! Action Chance");
		break;
	    case "Caisse de Communauté":
		System.out.print("A Faire ! Action Caisse de Communauté");
		break;
	    case "Départ":
		System.out.print("A Faire ! Action Départ");
		break;
	    case "Impôt sur le revenu":
		System.out.print("A Faire ! Action Impôt sur le revenu");
		break;
	    case "Simple Visite / En Prison":
		System.out.print("A Faire ! Action Simple Visite / En Prison");
		break;
	    case "Parc Gratuit":
		System.out.print("A Faire ! Action Parc Gratuit");
		break;
	    case "Allez en prison":
		System.out.print("A Faire ! Action Allez en prison");
		break;
	    case "Taxe de Luxe":
		System.out.print("A Faire ! Action Taxe de Luxe");
		break;
	    default:
		break;
	}
	

	
    }
    

    
    //v--getters setters--v

}
