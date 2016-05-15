package Data;

public class Gare extends Biens_achetables{

    
    
    public Gare(int prixAchat, int numero, String nomCarreau) {
	super(prixAchat, numero, nomCarreau);
    }
    
    
    
    
    @Override
    public int CalculLoyer(int resultde){
	return(25); // A COMPLETTER
    }
    
    
    
    //v--getters setters--v

}
