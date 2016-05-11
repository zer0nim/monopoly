package Jeu;
import Ihm.Ihm;

public class Controleur {

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
	public Ihm ihm;
	public Monopoly monopoly;

	public void jouerUnCoup(Joueur J) {

	}

	private Carreau lancerDésAvancer(Joueur J) {
            LancerDe();
	}
}
