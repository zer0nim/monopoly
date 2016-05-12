
package Data;

public class Compagnie extends Biens_achetables{

    public Compagnie(int prixAchat, int numero, String nomCarreau) {
	super(prixAchat, numero, nomCarreau, null);
    }
    
    
    
    
    public int CalculLoyer(int résultatDé) {
	return(résultatDé * 25); // A COMPLETTER
    }
    
    
    
    
    //v--getters setters--v



}
