package Jeu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Data.*;


public class Monopoly {
    private ArrayList<Carreau> carreaux;
    private ArrayList<Joueur> joueurs;
    private ArrayList<Groupe> groupes;
    
    
    
    public Monopoly() {
	carreaux = new ArrayList<>();
	joueurs = new ArrayList<>();
	groupes = new ArrayList<>();
	
	CreerPlateau("src\\main\\data.txt");
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
				System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
			}
			else if(caseType.compareTo("G") == 0){
				System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
			}
			else if(caseType.compareTo("C") == 0){
				System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
			}
			else if(caseType.compareTo("AU") == 0){
				System.out.println("Case Autre :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
			}
			else
				System.err.println("[buildGamePleateau()] : Invalid Data type");
		}
		
		
	//TODO: create cases instead of displaying TTTTTOOOOO_DDDDDDDDDDOOOOOOOOOOOOOOOOOOOOOOO TTTTTOOOOO_DDDDDDDDDDOOOOOOOOOOOOOOOOOOOOOOO TTTTTOOOOO_DDDDDDDDDDOOOOOOOOOOOOOOOOOOOOOOO
	//TODO: create cases instead of displaying TTTTTOOOOO_DDDDDDDDDDOOOOOOOOOOOOOOOOOOOOOOO TTTTTOOOOO_DDDDDDDDDDOOOOOOOOOOOOOOOOOOOOOOO TTTTTOOOOO_DDDDDDDDDDOOOOOOOOOOOOOOOOOOOOOOO

				
		
		for(int i=0; i<data.size(); ++i){
			String caseType = data.get(i)[0];
			if(caseType.compareTo("P") == 0){
				carreaux.add(new ProprieteAConstruire(Integer.parseInt(data.get(i)[4]), Integer.parseInt(data.get(i)[5]), Integer.parseInt(data.get(i)[1]), data.get(i)[2], getGroupe(CouleurPropriete.valueOf(data.get(i)[3]))  )	    );
				//public ProprieteAConstruire(int prixAchat, int prixPassage, int numero, String nomCarreau, Groupe groupe) {
			}
			else if(caseType.compareTo("G") == 0){
				carreaux.add(new Gare(Integer.parseInt(data.get(i)[4]), Integer.parseInt(data.get(i)[5]), Integer.parseInt(data.get(i)[1]), data.get(i)[2], getGroupe(CouleurPropriete.valueOf(data.get(i)[3])) )    );
				//public Gare(int prixAchat, int prixPassage, int numero, String nomCarreau, Groupe groupe) {
			}
			else if(caseType.compareTo("C") == 0){
				carreaux.add(	new Compagnie(Integer.parseInt(data.get(i)[4]), Integer.parseInt(data.get(i)[5]), Integer.parseInt(data.get(i)[1]), data.get(i)[2], getGroupe(CouleurPropriete.valueOf(data.get(i)[3])) )	);
				//public Compagnie(int prixAchat, int prixPassage, int numero, String nomCarreau, Groupe groupe) {
			}
			else if(caseType.compareTo("AU") == 0){
				carreaux.add(	new AutreCarreau(Integer.parseInt(data.get(i)[1]), data.get(i)[2], getGroupe(CouleurPropriete.valueOf(data.get(i)[3])) )    );
				//public AutreCarreau(int numero, String nomCarreau, Groupe groupe) {
			}
			else
				System.err.println("[buildGamePleateau()] : Invalid Data type");
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

    public void setJoueurs(Joueur j) {
	getJoueurs().add(j);
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
}

