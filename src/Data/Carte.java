package Data;
import Data.Enumeration;

public class Carte {
    private String description;
    private Enumeration.ActionChCo typeAction;

    public Carte(String description, Enumeration.ActionChCo typeAct) {
	setDescription(description);
	setTypeAction(typeAct);
    }
    
    public void Action(){
    }
    

    //v--getters setters--v

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Enumeration.ActionChCo getTypeAction() {
	return typeAction;
    }

    public void setTypeAction(Enumeration.ActionChCo typeAction) {
	this.typeAction = typeAction;
    }

}
