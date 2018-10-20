package utils.botonQueLate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class VentanaBoton extends JFrame {

	JPanel pFondo;
	JPanel pTitulo;

	JButtonAnimado bJugar;

	public VentanaBoton() {
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pFondo = new JPanel();
		pTitulo = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(300, 300);
			};
		};

		bJugar = new JButtonAnimado(100, 100, 100, 100, "huuula", this, 10, 30);
		pFondo.setLayout(null);

		this.add(pFondo, BorderLayout.CENTER);
		pFondo.add(pTitulo);
		pFondo.add(bJugar);

		pTitulo.setBounds(500, 500, 500, 500);
		pFondo.setBackground(Color.BLACK);

		pFondo.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		pTitulo.setBorder(BorderFactory.createLineBorder(Color.magenta));

		pTitulo.add(new JLabel("HOOOOOOOOOOOOOOOOOOOOOOLA"));
		pTitulo.setBorder(javax.swing.BorderFactory.createTitledBorder("Javi es feo"));	//setear titulo
		
	
	}
	public static void main(String[] args) {

		VentanaBoton v1 = new VentanaBoton();
		v1.setVisible(true);
		v1.setSize(1920, 1080);
	}

}
