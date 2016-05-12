package Data;

public class Gare extends Biens_achetables{

    
    
    public Gare(int prixAchat, int prixPassage, int numero, String nomCarreau, Groupe groupe) {
	super(prixAchat, prixPassage, numero, nomCarreau, groupe);
    }
    
    
    
    
    public int CalculLoyer(){
	return(getPrixPassage()); // A COMPLETTER
    }
    
    
    
    //v--getters setters--v

}
