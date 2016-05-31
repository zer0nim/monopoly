
package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ChanceCommu extends AutreCarreau{
    private ArrayList<Carte> cartesChance = new ArrayList<>();
    private ArrayList<Carte> cartesCommu = new ArrayList<>();

    
    public ChanceCommu(int numero, String nomCarreau) {
	super(numero, nomCarreau);
	buildChanceCommu("src//main//dataChCo.txt");
    }
    
    
    private void buildChanceCommu(String dataFilename)
    {
	try{
		ArrayList<String[]> data = readDataFile(dataFilename, ",");

		for(int i=0; i<data.size(); ++i){
			String caseType = data.get(i)[0];
			if(caseType.compareTo("CH") == 0){
			    	cartesChance.add(new Carte(data.get(i)[0]));
			}
			else if(caseType.compareTo("CO") == 0){
			    	cartesCommu.add(new Carte(data.get(i)[0]));
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
}
