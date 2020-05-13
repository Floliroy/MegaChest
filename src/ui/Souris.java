package ui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import plateau.Case;

public class Souris extends MouseAdapter{
	
	private Case casePlateau;
	private JPanel panneauInfos;
	
	public Souris(Case casePlateau, JPanel panneauInfos) {
		this.casePlateau = casePlateau;
		this.panneauInfos = panneauInfos;
	}
	
	@Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Case clique : " + (casePlateau.getPositionX()+1) + " , " + (casePlateau.getPositionY()+1) 
        		+ (casePlateau.getPersonnage() != null ? " -> " + casePlateau.getPersonnage().getNom() : ""));
    }
	
	@Override
    public void mouseEntered(MouseEvent e) {
		if(casePlateau.getPersonnage() != null) {
			panneauInfos.setBackground(Color.GREEN);
		}else {
			panneauInfos.setBackground(Color.PINK);
		}
    }
	
	@Override
    public void mouseExited(MouseEvent e) {
		panneauInfos.setBackground(Color.RED);
    }

	public Case getCasePlateau() {
		return casePlateau;
	}

	public void setCasePlateau(Case casePlateau) {
		this.casePlateau = casePlateau;
	}
}
