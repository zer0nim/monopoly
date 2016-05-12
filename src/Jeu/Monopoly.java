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
    
    
    public Monopoly() {
	carreaux = new ArrayList<>();
	joueurs = new ArrayList<>();
	
	CreerPlateau("src\\main\\data.txt");
    }

    public void CreerPlateau(String dataFilename){
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
				System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
				carreaux.add(	new ProprieteAConstruire(int prixAchat, int prixPassage, Joueur propriétaire, int numero, String nomCarreau, Groupe groupe) )
			}
			else if(caseType.compareTo("G") == 0){
				System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
				carreaux.add(	new Gare(int prixAchat, int prixPassage, Joueur propriétaire, int numero, String nomCarreau, Groupe groupe)    )
			}
			else if(caseType.compareTo("C") == 0){
				System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
				carreaux.add(	new Compagnie(int prixAchat, int prixPassage, Joueur propriétaire, int numero, String nomCarreau, Groupe groupe)	)
			}
			else if(caseType.compareTo("AU") == 0){
				System.out.println("Case Autre :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
				carreaux.add(	new AutreCarreau(int numero, String nomCarreau, Groupe groupe)    )
			}
			else
				System.err.println("[buildGamePleateau()] : Invalid Data type");
		}
							/*
							run:
						Case Autre :	Départ	@ case 1
						Propriété :	Bd de Belleville	@ case 2
						[buildGamePleateau()] : Invalid Data type
						Case Autre :	Caisse de Communauté	@ case 3
						Propriété :	Rue Lecourbe	@ case 4
						Case Autre :	Impôt sur le revenu	@ case 5
						Gare :	Gare Montparnasse	@ case 6
						Propriété :	Rue de Vaugirard	@ case 7
						Case Autre :	Chance	@ case 8
						Propriété :	Rue de Courcelles	@ case 9
						Propriété :	Av. de la République	@ case 10
						Case Autre :	Simple Visite / En Prison	@ case 11
						Propriété :	Bd de la Villette	@ case 12
						Compagnie :	Companie de distribution d'électricité	@ case 13
						Propriété :	Rue de Paradis	@ case 14
						Propriété :	Av. de Neuilly	@ case 15
						Gare :	Gare de Lyon	@ case 16
						Propriété :	Bd Saint-Michel	@ case 17
						Case Autre :	Caisse de Communauté	@ case 18
						Propriété :	Av. Mozart	@ case 19
						Propriété :	Place Pigalle	@ case 20
						Case Autre :	Parc Gratuit	@ case 21
						Propriété :	Bd Malesherbes	@ case 22
						Case Autre :	Chance	@ case 23
						Propriété :	Av. Matignon	@ case 24
						Propriété :	Av. Henri-Martin	@ case 25
						Gare :	Gare du Nord	@ case 26
						Propriété :	Faub. Saint-Honoré	@ case 27
						Propriété :	Place de la Bourse	@ case 28
						Compagnie :	Companie de distribution des eaux	@ case 29
						Propriété :	Rue La Fayette	@ case 30
						Case Autre :	Allez en prison	@ case 31
						Propriété :	Av. de Breteuil	@ case 32
						Propriété :	Av. Foch	@ case 33
						Case Autre :	Caisse de Communauté	@ case 34
						Propriété :	Bd des Capucines	@ case 35
						Gare :	Gare Saint-Lazare	@ case 36
						Case Autre :	Chance	@ case 37
						Propriété :	Av. des Champs-Elysées	@ case 38
						Case Autre :	Taxe de Luxe	@ case 39
						Propriété :	Rue de la Paix	@ case 40
						BUILD SUCCESSFUL (total time: 0 seconds)

							*/
		
		
		
		
		
		
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
}

