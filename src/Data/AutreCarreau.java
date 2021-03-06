package Data;

import java.util.ArrayList;
import Ihm.*;
import Jeu.ControleurGraphique;

public class AutreCarreau extends Carreau {

    private int info;
    private ControleurGraphique controleur;

    public AutreCarreau(int numero, String nomCarreau, int info, ControleurGraphique controleur) {
	super(numero, nomCarreau);
        this.controleur = controleur;
        this.info = info;
    }

    public void action(Joueur j, ArrayList<Joueur> joueurs, ArrayList<Carreau> carreaux, ChanceCommu cartesChCo) {
	switch (getNomCarreau()) {
	    case "Chance": //Si on tombe sur la case, carte chance
		cartesChCo.piocherCarteChance().Action(j, joueurs, carreaux, controleur);
		break;
	    case "Caisse de Communauté": //Si on tombe sur la case, carte communauté
		cartesChCo.piocherCarteCommu().Action(j, joueurs, carreaux, controleur);
		break;
	    case "Départ": //Si on tombe sur la case départ, ne rien faire
		break;
	    case "Impôt sur le revenu": //Si on tombe sur la case "Impôt sur le revenu"
		j.setCash(j.getCash() + getInfo());
                controleur.setCom("Affichage", new Object[]{j.getNomJoueur()+ " : Vous payez l'impôt sur le revenu, vous perdez donc 200€", true});
		break;
	    case "Simple Visite / En Prison": //Case Prison ou Visite
                if(j.getPrison() > 1){
                    if(j.getCarteLibPrison() != 0){
                        controleur.getInterfacee().getFenetre().setEnabledButton(new Integer[]{-1,0,-1,0});
                        controleur.setCom("DemandePrison", new Object[]{j.getNomJoueur()+ " : Vous êtes en prison pour encore " + j.getPrison() + " tours. Vous disposez d'une carte \"Vous êtes libéré de Prison\".", controleur});
                    }else{
                       purgerPeine(j); 
                    }
                }else if(j.getPrison() == 1){ //POur son dernier tour il paye une amende de 50$
                    j.setCash(j.getCash()-50);
                    j.setEnPrison(-1);
                    controleur.setCom("Affichage", new Object[]{j.getNomJoueur()+ " : Vous avez fini votre de peine de prison. Vous avez payé 50€ d'amande. Vous jouez votre tour :", true});
                }else{
                    controleur.setCom("Affichage", new Object[]{j.getNomJoueur()+ " : Vous participez à une visite de la prison.", true});
                }
		break;
	    case "Parc Gratuit":
                controleur.setCom("Affichage", new Object[]{j.getNomJoueur()+ " : Vous êtes au parc gratuit. Reposez-vous en attendant le prochain tour.", true});
		//j.setCash(j.getCash() + this.info);
		//this.setInfo(0);
		break;
	    case "Allez en prison": //Si on tombe sur "Allez en Prison, on est immédiatement positionné dessus
		j.setPositionCourante(carreaux.get(10));//récupère le carreau Prison
		j.setEnPrison(3); //Bloque le joueur en prison pendant 3 tours
		controleur.setCom("Affichage", new Object[]{j.getNomJoueur()+ " : Vous avez été envoyé en prison. Vous devez y rester 3 tours.", true});
		break;
	    case "Taxe de Luxe": //Enlève la somme getInfo() de la case "Taxe de Luxe"
		j.setCash(j.getCash() + getInfo());
                controleur.setCom("Affichage", new Object[]{j.getNomJoueur()+ " : Vous payez la taxe de Luxe, vous perdez donc 100€", true});
                break;
	    default:
		break;
	}
        controleur.getInterfacee().getFenetre().setEnabledButton(new Integer[]{-1,0,-1,1});
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
    
    public void libPrisonCarte(Joueur j){
        j.setEnPrison(0);
        j.setCarteLibPrison(j.getCarteLibPrison() - 1);
        controleur.getInterfacee().getFenetre().setEnabledButton(new Integer[]{-1,0,-1,0});
        controleur.setCom("Affichage", new Object[]{j.getNomJoueur()+ " : Vous êtes sorti de prison avec votre carte \"Vous êtes libéré de Prison\". Vous jouez votre tour :", true});
        j.setPositionCourante(controleur.getMonopoly().getCarreaux().get(((j.getPositionCourante().getNumero() + controleur.getResultatD())-1)%40));
        controleur.actionCarreau(controleur.getJoueurCourant(), controleur.getResultatD());
    }
    
    public void purgerPeine(Joueur j){
            j.setEnPrison(j.getPrison()-1);
            controleur.getInterfacee().getFenetre().setEnabledButton(new Integer[]{-1,0,-1,1});
            controleur.setCom("Affichage", new Object[]{j.getNomJoueur()+ " : Vous êtes en prison pour encore " + Integer.toString(j.getPrison()) + " tours.", true});
    }
}
