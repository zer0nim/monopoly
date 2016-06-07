package affPlateau;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class IhmPlateau {
    public IhmPlateau() {
	JCanvas jc = new JCanvas();

	JFrame framePlateau = new JFrame();
	framePlateau.setTitle("Plateau");
	framePlateau.setSize(800, 800);
	framePlateau.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	framePlateau.add(jc);
	framePlateau.setVisible(true);  
    }
    

    public class JCanvas extends JPanel {

	    @Override
	    public void paint(Graphics g) {
		    Dimension dimension = getSize(); // Taille de la zone de dessin
		    
		    double plusPetitCotéF = dimension.height;
		    if (dimension.width < plusPetitCotéF)
			    plusPetitCotéF = dimension.width;
		    
		    double CotéPlateau = (plusPetitCotéF*0.98);
		    
		    double espaceEntre = (CotéPlateau*0.005);
		    double cotéCarreau = ((CotéPlateau - (9 * espaceEntre)) / 10);
		    double margex = (dimension.width - CotéPlateau)/2;
		    double margey = (dimension.height - CotéPlateau)/2;

		    
		    //System.out.println("plus petit coté fenetre: " + plusPetitCotéF + " coté plateau: " + CotéPlateau + " margex: " + margex + " margey: " + margey);
		    //System.out.println("espace entre: " + espaceEntre + " cotéCarreau: " + cotéCarreau);
		    //System.out.println("grand carré: " + ( (cotéCarreau*10) + (espaceEntre*9) ));
		    
		    g.setColor(new Color(160,197,98));
		    
		    
		    for (int y = 0 ; y < 10 ; y++){
			for (int x = 0 ; x < 10 ; x++){
			    if ((x == 0 || x == 9) || (y == 0 || y == 9))				
				g.fillRect((int)((cotéCarreau*x)+(espaceEntre*x)+margex), (int)((cotéCarreau*y)+(espaceEntre*y)+margey),(int)cotéCarreau,(int)cotéCarreau);
			}
		    }
	    }

    }
                
}