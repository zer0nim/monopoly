
package Data;

public class Compagnie extends Biens_achetables{

    public Compagnie(int prixAchat, int prixPassage, Joueur propriétaire, int numero, String nomCarreau, Groupe groupe) {
	super(prixAchat, prixPassage, propriétaire, numero, nomCarreau, groupe);
    }
    
    
    
    
    
    public int CalculLoyer(int résultatDé){
	return(résultatDé * getPrixPassage()); // A COMPLETTER
    }
    
    
    
    
    //v--getters setters--v

}
