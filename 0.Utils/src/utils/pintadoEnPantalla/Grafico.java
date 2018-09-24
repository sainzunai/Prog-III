package utils.pintadoEnPantalla;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Grafico extends JFrame {

	private JPanel panelTiro;
	private static Graphics2D graphics;
	private boolean dibujadoInmediato = true;
	private BufferedImage buffer;

	@SuppressWarnings("serial")
	public Grafico() {

		///////////////////////////////////////////////
		// ↓↓↓↓↓↓↓ CONFIGUARCION DE LA VENTANA ↓↓↓↓↓↓↓↓
		///////////////////////////////////////////////

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		// setContentPane(contentPane); ---NO TOCAR
		buffer = new BufferedImage(2000, 1500, BufferedImage.TYPE_INT_ARGB);
		graphics = buffer.createGraphics();
		graphics.setPaint(Color.white);
		graphics.fillRect(0, 0, 2000, 1500);
		panelTiro = new JPanel() {
			{
				setLayout(new BorderLayout());
			}

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				((Graphics2D) g).drawImage(buffer, null, 0, 0);
			}
		};

		panelTiro.setBorder(BorderFactory.createLineBorder(Color.BLUE));

		/////////////////////////////////////////////////////////////
		// ↓↓↓↓↓↓↓ ASIGNACION DE COMPONENTES A CONTENEDORES ↓↓↓↓↓↓↓↓
		////////////////////////////////////////////////////////////

		getContentPane().add(panelTiro, BorderLayout.CENTER);

	}

	private static void disparar(double x1, double y1, double hInit, double gravedad, float combustible) {
		g.dibujaCirculo(50, 50, 5.0, 5.0f, Color.blue); // Dibuja punto
	}

	private void dibujaCirculo(double x, double y, double radio, float grosor, Color color) {
		graphics.setColor(Color.black);
		graphics.setStroke(new BasicStroke(grosor));
		graphics.drawOval((int) Math.round(x - radio), (int) Math.round(y - radio), (int) Math.round(radio * 2),
				(int) Math.round(radio * 2));
		if (dibujadoInmediato)
			panelTiro.repaint();
	}

	private static Grafico g;

	public static void main(String[] args) {
		g = new Grafico();
		g.setVisible(true);
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, g.getWidth() + 2, g.getHeight() + 2);
		g.disparar(10, 10, 10, 1, 10);
	}
}
