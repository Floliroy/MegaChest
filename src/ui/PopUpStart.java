package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PopUpStart extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField nomJ1;
	private JTextField nomJ2;
	
	public PopUpStart(JFrame master) {
		
		
		this.setSize(300, 200);
		this.setLocationRelativeTo(master);
		this.setResizable(false);
		this.setModal(true);
		
		JPanel panel = new JPanel();
		nomJ1 = new JTextField();
		nomJ1.setPreferredSize(new Dimension(150, 25));
		nomJ2 = new JTextField();
		nomJ2.setPreferredSize(new Dimension(150, 25));
		
		panel.setBackground(Color.GRAY);
		panel.add(new JLabel("Nom Joueur 1 :"));
		panel.add(nomJ1);
		panel.add(new JLabel("Nom Joueur 2 :"));
		panel.add(nomJ2);
		this.add(panel);
		
	}

}
