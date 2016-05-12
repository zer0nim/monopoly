package Data;

public class Gare extends Biens_achetables{

    
    
    public Gare(int prixAchat, int numero, String nomCarreau) {
	super(prixAchat, 25, numero, nomCarreau, null);
    }
    
    
    
    
    public int CalculLoyer(){
	return(getPrixPassage()); // A COMPLETTER
    }
    
    
    
    //v--getters setters--v

}
