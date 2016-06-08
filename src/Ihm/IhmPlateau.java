package Ihm;

import Data.Carreau;
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
	    ArrayList<Carreau> carreaux = monopoly.getCarreaux();

	    Graphics2D g = (Graphics2D) g1;
	    Dimension dimension = getSize(); // Taille de la zone de dessin

	    double plusPetitCotéF = dimension.height;
	    if (dimension.width < plusPetitCotéF) {
		plusPetitCotéF = dimension.width;
	    }

	    double CotéPlateau = (plusPetitCotéF * 0.98);

	    double espaceEntre = (CotéPlateau * 0.005);
	    double cotéCarreau = ((CotéPlateau - (9 * espaceEntre)) / 12);
	    double hautCarreau = (1.5) * cotéCarreau;
	    double margex = (dimension.width - CotéPlateau) / 2;
	    double margey = (dimension.height - CotéPlateau) / 2;

	    //System.out.println("plus petit coté fenetre: " + plusPetitCotéF + " coté plateau: " + CotéPlateau + " margex: " + margex + " margey: " + margey);
	    //System.out.println("espace entre: " + espaceEntre + " cotéCarreau: " + cotéCarreau);
	    //System.out.println("grand carré: " + ( (cotéCarreau*10) + (espaceEntre*9) ));		    
	    g.setStroke(new BasicStroke(2.5f));
	    int numC = 0;
	    int[] coresp = {0,1,2,3,4,5,6,7,8,9,10,39,11,38,12,37,13,36,14,35,15,34,16,33,17,32,18,31,19,30,29,28,27,26,25,24,23,22,21,20};
	    for (int y = 0; y < 11; y++) {
		for (int x = 0; x < 11; x++) {
		    if ((x == 0 || x == 10) || (y == 0 || y == 10)) {
			double espaceFirstLast = hautCarreau + (cotéCarreau * 9);

			if (y != 0 && y != 10) {
			    if (x == 0) {
				int posx = (int) (margex + (espaceEntre * x));
				int posy = (int) (margey + hautCarreau + ((y - 1) * cotéCarreau) + (espaceEntre * y));
				drawRect(g1, posx, posy, (int) hautCarreau, (int) hautCarreau, carreaux.get( (coresp[numC]+20)%40 ));
			    }
			    else {
				int posx = (int) (margex + espaceFirstLast + (espaceEntre * x));
				int posy = (int) (margey + hautCarreau + ((y - 1) * cotéCarreau) + (espaceEntre * y));
				drawRect(g1, posx, posy, (int) hautCarreau, (int) hautCarreau, carreaux.get( (coresp[numC]+20)%40 ));
			    }
			}
			else if (y == 0) {//premiere ligne
			    if (x != 0 && x != 10) {
				int posx = (int) (margex + hautCarreau + ((x - 1) * cotéCarreau) + (espaceEntre * x));
				int posy = (int) (margey + (espaceEntre * y));
				drawRect(g1, posx, posy, (int) cotéCarreau, (int) hautCarreau, carreaux.get( (coresp[numC]+20)%40 ));
			    }
			    else if (x == 0) {
				int posx = (int) (margex + (espaceEntre * x));
				int posy = (int) (margey + (espaceEntre * y));
				drawRect(g1, posx, posy, (int) hautCarreau, (int) hautCarreau, carreaux.get( (coresp[numC]+20)%40 ));
			    }
			    else {
				int posx = (int) (margex + espaceFirstLast + (espaceEntre * x));
				int posy = (int) (margey + (espaceEntre * y));
				drawRect(g1, posx, posy, (int) hautCarreau, (int) hautCarreau, carreaux.get( (coresp[numC]+20)%40 ));
			    }
			}
			else if (x != 0 && x != 10) {
			    int posx = (int) (margex + hautCarreau + ((x - 1) * cotéCarreau) + (espaceEntre * x));
			    int posy = (int) (margey + espaceFirstLast + (espaceEntre * y));
			    drawRect(g1, posx, posy, (int) cotéCarreau, (int) hautCarreau, carreaux.get( (coresp[numC]+20)%40 ));
			}
			else if (x == 0) {
			    int posx = (int) (margex + (espaceEntre * x));
			    int posy = (int) (margey + espaceFirstLast + (espaceEntre * y));
			    drawRect(g1, posx, posy, (int) hautCarreau, (int) hautCarreau, carreaux.get( (coresp[numC]+20)%40 ));
			}
			else {
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

    public void drawRect(Graphics g, int x, int y, int larg, int haut, Carreau carreau) {
	g.setColor(Color.black);
	g.drawRect(x, y, larg, haut);
	g.setColor(new Color(218, 233, 212));
	g.fillRect(x, y, larg, haut);
	
	g.setFont(new Font("Droid Sans", Font.PLAIN, 8));

	g.setColor(Color.black);
	
	String nomc = carreau.getNomCarreau();
	String[] parts = nomc.split(" ");
	
	int i = 0;
	String str;
	while (i < parts.length){
	    str = parts[i];
	    if ((i+1) < parts.length){
		if ((parts[i].length() + parts[1+i].length()) < 13){
		    str += " " + parts[i +1];
		    g.drawString(str, x+2, (y+10)+i*8);
		    i++;
		}
		else{
		    g.drawString(str, x+2, (y+10)+i*8);
		}
	    }
	    else{
		g.drawString(str, x+2, (y+10)+i*8);
	    }
	    i++;
	}
    }

    public JCanvas getJc() {
	return jc;
    }

    public void setJc(JCanvas jc) {
	this.jc = jc;
    }
}
