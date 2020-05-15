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
	
	/** Le personnage a afficher dans la case */
	private Personnage personnage;
	/** La taille en X de l'image */
	private Integer sizeX;
	/** La taille en Y de l'image */
	private Integer sizeY;
	/** La couleur de fond de l'image */
	private String stringFond;
	/** Les attributs de transparence a appliquer sur l'image */
	private HashMap<String, Integer> transparency;


	/**
	 * Constructeur de notre JPanel
	 * @param personnage Le personnage a afficher
	 * @param sizeX La taille en X
	 * @param sizeY La taille en Y
	 * @param stringFond La couleur de fond (null si pas de fond)
	 */
	public CaseImage(Personnage personnage, int sizeX, int sizeY, String stringFond) {
		this.personnage = personnage;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.stringFond = stringFond;

        this.transparency = new HashMap<>();
	}
	
	/**
	 * Permet de changer la taille d'une image
	 * @param img L'image dont on souhaite changer la taille
	 * @param newW La nouvelle taille en X
	 * @param newH La nouvelle taille en Y
	 * @return L'image a la nouvelle taille
	 */
	private BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
	/**
	 * Méthode pour formater notre JPanel avec l'image a la bonne taille et avec le bon fond
	 */
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
	
	/**
	 * Setter de la couleur du fond de notre JPanel
	 * @param stringFond La couleur de fond souhaitée
	 */
	public void setStringFond(String stringFond) {
		this.stringFond = stringFond;
	}

	/**
	 * Setter du personnage de notre JPanel
	 * @param personnage Le personnage souhaité
	 */
	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}
	
	/**
	 * Permet d'appliquer une couleur de transparence sur l'image du personnage
	 * @param transparency La HashMap de transparence souhaitée (cf. Util.java)
	 */
	public void setTransparency(HashMap<String, Integer> transparency) {
		this.transparency = transparency;
	}

}
