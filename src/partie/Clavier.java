package partie;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Clavier {
    private static Scanner scanner = new Scanner(System.in);

    public static int entrerClavierInt() {
    	try {
            return scanner.nextInt();
    	}catch(InputMismatchException e){
    		scanner.next();
    		System.out.println("ERREUR: Ce n'est pas un entier...");
    		return entrerClavierInt();
    	}
    }

    public static String entrerClavierString() {
        return scanner.next();
    }

}
