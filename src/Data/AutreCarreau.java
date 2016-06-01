package Data;

import java.util.ArrayList;

public class AutreCarreau extends Carreau{
    private int info;

    public AutreCarreau(int numero, String nomCarreau, int info) {
	super(numero, nomCarreau);
    }
    
    public void action(Joueur j, ArrayList<Joueur> joueurs, ArrayList<Carreau> carreaux, ChanceCommu cartesChCo){
	switch (getNomCarreau()) {
	    case "Chance":
		System.out.println("A Faire ! Action Chance");
		break;
	    case "Caisse de Communauté":
		System.out.println("A Faire ! Action Caisse de Communauté");
		break;
	    case "Départ":
		System.out.println("A Faire ! Action Départ");
		break;
	    case "Impôt sur le revenu":
		for(Carreau c : carreaux){
                    if(c.getNomCarreau() == "Parc Gratuit"){
                        if(j.getCash() <= this.getInfo()){
                            ((AutreCarreau)c).addInfo(j.getCash());
                        }else{
                            ((AutreCarreau)c).addInfo(this.getInfo());
                        }
                    }
                }
		j.setCash(j.getCash() + getInfo());
		break;
	    case "Simple Visite / En Prison":
		System.out.println("A Faire ! Action Simple Visite / En Prison");
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
		break;
	    case "Taxe de Luxe":
		for(Carreau c : carreaux){
                    if(c.getNomCarreau() == "Parc Gratuit"){
                        if(j.getCash() <= this.getInfo()){
                            ((AutreCarreau)c).addInfo(j.getCash());
                        }else{
                            ((AutreCarreau)c).addInfo(this.getInfo());
                        }
                    }
                }
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
