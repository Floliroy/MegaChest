package util;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class RedirectionOutput extends OutputStream {

	private JTextArea textArea;
	
	public RedirectionOutput(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	public void write(int b) throws IOException{
		textArea.append(String.valueOf((char)b));
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	

}
