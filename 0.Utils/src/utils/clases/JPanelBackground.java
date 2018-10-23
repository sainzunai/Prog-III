package utils.clases;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelBackground extends JPanel {

	private static final long serialVersionUID = 1L;
	 private Image fondo;
	 
	 public JPanelBackground(String imagenFondo) {
	  fondo = new ImageIcon(imagenFondo).getImage();
	 }
	 
	 @Override
	 public void paintComponent(Graphics g) {
	    	g.drawImage(fondo, 0, 0, this.getWidth(), this.getHeight(), this);
	 }

}


