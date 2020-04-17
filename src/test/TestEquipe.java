package test;

import partie.Equipe;
import personnages.*;

public class TestEquipe {

	public static void main(String []args) {
		
		System.out.println("Creation d'une equipe ");
		Equipe team = new Equipe();
		
		System.out.println("Creation des personnages a ajouter dans l'equipe");
	
		System.out.println("Ajout des personnages a l'equipe");
		team.addEquipe(new Jarvan());
		team.addEquipe(new Kayle());
		team.addEquipe(new Kled());
		team.addEquipe(new Fizz());
		team.addEquipe(new Swain());
		team.addEquipe(new Pyke());
		team.addEquipe(new Sion());

		
	}
}
