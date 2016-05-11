package Utils;

import java.util.Random;

public class PlateauUtilitaire {
    private static final Random RANDOM = new Random();

    
    private static int De3() {
        return RANDOM.nextInt(3)+1;
    }
    
    
    public static int De3(int nbdes) {
        int degatsub = 0;
        for(int i = 1; i < nbdes; i++){
            degatsub += De3();
        }
        return degatsub;
    }  
    
    
}
