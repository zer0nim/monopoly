package Data;

import java.util.ArrayList;
import Ihm.*;

public class AutreCarreau extends Carreau{
    private int info;

    public AutreCarreau(int numero, String nomCarreau, int info) {
	super(numero, nomCarreau);
    }
    
    public void action(Joueur j, ArrayList<Joueur> joueurs, ArrayList<Carreau> carreaux, ChanceCommu cartesChCo){
	switch (getNomCarreau()) {
	    case "Chance":
		cartesChCo.piocherCarteChance().Action(j, joueurs, carreaux);
		break;
	    case "Caisse de Communauté":
		cartesChCo.piocherCarteCommu().Action(j, joueurs, carreaux);
		break;
	    case "Départ":
		System.out.println("A Faire ! Action Départ");
		break;
	    case "Impôt sur le revenu":
		j.setCash(j.getCash() + getInfo());
		break;
	    case "Simple Visite / En Prison":
                System.out.println("A Faire ! Action Départ");
		break;
	    case "Parc Gratuit":
		//j.setCash(j.getCash() + this.info);
                //this.setInfo(0);
		break;
	    case "Allez en prison":
                for(Carreau c : carreaux){
                    if(c.getNomCarreau() == "Simple Visite / En Prison"){
                        j.setPositionCourante(c);
                    }
                }
                j.setEnPrison(3);
		break;
	    case "Taxe de Luxe":
                j.setCash(j.getCash() + getInfo());
		break;
	    default:
		break;
	}
	

	
    }
    

    
    //v--getters setters--v

    /**
     * @return the info
     */
    public int getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(int info) {
        this.info = info;
    }

    public void addInfo(int info) {
        this.info += info;
    }

}
