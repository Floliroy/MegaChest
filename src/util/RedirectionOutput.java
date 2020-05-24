package util;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

/**
 * Classe permettant de faire la redirection de System.out 
 * vers la JTextArea passée en paramètre du constructeur.
 */
public class RedirectionOutput extends OutputStream {

	private JTextArea textArea;
	
	/**
	 * Constructeur
	 * 
	 * @param textArea zone de texte où est redirigée System.out
	 */
	public RedirectionOutput(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	/**
	 * Ajoute le texte à l'intérieur du JTextArea.
	 */
	public void write(int b) throws IOException{
		textArea.append(String.valueOf((char)b));
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	

}
