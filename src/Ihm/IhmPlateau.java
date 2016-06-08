package Ihm;

import javax.swing.JPanel;
import java.awt.*;

public class IhmPlateau {
	private JCanvas jc;

    public IhmPlateau() {
	jc = new JCanvas();
    }    

    public class JCanvas extends JPanel {

	    @Override
	    public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		    Dimension dimension = getSize(); // Taille de la zone de dessin
		    
		    double plusPetitCotéF = dimension.height;
		    if (dimension.width < plusPetitCotéF)
			    plusPetitCotéF = dimension.width;
		    
		    double CotéPlateau = (plusPetitCotéF*0.95);
		    
		    double espaceEntre = (CotéPlateau*0.005);
		    double cotéCarreau = ((CotéPlateau - (9 * espaceEntre)) / 12);
		    double hautCarreau = (1.5)*cotéCarreau;
		    double margex = (dimension.width - CotéPlateau)/2;
		    double margey = (dimension.height - CotéPlateau)/2;

		    
		    //System.out.println("plus petit coté fenetre: " + plusPetitCotéF + " coté plateau: " + CotéPlateau + " margex: " + margex + " margey: " + margey);
		    //System.out.println("espace entre: " + espaceEntre + " cotéCarreau: " + cotéCarreau);
		    //System.out.println("grand carré: " + ( (cotéCarreau*10) + (espaceEntre*9) ));		    
		    g.setStroke(new BasicStroke(2.5f));
		    for (int y = 0 ; y < 11 ; y++){
			for (int x = 0 ; x < 11 ; x++){
			    if ((x == 0 || x == 10) || (y == 0 || y == 10)){
				double espaceFirstLast = hautCarreau + (cotéCarreau*9);

				if (y != 0 && y != 10){
				    if (x == 0){
					g.setColor(Color.black);
					g.drawRect((int)(margex+(espaceEntre*x)), (int)(margey+hautCarreau+((y-1)*cotéCarreau)+(espaceEntre*y)),(int)hautCarreau,(int)cotéCarreau);
					g.setColor(new Color(218,233,212));
					g.fillRect((int)(margex+(espaceEntre*x)), (int)(margey+hautCarreau+((y-1)*cotéCarreau)+(espaceEntre*y)),(int)hautCarreau,(int)cotéCarreau);
				    }
				    else{
					g.setColor(Color.black);
					g.drawRect((int)(margex+espaceFirstLast+(espaceEntre*x)), (int)(margey+hautCarreau+((y-1)*cotéCarreau)+(espaceEntre*y)),(int)hautCarreau,(int)cotéCarreau);
					g.setColor(new Color(218,233,212));
					g.fillRect((int)(margex+espaceFirstLast+(espaceEntre*x)), (int)(margey+hautCarreau+((y-1)*cotéCarreau)+(espaceEntre*y)),(int)hautCarreau,(int)cotéCarreau);
				    }
				}
				else{
				    if (y == 0){//premiere ligne
					if (x != 0 && x != 10){
					    	g.setColor(Color.black);
						g.drawRect((int)(margex+hautCarreau+((x-1)*cotéCarreau)+(espaceEntre*x)), (int)(margey+(espaceEntre*y)),(int)cotéCarreau,(int)hautCarreau);
						g.setColor(new Color(218,233,212));
						g.fillRect((int)(margex+hautCarreau+((x-1)*cotéCarreau)+(espaceEntre*x)), (int)(margey+(espaceEntre*y)),(int)cotéCarreau,(int)hautCarreau);
					}
					else{
					    if (x == 0){
						g.setColor(Color.black);
						g.drawRect((int)(margex+(espaceEntre*x)), (int)(margey+(espaceEntre*y)),(int)hautCarreau,(int)hautCarreau);
						g.setColor(new Color(218,233,212));
						g.fillRect((int)(margex+(espaceEntre*x)), (int)(margey+(espaceEntre*y)),(int)hautCarreau,(int)hautCarreau);
					    }
					    else{
						g.setColor(Color.black);
						g.drawRect((int)(margex+espaceFirstLast+(espaceEntre*x)), (int)(margey+(espaceEntre*y)),(int)hautCarreau,(int)hautCarreau);
						g.setColor(new Color(218,233,212));
						g.fillRect((int)(margex+espaceFirstLast+(espaceEntre*x)), (int)(margey+(espaceEntre*y)),(int)hautCarreau,(int)hautCarreau);
					    }
					}	
				    }
				    else{
					if (x != 0 && x != 10){
						g.setColor(Color.black);
						g.drawRect((int)(margex+hautCarreau+((x-1)*cotéCarreau)+(espaceEntre*x)), (int)(margey+espaceFirstLast+(espaceEntre*y)),(int)cotéCarreau,(int)hautCarreau);
						g.setColor(new Color(218,233,212));
						g.fillRect((int)(margex+hautCarreau+((x-1)*cotéCarreau)+(espaceEntre*x)), (int)(margey+espaceFirstLast+(espaceEntre*y)),(int)cotéCarreau,(int)hautCarreau);
					}
					else{
					    if (x == 0){
						g.setColor(Color.black);
						g.drawRect((int)(margex+(espaceEntre*x)), (int)(margey+espaceFirstLast+(espaceEntre*y)),(int)hautCarreau,(int)hautCarreau);
						g.setColor(new Color(218,233,212));
						g.fillRect((int)(margex+(espaceEntre*x)), (int)(margey+espaceFirstLast+(espaceEntre*y)),(int)hautCarreau,(int)hautCarreau);
					    }
					    else{
						g.setColor(Color.black);
						g.drawRect((int)(margex+espaceFirstLast+(espaceEntre*x)), (int)(margey+espaceFirstLast+(espaceEntre*y)),(int)hautCarreau,(int)hautCarreau);
						g.setColor(new Color(218,233,212));
						g.fillRect((int)(margex+espaceFirstLast+(espaceEntre*x)), (int)(margey+espaceFirstLast+(espaceEntre*y)),(int)hautCarreau,(int)hautCarreau);
					    }
					}
				    }
				}
			    }
			}
		    }
	    }

    }
    public JCanvas getJc() {
	return jc;
    }

    public void setJc(JCanvas jc) {
	this.jc = jc;
    }           
}