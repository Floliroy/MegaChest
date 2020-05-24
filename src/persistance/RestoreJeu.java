package persistance;



import partie.Equipe;
import partie.Jeu;

import plateau.Case;
import plateau.Plateau;

public  class RestoreJeu {
	
	public static void Restore(Jeu partie, SauvegardeJeu sauvegardePartie) {
		partie.setEtatJeu(sauvegardePartie.getEtatJeu());
		
		partie.setJoueur1(sauvegardePartie.getJoueur1());
		partie.getJoueur1().setEquipe(new Equipe());
		partie.setJoueur2(sauvegardePartie.getJoueur2());
		partie.getJoueur2().setEquipe(new Equipe());
		
		Equipe equipeJoueur1 = partie.getJoueur1().getEquipe();
		for(Case caseOccupe : sauvegardePartie.getEquipeJoueur1()) {
			equipeJoueur1.addEquipe(caseOccupe.getPersonnage());
		}
		
		Equipe equipeJoueur2 = partie.getJoueur2().getEquipe();
		for(Case caseOccupe : sauvegardePartie.getEquipeJoueur2()) {
			equipeJoueur2.addEquipe(caseOccupe.getPersonnage());
		}
		

		Plateau plateau = partie.getPlateauJeu();
		
		for(int colonne = 0; colonne < Plateau.NOMBRE_COLONNE; colonne ++) {
			for(int ligne = 0; ligne < Plateau.NOMBRE_LIGNE; ligne ++) {
				plateau.getCase(colonne, ligne).setPersonnage(null);;
			}
		}
		
		for(Case current : sauvegardePartie.getEquipeJoueur1())
			plateau.getCase(current.getPositionX(), current.getPositionY()).setPersonnage(current.getPersonnage());
		
		
		for(Case current : sauvegardePartie.getEquipeJoueur2()) 
			plateau.getCase(current.getPositionX(), current.getPositionY()).setPersonnage(current.getPersonnage());
	}
	
}
