package Utils;

import java.util.Random;

public class PlateauUtilitaire {
    private static final Random RANDOM = new Random();

    
    private static int De6() {
        return RANDOM.nextInt(6)+1;
    }
    
    
    public static int LancerDe() {
        int de = 0;
        de += De6();
        return de;
    }  
    
}
