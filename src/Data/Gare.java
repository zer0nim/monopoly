package Data;

public class Gare extends Biens_achetables{

    
    
    public Gare(int prixAchat, int numero, String nomCarreau) {
	super(prixAchat, numero, nomCarreau);
    }
    
    
    
    
    @Override
    public int CalculLoyer(int resultde){
	this.getPropriétaire().getPropriétés();
	return(25);
    }
    
    
    
    //v--getters setters--v

}
