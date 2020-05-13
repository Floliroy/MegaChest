package ui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanneauLogs extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextArea textOutput;
	
	public PanneauLogs(JTextArea textArea) {
		this.setBackground(Color.MAGENTA);
		this.textOutput = textArea;
		this.textOutput.setEditable(false);
		this.textOutput.setVisible(true);
	}

	public JTextArea getTextOutput() {
		return textOutput;
	}

}
