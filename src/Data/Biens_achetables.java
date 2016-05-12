package Data;

public class Biens_achetables {
    private int PrixAchat, PrixPassage;

    public void action(Joueur j){

    }
    
    public void acheterPropriété(Joueur j){

    }
    
    public boolean assezArgent(Joueur j){
	return((getPrixAchat() - j.getCash()) <= 0);
    }
    

    
    
    
    
    //v--getters setters--v

    public int getPrixAchat() {
	return PrixAchat;
    }

    public void setPrixAchat(int pa) {
	this.PrixAchat = pa;
    }

    public int getPrixPassage() {
	return PrixPassage;
    }

    public void setPrixPassage(int pp) {
	this.PrixPassage = pp;
    }

}
