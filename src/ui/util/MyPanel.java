package ui.util;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyPanel(JLabel label) {
		super();
		this.setLayout(new GridBagLayout());
		this.add(label);
	}
	
	public MyPanel(JTextField field) {
		super();
		this.add(field);
	}
	
}
