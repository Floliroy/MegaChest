package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import personnages.Personnage;

public class SourisSelection extends MouseAdapter{

	private Personnage personnage;
	private PanneauInfos panneauInfos;
	
	public SourisSelection(Personnage personnage, PanneauInfos panneauInfos) {
		this.personnage = personnage;
		this.panneauInfos = panneauInfos;
	}
	
	@Override
    public void mouseEntered(MouseEvent e) {
		if(personnage != null) 
			panneauInfos.refresh(personnage);
    }
	
	@Override
    public void mouseExited(MouseEvent e) {
		panneauInfos.refresh();
    }
}
