package monopoly.Data;

import java.util.ArrayList;

public class Joueur {
	private String nomJoueur;
	private int cash = 1500;
	public ArrayList<Gare> gares = new ArrayList<>();
	private Carreau positionCourante;
	public ArrayList<ProprieteAConstruire> proprietés = new ArrayList<>();
	public ArrayList<Biens_achetables> propriétés = new ArrayList<>();

	public void payerLoyer(int L) {
	}

	public void recevoirLoyer(int L) {
		throw new UnsupportedOperationException();
	}
}