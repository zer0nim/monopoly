
package DataText;

import DataText.*;

public class Compagnie extends Biens_achetables{

    public Compagnie(int prixAchat, int numero, String nomCarreau) {
	super(prixAchat, numero, nomCarreau);
    }
    
    
    
    
    @Override
    public int CalculLoyer(int résultatDé) {
	int nbComp = 0;
        for (Biens_achetables bien : getPropriétaire().getPropriétés()){
            if (bien.getClass().getSimpleName().equals("Compagnie")){ //Boucle sur les Compagnie du joueur actuel
                nbComp++; //Compte le nombre de COmpagnie du joueur actuel
            }
        }
        if (nbComp == 1){ // Si il en a 1 seul, le prix est égale à 4 fois le résultat du dés
            return(4*résultatDé);
        } else { // Sinon le prix est égale à 10 fois le résultat du dés
            return(10*résultatDé);
        }
    }
    
    
    
    
    
    //v--getters setters--v



}
