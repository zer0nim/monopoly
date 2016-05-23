
package Data;

public class Compagnie extends Biens_achetables{

    public Compagnie(int prixAchat, int numero, String nomCarreau) {
	super(prixAchat, numero, nomCarreau);
    }
    
    
    
    
    @Override
    public int CalculLoyer(int résultatDé) {
	int nbComp = 0;
        for (Biens_achetables bien : getPropriétaire().getPropriétés()){
            if (bien.getClass().getSimpleName().equals("Compagnie")){
                nbComp++;
            }
        }
        if (nbComp == 1){
            return(4*résultatDé);
        } else {
            return(10*résultatDé);
        }
    }
    
    
    
    
    //v--getters setters--v



}
