package Data;

import java.util.ArrayList;

public class Groupe {
    private CouleurPropriete couleur;
    public ArrayList<Carreau> carreau = new ArrayList<>();

    public Groupe(CouleurPropriete couleur) {
	this.couleur = couleur;
    }

    public CouleurPropriete getCouleur() {
        return couleur;
    }

    public void setCouleur(CouleurPropriete couleur) {
        this.couleur = couleur;
    }

    public ArrayList<Carreau> getCarreau() {
        return carreau;
    }

    public void setCarreau(Carreau carreau) {
        this.carreau.add(carreau);
    }
}