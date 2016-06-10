package DataText;

import Data.*;

public abstract class Carreau {
    private int numero;
    private String nomCarreau;

    public Carreau(int numero, String nomCarreau) {
	this.numero = numero; //Emplacement du carreau sur le plateau de jeu
	this.nomCarreau = nomCarreau; //Nom du carreau (Exemple : "Rue de Vaugirard"
    }
    
    //v--getters setters--v

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNomCarreau() {
        return nomCarreau;
    }

    public void setNomCarreau(String nomCarreau) {
        this.nomCarreau = nomCarreau;
    }
}