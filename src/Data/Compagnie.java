
package Data;

public class Compagnie extends Biens_achetables{

    public Compagnie(int prixAchat, int numero, String nomCarreau) {
	super(prixAchat, 25, numero, nomCarreau, null);
    }
    
    
    
    
    
    public int CalculLoyer(int résultatDé){
	return(résultatDé * getPrixPassage()); // A COMPLETTER
    }
    
    
    
    
    //v--getters setters--v

}
