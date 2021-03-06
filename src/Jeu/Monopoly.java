package Jeu;

import java.util.ArrayList;
import Data.*;
import Ihm.IhmText;
import java.io.*;


public class Monopoly {
    private ArrayList<Carreau> carreaux;
    private ArrayList<Joueur> joueurs;
    private ArrayList<Groupe> groupes;
    private ChanceCommu cartesChCo;
    private ControleurGraphique controleur;
    
    public Monopoly(ControleurGraphique controleur) {
	carreaux = new ArrayList<>();
	joueurs = new ArrayList<>();
	groupes = new ArrayList<>();
        this.controleur = controleur;
	
	CreerPlateau("src//main//data.txt");
	cartesChCo = new ChanceCommu(controleur);
    }

    public void CreerPlateau(String dataFilename){
	for(CouleurPropriete c : CouleurPropriete.values()){
	    getGroupes().add(new Groupe(c));
	}
	buildGamePlateau(dataFilename);
    }
	
    private void buildGamePlateau(String dataFilename)
    {
	try{
		ArrayList<String[]> data = readDataFile(dataFilename, ",");

		for(int i=0; i<data.size(); ++i){
			String caseType = data.get(i)[0];
			if(caseType.compareTo("P") == 0){
				ArrayList<Integer> Loyers = new ArrayList<>();
                                for(int j = 6; j <= 10; j++){
                                    Loyers.add(Integer.parseInt(data.get(i)[j]));
                                }
				carreaux.add(new ProprieteAConstruire(Integer.parseInt(data.get(i)[4]), Integer.parseInt(data.get(i)[5]), Integer.parseInt(data.get(i)[1]), data.get(i)[2], getGroupe(CouleurPropriete.valueOf(data.get(i)[3])), Loyers, Integer.parseInt(data.get(i)[11]), Integer.parseInt(data.get(i)[12]), controleur));
				//public ProprieteAConstruire(int prixAchat, int prixPassage, int numero, String nomCarreau, Groupe groupe) {
                                ((ProprieteAConstruire)carreaux.get(carreaux.size()-1)).getGroupe().setCarreau(((ProprieteAConstruire)carreaux.get(carreaux.size()-1)));
			}else if(caseType.compareTo("G") == 0){
				carreaux.add(new Gare(Integer.parseInt(data.get(i)[3]), Integer.parseInt(data.get(i)[1]), data.get(i)[2],controleur));
				//public Gare(int prixAchat, int numero, String nomCarreau) {
			}else if(caseType.compareTo("C") == 0){
				carreaux.add(new Compagnie(Integer.parseInt(data.get(i)[3]), Integer.parseInt(data.get(i)[1]), data.get(i)[2],controleur));
				//public Compagnie(int prixAchat, int prixPassage, int numero, String nomCarreau, Groupe groupe) {
			}else if(caseType.compareTo("AU") == 0){
			    if(data.get(i).length < 4){
				carreaux.add(new AutreCarreau(Integer.parseInt(data.get(i)[1]), data.get(i)[2],0, controleur));
                            }else{
				carreaux.add(new AutreCarreau(Integer.parseInt(data.get(i)[1]), data.get(i)[2], Integer.parseInt(data.get(i)[3]), controleur));
				//public AutreCarreau(int numero, String nomCarreau) {
                            }
                        }else{
				System.err.println("[buildGamePleateau()] : Invalid Data type");
                        }
		}
		
	} 
	catch(FileNotFoundException e){
		System.err.println("[buildGamePlateau()] : File is not found!");
	}
	catch(IOException e){
		System.err.println("[buildGamePlateau()] : Error while reading file!");
	}
    }
	
    private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException
    {
	ArrayList<String[]> data = new ArrayList<>();
	
	BufferedReader reader  = new BufferedReader(new FileReader(filename));
	String line = null;
	while((line = reader.readLine()) != null){
		data.add(line.split(token));
	}
	reader.close();
	
	return data;
    }

    
    
    
    //v--getters setters--v

    public ArrayList<Carreau> getCarreaux() {
	return carreaux;
    }

    public void setCarreaux(Carreau c) {
	getCarreaux().add(c);
    }

    public ArrayList<Joueur> getJoueurs() {
	return joueurs;
    }

    public void setJoueur(Joueur j) {
	joueurs.add(j);
    }

    public Groupe getGroupe(CouleurPropriete c) {
	for(Groupe g : getGroupes()){
	    if(g.getCouleur() == c)
		return(g);
	}
	return(null);
    }
    
    public ArrayList<Groupe> getGroupes() {
	return groupes;
    }

    public void setGroupes(ArrayList<Groupe> groupes) {
	this.groupes = groupes;
    }

    public void setJoueurs(ArrayList<Joueur> joueurs) {
	this.joueurs = joueurs;
    }

    public ChanceCommu getCartesChCo() {
	return cartesChCo;
    }

    public void setCartesChCo(ChanceCommu cartesChCo) {
	this.cartesChCo = cartesChCo;
    }
    
    public void removeJoueur(Joueur j){
        joueurs.remove(j);
    }
}

