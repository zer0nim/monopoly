package Data;

public class ProprieteAConstruire extends Biens_achetables{
	private int prixPassage;

    
    
    public ProprieteAConstruire(int prixAchat, int prixPassage, int numero, String nomCarreau, Groupe groupe) {
	super(prixAchat, numero, nomCarreau, groupe);
	
    }

    
    
    
    public int CalculLoyer(){
	return(getPrixPassage()); // A COMPLETTER
    }
    
    
    //v--getters setters--v

    public int getPrixPassage() {
	return prixPassage;
    }

    public void setPrixPassage(int prixPassage) {
	this.prixPassage = prixPassage;
    }
}
