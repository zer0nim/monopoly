package Ihm;

import Data.*;
import Jeu.Monopoly;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class IhmPlateau {
    private Monopoly monopoly;
    private JCanvas jc;

    public IhmPlateau(Monopoly monopoly) {
	jc = new JCanvas();
	this.monopoly = monopoly;
    }

    public class JCanvas extends JPanel {

	@Override
	public void paint(Graphics g1) {
	    ArrayList<Carreau> carreaux = getMonopoly().getCarreaux();

	    Graphics2D g = (Graphics2D) g1;
	    Dimension dimension = getSize(); // Taille du paneau

	    double plusPetitCotéF = dimension.height;
	    if (dimension.width < plusPetitCotéF) {
		plusPetitCotéF = dimension.width;
	    }

	    double CotéPlateau = (plusPetitCotéF * 0.98);

	    double espaceEntre = (CotéPlateau * 0.005);
	    double cotéCarreau = ((CotéPlateau - (9 * espaceEntre)) / 12); //calcul de la taille du plus petit coté d'un careau
	    double hautCarreau = (1.5) * cotéCarreau; //calcul de la taille du plus grand coté d'un careau
	    double margex = (dimension.width - CotéPlateau) / 2;
	    double margey = (dimension.height - CotéPlateau) / 2;

	    //System.out.println("plus petit coté fenetre: " + plusPetitCotéF + " coté plateau: " + CotéPlateau + " margex: " + margex + " margey: " + margey);
	    //System.out.println("espace entre: " + espaceEntre + " cotéCarreau: " + cotéCarreau);
	    //System.out.println("grand carré: " + ( (cotéCarreau*10) + (espaceEntre*9) ));	    
	    g.setStroke(new BasicStroke(2.5f));
	  
	    g.setColor(new Color(146,169,143));// Couleur de fond
	    g.fillRect(0, 0, dimension.width, dimension.height);
	    
	    //-------vv----Affiche le centre du plateau----vvv
	    Image imgCentre = Toolkit.getDefaultToolkit().getImage("src//Image//CentreJeu.png");
	    g.drawImage(imgCentre, (int)(margex+hautCarreau+2), (int)(margey+hautCarreau+2), (int)(CotéPlateau - (hautCarreau*2)), (int)(CotéPlateau - (hautCarreau*2)), null);
	    
	    int numC = 0;
	    
	    // Comme les careau ne sont pas désiné dans l'ordre mais ligne par ligne on utilise se tableau de conversion
	    int[] coresp = {0,1,2,3,4,5,6,7,8,9,10,39,11,38,12,37,13,36,14,35,15,34,16,33,17,32,18,31,19,30,29,28,27,26,25,24,23,22,21,20};
	    
	    // ----vv----double boucle pour parcourir un tableau fictif a deux dimension--vv---
	    for (int y = 0; y < 11; y++) {
		for (int x = 0; x < 11; x++) {
	    // ----^^----double boucle pour parcourir un tableau fictif a deux dimension--^^---
		    
		    if ((x == 0 || x == 10) || (y == 0 || y == 10)) { //permet de ne rien faire si on se trouve sur les cases du milieu(exemple 5-0 ok mais 5-2 pas ok car au centre)
			
			
			double espaceFirstLast = hautCarreau + (cotéCarreau * 9); //espace entre les deux carreaux en hauteur ou en largeur(car carré) -> 1----------1 espace entre 1 et 1

			if (y != 0 && y != 10) { //Pour dessiner tous les carreaux centraux
			    if (x == 0) { //premier c de la ligne
				int posx = (int) (margex + (espaceEntre * x));
				int posy = (int) (margey + hautCarreau + ((y - 1) * cotéCarreau) + (espaceEntre * y));
				drawRect(g1, posx, posy, (int) hautCarreau, (int) cotéCarreau, carreaux.get( (coresp[numC]+20)%40 ));
			    }
			    else { //dernier c de la ligne
				int posx = (int) (margex + espaceFirstLast + (espaceEntre * x));
				int posy = (int) (margey + hautCarreau + ((y - 1) * cotéCarreau) + (espaceEntre * y));
				drawRect(g1, posx, posy, (int) hautCarreau, (int) cotéCarreau, carreaux.get( (coresp[numC]+20)%40 ));
			    }
			}
			//--vv puis la premiere et derniere ligne

			//--vv premiere ligne
			else if (y == 0) {//premiere ligne
			    if (x != 0 && x != 10) {// c centraux de la ligne
				int posx = (int) (margex + hautCarreau + ((x - 1) * cotéCarreau) + (espaceEntre * x));
				int posy = (int) (margey + (espaceEntre * y));
				drawRect(g1, posx, posy, (int) cotéCarreau, (int) hautCarreau, carreaux.get( (coresp[numC]+20)%40 ));
			    }
			    else if (x == 0) { //premier c de la ligne
				int posx = (int) (margex + (espaceEntre * x));
				int posy = (int) (margey + (espaceEntre * y));
				drawRect(g1, posx, posy, (int) hautCarreau, (int) hautCarreau, carreaux.get( (coresp[numC]+20)%40 ));
			    }
			    else { //dernier c de la ligne
				int posx = (int) (margex + espaceFirstLast + (espaceEntre * x));
				int posy = (int) (margey + (espaceEntre * y));
				drawRect(g1, posx, posy, (int) hautCarreau, (int) hautCarreau, carreaux.get( (coresp[numC]+20)%40 ));
			    }
			}
			//--vv derniere ligne
			else if (x != 0 && x != 10) {// c centraux de la ligne
			    int posx = (int) (margex + hautCarreau + ((x - 1) * cotéCarreau) + (espaceEntre * x));
			    int posy = (int) (margey + espaceFirstLast + (espaceEntre * y));
			    drawRect(g1, posx, posy, (int) cotéCarreau, (int) hautCarreau, carreaux.get( (coresp[numC]+20)%40 ));
			}
			else if (x == 0) { //premier c de la ligne
			    int posx = (int) (margex + (espaceEntre * x));
			    int posy = (int) (margey + espaceFirstLast + (espaceEntre * y));
			    drawRect(g1, posx, posy, (int) hautCarreau, (int) hautCarreau, carreaux.get( (coresp[numC]+20)%40 ));
			}
			else { //dernier c de la ligne
			    int posx = (int) (margex + espaceFirstLast + (espaceEntre * x));
			    int posy = (int) (margey + espaceFirstLast + (espaceEntre * y));
			    drawRect(g1, posx, posy, (int) hautCarreau, (int) hautCarreau, carreaux.get( (coresp[numC]+20)%40 ));
			}
			numC++;
			

		    }
		    
		}
	    }
	    
	    
	}

    }

    public void drawRect(Graphics g, int x, int y, int larg, int haut, Carreau carreau) { //Permet de dessiner une carte a la taille et au coordonés donnés
	g.setColor(Color.black);
	g.drawRect(x, y, larg, haut);
	g.setColor(new Color(204,227,199));
	g.fillRect(x, y, larg, haut);
	
	//------------vvv----Affiche--Couleurs-Groupes-----vvv
	if (carreau.getClass().getSimpleName().equals("ProprieteAConstruire")){ //donc si possede groupe
	    String couleur = ((ProprieteAConstruire)carreau).getGroupe().getCouleur().toString();
	    
	    switch (couleur) {
	    	case "bleuFonce":
		    g.setColor(new Color(1,104,181));
		    break;
	    	case "orange":
		    g.setColor(new Color(245,144,2));
		    break;
	    	case "mauve":
		    g.setColor(new Color(212,115,212));
		    break;
	    	case "violet":
		    g.setColor(new Color(127,72,140));
		    break;
	    	case "bleuCiel":
		    g.setColor(new Color(146,211,244));
		    break;
	    	case "jaune":
		    g.setColor(new Color(255,236,1));
		    break;
	    	case "vert":
		    g.setColor(new Color(31,165,76));
		    break;
	    	default:
		    //rouge
		    g.setColor(new Color(229,2,19));
		    break;
	    }
	    int hauteurGroupe = (haut > larg)? larg/4 : haut/4;

	    g.fillRect(x, y, larg, hauteurGroupe);//aff couleurgroupe
	}
	//------------^^^----Affiche--Couleurs-Groupes-----^^^
	
	
	
	//------------vvv----Affiche--Texte-Carreau-----vvv
	g.setFont(new Font("Droid Sans", Font.PLAIN, 9));

	g.setColor(Color.black);
	
	String nomc = carreau.getNomCarreau();
	String[] parts = nomc.split(" "); // Crée un tableau de la Chaine de caractere découpé avec pour séparateur " " dans chaque case
	
	int margeH = 10;
	if (carreau.getClass().getSimpleName().equals("ProprieteAConstruire")) //donc si possede groupe
	    margeH = 26;
	//---------vv-----permet-d'afficher-le-nom-de-la-carte-selon-l'espace-disponible
	int i = 0;
	int lign = 0;
	String str;
	while (i < parts.length){
	    str = parts[i];
	    if ((i+1) < parts.length){
		if ((parts[i].length() + parts[1+i].length()) < 13){
		    str += " " + parts[i +1];
		    g.drawString(str, x+2, (y+margeH)+lign*10);
		    i++;
		}
		else{
		    g.drawString(str, x+2, (y+margeH)+lign*10);
		}
	    }
	    else{
		g.drawString(str, x+2, (y+margeH)+lign*10);
	    }
	    i++;
	    lign++;
	}
	
	if (carreau.getClass().getSuperclass().getSimpleName().equals("Biens_achetables")){
	    g.drawString( Integer.toString(((Biens_achetables)carreau).getPrixAchat()) + "€", x+(larg/2)-(Integer.toString(((Biens_achetables)carreau).getPrixAchat()).length()*5/2), y+(haut-2));
	}
 	//------------^^^----Affiche--Texte-Carreau-----^^^
	
	
	
	//------------vvv----Affiche--Pions-Joueurs-----vvv	
	String nomImage;
	int comptPions = 0;
	for (Joueur jCourt : getMonopoly().getJoueurs()){ //boucle sur les joueurs
	    if (jCourt.getPositionCourante().equals(carreau)){
		nomImage = jCourt.getPion().toString();

		Image img1 = Toolkit.getDefaultToolkit().getImage("src//Image//Pions//" + nomImage + ".png"); //selectionne l'image du pion correspondant à celui du joueur jCourt
		g.drawImage(img1, ((comptPions < 3)?(x+comptPions*(larg/3)):(x+(comptPions-3)*(larg/3))),   (int)((y+haut)-(haut/3.6 * ((comptPions < 3)? 1:1.7))),    (int)(larg/3.5),    (int)( (int)(larg/3.5)*0.71), null);
 		comptPions ++;
	    }
	}
	//------------^^^----Affiche--Pions-Joueurs-----^^^

    }
    
    public JCanvas getJc() {
	return jc;
    }

    public void setJc(JCanvas jc) {
	this.jc = jc;
    }
    
    public Monopoly getMonopoly() {
	return monopoly;
    }
}