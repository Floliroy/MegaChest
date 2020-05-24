package ui.util;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Permet de créer un JPanel contenant déjà un autre composant java swing
 */

public class MyPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * JPanel avec un JLabel centré
	 * 
	 * @param label JLabel à insérer
	 */
	public MyPanel(JLabel label) {
		super();
		this.setLayout(new GridBagLayout());
		this.add(label);
	}
	
	/**
	 * JPanel avec un JButton centré
	 * 
	 * @param button JButton à insérer
	 */
	public MyPanel(JButton button) {
		super();
		this.setLayout(new GridBagLayout());
		this.add(button);
	}
	
	/**
	 * JPanel avec un JTextField centré
	 * 
	 * @param field JTextField à insérer
	 */
	public MyPanel(JTextField field) {
		super();
		this.add(field);
	}
	
}
