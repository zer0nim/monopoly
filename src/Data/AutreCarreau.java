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
		break;
	    case "Impôt sur le revenu":
		j.setCash(j.getCash() + getInfo());
		break;
            case "Simple Visite / En Prison":
                if(j.getPrison() > 1){
                    if(j.getCarteLibPrison() != 0){
                        if(Ihm.demanderCartePrison()){
                            j.setEnPrison(0);
                            Ihm.Afficher("Vous venez de sortir de prison avec votre carte \"Vous êtes libéré de Prison\".");
                        }
                    }
                    if(j.getPrison() != 0){
                        j.setEnPrison(j.getPrison()-1);
                        Ihm.Afficher(j.getNomJoueur() + " est en prison. Il lui reste " + j.getPrison() + " tour(s) en prison.");
                    }
                }else if(j.getPrison() == 1){
                    j.setCash(j.getCash()-50);
                    j.setEnPrison(-1);
                    Ihm.Afficher("Vous avez fini votre peine de prison. Vous venez de payer une amende de 50$");
                }
		break;
	    case "Parc Gratuit":
		//j.setCash(j.getCash() + this.info);
                //this.setInfo(0);
		break;
	    case "Allez en prison":
                j.setPositionCourante(carreaux.get(10));
                j.setEnPrison(3);
                Ihm.Afficher(j.getNomJoueur() + " est en prison. Il lui reste " + j.getPrison() + " tour(s) en prison.");
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
