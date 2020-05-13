package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CaseImage extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	private BufferedImage fond;

	public CaseImage(String stringImage, int sizeX, int sizeY, String stringFond) {
		 try {         
	          image = resize(ImageIO.read(new File(stringImage)), sizeX, sizeY);
	          if(stringFond != null)
	        	  fond = ImageIO.read(new File(System.getProperty("user.dir") + "/images/util/" + stringFond));

	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	}
	
	private BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		if(fond != null)
			g2d.drawImage(fond, 0, 0, null);
		
		g2d.translate(this.getWidth() / 2, this.getHeight() / 2);
		g2d.translate(-image.getWidth(null) / 2, -image.getHeight(null) / 2);
		g2d.drawImage(image, 0, 0, null);  
	}

}
