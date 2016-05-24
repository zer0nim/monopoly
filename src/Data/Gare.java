package Data;

public class Gare extends Biens_achetables{

    
    
    public Gare(int prixAchat, int numero, String nomCarreau) {
	super(prixAchat, numero, nomCarreau);
    }
    
    
    
    
    @Override
    public int CalculLoyer(int resultde){
	int prix = 0;
	for (Biens_achetables bien : getPropriétaire().getPropriétés()){ //récupère toutes les propriétés du propriétaire de "bien"
	    if (bien.getClass().getSimpleName().equals("Gare")){ //trie pour augmenter le prix à chaque fois qu'il possède une gare
		prix += 25; //augmentation du prix de 25 pour chaque gare possédé par le propriétaire
	    }
	}
	return(prix);
    }
    
    
    
    //v--getters setters--v

}
