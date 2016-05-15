package Jeu;
import Ihm.*;
import Data.*;
import Utils.*;

public class Controleur {
    public Ihm ihm;
    public Monopoly monopoly;

    public void jouerUnCoup(Joueur j) {
        j.getPositionCourante().action(j, lancerDésAvancer(j).getNumero());
    }

    private Carreau lancerDésAvancer(Joueur j){
        j.setPositionCourante(getMonopoly().getCarreaux().get(PlateauUtilitaire.LancerDe()));
	return(j.getPositionCourante());
    }
    
    
    

    //v--getters setters--v
    public Ihm getIhm() {
        return ihm;
    }

    public void setIhm(Ihm ihm) {
        this.ihm = ihm;
    }

    public Monopoly getMonopoly() {
        return monopoly;
    }

    public void setMonopoly(Monopoly monopoly) {
        this.monopoly = monopoly;
    }
}
