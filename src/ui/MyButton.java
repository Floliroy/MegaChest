package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MyButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MyButton(String texte, Color color) {
		this.setText(texte);
		this.setBackground(color);
		this.setForeground(Color.DARK_GRAY);
		this.setFont(new Font("Calibri", Font.BOLD, 18));
		this.setVerticalAlignment(SwingConstants.BOTTOM);
		this.setFocusPainted(false);
	}

}
