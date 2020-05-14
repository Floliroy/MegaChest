package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import personnages.Personnage;

public class CaseImage extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Personnage personnage;
	private Integer sizeX;
	private Integer sizeY;
	private String stringFond;
	private HashMap<String, Integer> transparency;


	public CaseImage(Personnage personnage, int sizeX, int sizeY, String stringFond) {
		this.personnage = personnage;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.stringFond = stringFond;

        this.transparency = new HashMap<>();
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
		
		if(personnage != null) {

			try {
				BufferedImage image = resize(ImageIO.read(new File(personnage.getCheminImage())), sizeX, sizeY);
				
				Graphics2D g2d = (Graphics2D) g;
				if(stringFond != null) {
					BufferedImage fond = ImageIO.read(new File(System.getProperty("user.dir") + "/images/util/" + stringFond));
					g2d.drawImage(fond, 0, 0, null);
				}
				
				g2d.translate(this.getWidth() / 2, this.getHeight() / 2);
				g2d.translate(-image.getWidth(null) / 2, -image.getHeight(null) / 2);
				g2d.drawImage(image, 0, 0, null);  
				if(transparency != null && !transparency.isEmpty()) {
					g2d.setComposite(AlphaComposite.SrcAtop);
					g2d.setColor(new Color(transparency.get("red"), transparency.get("green"), transparency.get("blue"), transparency.get("alpha")));
					g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
					g2d.dispose();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	

	public String getStringFond() {
		return stringFond;
	}

	public void setStringFond(String stringFond) {
		this.stringFond = stringFond;
	}

	public Personnage getPersonnage() {
		return personnage;
	}

	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}

	public HashMap<String, Integer> getTransparency() {
		return transparency;
	}

	public void setTransparency(HashMap<String, Integer> transparency) {
		this.transparency = transparency;
	}

}
