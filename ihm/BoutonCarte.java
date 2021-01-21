package ihm;

import javax.swing.*;

import univers.Carte;
import univers.Serviteur;

import java.awt.*;

public class BoutonCarte extends JButton {
	private static final long serialVersionUID = 4149826761495475348L;
	
	private int position;
	private Carte carte;
	private Image image;
	private boolean vide;
	private boolean aDejaAttaque;

	public BoutonCarte(int pos, int w, int h, boolean vide) {
		this.position = pos;
		this.vide = vide;
		this.aDejaAttaque = false;
		this.carte = null;
		this.image = null;
		
		this.setSize(w, h);

        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
	}
	
	public BoutonCarte(Carte carte, int pos, int w, int h, boolean vide) {
		this(pos, w, h, vide);
		this.carte = carte;
		this.image = carte.getImage();
	}
	
	public boolean estVide(){
		return vide;
	}
	
	public boolean aDejaAttaque(){
		return this.aDejaAttaque;
	}
	
	public void setAAttaque(boolean aAttaque){
		this.aDejaAttaque = aAttaque;
	}
	
	public int getPos() {
		return this.position;
	}
	
	public Carte getCarte() {
		return this.carte;
	}
	
	public void setImage(Image newImage) {
		this.image = newImage;
	}
	
	public void setCarte(Carte newC) {
		this.carte = newC;
	}

	@Override
	public void paintComponent(Graphics g){		
		if(carte != null && image != null) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);   // dessin de l'image
			
			// Affichage des PVs et Attaque
			Rectangle rect = this.getBounds();
			g2d.drawString("" + carte.getDegats(), (int)(1.5/12.0 * rect.width), (int)(0.075 * rect.height));
			g2d.drawString("" + carte.getCout(), (int)(11/12.0 * rect.width), (int)(0.98 * rect.height));
			
			if (carte instanceof Serviteur)
				g2d.drawString("" + ((Serviteur)carte).getPV(), (int)(10/12.0 * rect.width), (int)(0.075 * rect.height));	
			
			g2d.setFont(new Font("Poor Richard", Font.BOLD, 10));
			g2d.setColor(Color.white);
		}
	}
}