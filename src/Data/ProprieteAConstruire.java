package Data;

public class ProprieteAConstruire extends Biens_achetables{
	private int prixPassage;
        private Groupe groupe;

    
    
    public ProprieteAConstruire(int prixAchat, int prixPassage, int numero, String nomCarreau, Groupe groupe) {
	super(prixAchat, numero, nomCarreau);
	setGroupe(groupe);
    }

    
    
    
        @Override
    public int CalculLoyer(int resultde){
	return(getPrixPassage()); // A COMPLETTER
    }
    
    
    //v--getters setters--v

    public int getPrixPassage() {
	return prixPassage;
    }

    public void setPrixPassage(int prixPassage) {
	this.prixPassage = prixPassage;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
}
