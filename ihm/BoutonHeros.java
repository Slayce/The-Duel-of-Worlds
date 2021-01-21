package ihm;

import javax.swing.*;

import univers.Heros;

import java.awt.*;

public class BoutonHeros extends JButton {
	private static final long serialVersionUID = 9211672957933277307L;
	
	private Image image;
	private Heros heros;
	
	public BoutonHeros(Image img, Heros heros, int w, int h) {
		Dimension d = new Dimension(w, h);
        setMinimumSize(d);
        setMaximumSize(d);
        setPreferredSize(d);
		this.image = img;
		this.heros = heros;

        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
	}
	
	public Heros getHeros() {
		return this.heros;
	}

	@Override
	public void paintComponent(Graphics g){		
		if(image != null) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);   // dessin de l'image
		}
	}
}