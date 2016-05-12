package Data;

public class ProprieteAConstruire extends Biens_achetables{

    
    
    public ProprieteAConstruire(int prixAchat, int prixPassage, int numero, String nomCarreau, Groupe groupe) {
	super(prixAchat, prixPassage, numero, nomCarreau, groupe);
    }

    
    
    
    public int CalculLoyer(){
	return(getPrixPassage()); // A COMPLETTER
    }
    
    
    //v--getters setters--v
}
