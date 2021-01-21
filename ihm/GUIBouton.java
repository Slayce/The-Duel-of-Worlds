package ihm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/** Classe qui hérite de JButton et qui a l'aspect souhaité pour tous les boutons du jeu
 * @author Remi
 */
public class GUIBouton extends JButton {
	private static final long serialVersionUID = 6160169554395244618L;
	
	static BufferedImage img;
	private final Font font = new Font("Poor Richard", Font.BOLD, 30);
	private String name;
	
	static void initTexture() throws IOException {
		img = jeu.FilesManager.loadGUIImage("button.png");
	}
	
	public GUIBouton(String label) {
        super(label);
        name = label;
        this.setFont(font);
        this.setForeground(Color.WHITE);
        
        if (img == null)
        	throw new NullPointerException("GUIBouton n'a pas été initialisé :  veuillez appeler la méthode statique initTexture().");
        
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        
		Image scaledImg = img.getScaledInstance(this.getPreferredSize().width, this.getPreferredSize().height, Image.SCALE_SMOOTH);
		this.setIcon(new ImageIcon(scaledImg));
    }
	
	
	@Override
	public void setText(String text) {
		super.setText(text);
		name = text;
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
	    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);   // dessin de l'image
	    
	    // Calcul de la taille du texte pour l'afficher et le centrer
	    FontMetrics metrics = getFontMetrics(font);
	    Rectangle rect = this.getBounds();
	    int x = (int) ((rect.width - metrics.stringWidth(this.name)) / 2.0);
	    int y = (this.getHeight() / 2) + 5;
	    g2d.drawString(this.name, x, y);
	}
}
