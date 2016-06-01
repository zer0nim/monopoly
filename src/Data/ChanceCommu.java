package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ChanceCommu{
    private ArrayList<Carte> cartesChance = new ArrayList<>();
    private ArrayList<Carte> cartesChanceMélangé = new ArrayList<>();

    private ArrayList<Carte> cartesCommu = new ArrayList<>();
    private ArrayList<Carte> cartesCommuMélangé = new ArrayList<>();

    
    public ChanceCommu(){
	buildChanceCommu("src//main//dataChCo.txt");
    }
    
    public Carte piocherCarteChance(){
	if (cartesChanceMélangé.isEmpty())
	    cartesChanceMélangé = MélangerPaquet(true);
	Carte carteP = cartesChanceMélangé.get(0);
	cartesChanceMélangé.remove(cartesChanceMélangé.get(0));
	return(carteP);
    }
    
    public Carte piocherCarteCommu(){
	if (cartesCommuMélangé.isEmpty())
	    cartesCommuMélangé = MélangerPaquet(false);
	Carte carteP = cartesCommuMélangé.get(0);
	cartesCommuMélangé.remove(cartesCommuMélangé.get(0));
	return(carteP);
    }
    
    private ArrayList<Carte> MélangerPaquet(boolean estCarteChance){
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
    
    private void buildChanceCommu(String dataFilename)
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
