package Utils;

import java.util.Random;

public class PlateauUtilitaire {
    private static final Random RANDOM = new Random();

    
    private static int De6() {
        return RANDOM.nextInt(6)+1; //retourne un nombre aléatoire entre 1 et 6
    }
    
    
    public static int LancerDe() {
        int de = De6(); //
        return de;
    }  
    
}
