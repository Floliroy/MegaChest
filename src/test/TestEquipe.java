package test;

import partie.Equipe;
import partie.Jeu;
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
		
		Jarvan j = new Jarvan();
		
		Jeu partie = new Jeu();
		partie.getPlateauJeu().getCase(0, 0).setPersonnage(j);
		System.out.println(partie.getPlateauJeu().getCase(j).dumpCase());

		
	}
}
