package Utils;

import java.util.Random;

public class PlateauUtilitaire {
    private static final Random RANDOM = new Random();

    
    private static int De6() {
        return RANDOM.nextInt(6)+1; //retourne un nombre aléatoire entre 1 et 6
    }
    
    
    public static int LancerDe() {
        return De6(); //retourne un entier correspondant à un lancé de dé
    }  
    
}
