package Data;

import Jeu.ControleurGraphique;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class ChanceCommu{
    private ArrayList<Carte> cartesChance = new ArrayList<>(); //Liste des cartes Chances
    private ArrayList<Carte> cartesChanceMélangé = new ArrayList<>(); //Liste des cartes Chances dans un ordre aléatoire

    private ArrayList<Carte> cartesCommu = new ArrayList<>(); //Liste des cartes Communautées
    private ArrayList<Carte> cartesCommuMélangé = new ArrayList<>(); //Liste des cartes Communautées dans un ordre aléatoire
    private ControleurGraphique controleur;
    
    public ChanceCommu(ControleurGraphique controleur){
	buildChanceCommu("src//main//dataChCo.txt"); //Répertoire du fichier contenant les cartes chances est communautées
        this.controleur = controleur;
    }
    
    public Carte piocherCarteChance(){ //Pioche un carte Chance dans le paquet mélangé
	if (cartesChanceMélangé.isEmpty()) // si le paquet mélangé est vide, on le mélange
	    cartesChanceMélangé = MélangerPaquet(true);
	Carte carteP = cartesChanceMélangé.get(0);
	cartesChanceMélangé.remove(cartesChanceMélangé.get(0)); //Lorsqu'une carte est utilisé, on l'enlève du paquet mélangé
	return(carteP);
    }
    
    public Carte piocherCarteCommu(){ //Pioche un carte Chance dans le paquet mélangé
	if (cartesCommuMélangé.isEmpty()) // si le paquet mélangé est vide, on le mélang
	    cartesCommuMélangé = MélangerPaquet(false);
	Carte carteP = cartesCommuMélangé.get(0);
	cartesCommuMélangé.remove(cartesCommuMélangé.get(0)); //Lorsqu'une carte est utilisé, on l'enlève du paquet mélangé
	return(carteP);
    }
    
    private ArrayList<Carte> MélangerPaquet(boolean estCarteChance){ //Mélange les paquets de manière aléatoire
	ArrayList<Carte> cartesM;
	if (estCarteChance){
	    cartesM = (ArrayList<Carte>)cartesChance.clone();
	    Collections.shuffle(cartesM);
	}
	else{
	    cartesM = (ArrayList<Carte>)cartesCommu.clone();
	    Collections.shuffle(cartesM);
	}
	return (cartesM);
    }
    
    private void buildChanceCommu(String dataFilename) //Créer les cartes chances et communautées
    {
	try{
		ArrayList<String[]> data = readDataFile(dataFilename, ",");

		for(int i=0; i<data.size(); ++i){
			String caseType = data.get(i)[0];
			if(caseType.compareTo("CH") == 0){
			    	getCartesChance().add(new Carte(data.get(i)[1], Enumeration.ActionChCo.valueOf(data.get(i)[2]), Integer.parseInt(data.get(i)[3]) ));
			}
			else if(caseType.compareTo("CO") == 0){
			    	getCartesCommu().add(new Carte(data.get(i)[1], Enumeration.ActionChCo.valueOf(data.get(i)[2]), Integer.parseInt(data.get(i)[3])));
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
	
    private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException //Permet de lire, ligne par ligne le fichier de création des cartes chances et communautées
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

    public ArrayList<Carte> getCartesChance() {
	return cartesChance;
    }

    public void setCartesChance(ArrayList<Carte> cartesChance) {
	this.cartesChance = cartesChance;
    }

    public ArrayList<Carte> getCartesChanceMélangé() {
	return cartesChanceMélangé;
    }

    public void setCartesChanceMélangé(ArrayList<Carte> cartesChanceMélangé) {
	this.cartesChanceMélangé = cartesChanceMélangé;
    }

    public ArrayList<Carte> getCartesCommu() {
	return cartesCommu;
    }

    public void setCartesCommu(ArrayList<Carte> cartesCommu) {
	this.cartesCommu = cartesCommu;
    }

    public ArrayList<Carte> getCartesCommuMélangé() {
	return cartesCommuMélangé;
    }

    public void setCartesCommuMélangé(ArrayList<Carte> cartesCommuMélangé) {
	this.cartesCommuMélangé = cartesCommuMélangé;
    }
}
