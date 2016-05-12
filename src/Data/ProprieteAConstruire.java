package Data;

public class ProprieteAConstruire extends Biens_achetables{

    
    
    public ProprieteAConstruire(int prixAchat, int prixPassage, Joueur propriétaire, int numero, String nomCarreau, Groupe groupe) {
	super(prixAchat, prixPassage, propriétaire, numero, nomCarreau, groupe);
    }

    
    
    
    public int CalculLoyer(){
	return(getPrixPassage()); // A COMPLETTER
    }
    
    
    //v--getters setters--v
}
