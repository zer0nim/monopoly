package Data;
import Data.Enumeration;
import static Data.Enumeration.ACTIONCHCO;

public class Carte {
    private String description;

    public Carte(String description, Data.Enumeration.ACTIONCHCO ttt) {
	setDescription(description);
    }
    
    public void Action(){
    }
    
    libération prison
    reculer
    avancerDeN
    avancerJusqu'aAvecCaseDep
    avancerJusqu'aSansCaseDep
    payerCash
    recvoirCash
    payerCashSelonHotels
    allerPrison
    anniversaire
    //v--getters setters--v

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

}
