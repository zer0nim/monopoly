package Data;

public class Gare extends Biens_achetables{

    
    
    public Gare(int prixAchat, int numero, String nomCarreau) {
	super(prixAchat, numero, nomCarreau);
    }
    
    
    
    
    @Override
    public int CalculLoyer(int resultde){
	int prix = 25;
	for (Biens_achetables bien : getPropriétaire().getPropriétés()){
	    if (bien.getClass().getSimpleName().equals("Gare")){
		prix += 25;
	    }
	}
	return(prix);
    }
    
    
    
    //v--getters setters--v

}
