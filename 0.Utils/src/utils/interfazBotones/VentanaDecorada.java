package utils.interfazBotones;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.omg.PortableServer.ServantRetentionPolicyValue;

public class VentanaDecorada extends JFrame{

	private VentanaDecorada() {
		//configuracion inicial de la ventana
		this.setTitle("PRUEBA - Ventana decorada");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//creacion de componentes
		JPanel pGeneral = new JPanel();
		
		JPanel pG1 = new JPanel();
		JPanel pG2 = new JPanel();
		JPanel pG3 = new JPanel();
		JPanel pG4 = new JPanel();
		JPanel pG5 = new JPanel();
		
		pGeneral.setLayout(new GridLayout(5, 1));
		
		pGeneral.add(pG1);
		pGeneral.add(pG2);
		pGeneral.add(pG3);
		pGeneral.add(pG4);
		pGeneral.add(pG5);
		
		JLabel l1 = new JLabel("KAAAAII");
		l1.setFont(new Font("Bauhaus 93", Font.CENTER_BASELINE, 20));
		
		
		pG1.add(l1);
		pG2.add(new JLabel("ESTO"));
		pG3.add(new JLabel("ES"));
		pG4.add(new JLabel("UNA"));
		pG5.add(new JLabel("PRUEBA"));
		

		//ubicacion de componentes
		this.add(pGeneral, BorderLayout.WEST);
		
		//edicion de componenetes
		pG1.setBackground(new Color(176, 196, 222));	//fuente de colores: https://didita9.es.tl/%3Ccenter%3ETabla-de-Colores%3C-s-center%3E.htm
		pG2.setBackground(new Color(30, 144, 255));
		pG3.setBackground(Color.LIGHT_GRAY);
		pG4.setBackground(Color.LIGHT_GRAY);
		pG5.setBackground(Color.LIGHT_GRAY);
		
		pG1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		pG2.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		pG3.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		pG4.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		pG5.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX() + " X " + e.getY());
				System.out.println("PG1: " + pG1.getBounds() + (pG1.getBounds().getY() + pG1.getBounds().getHeight()));
				System.out.println("PG2: " + pG2.getBounds() + (pG2.getBounds().getY() + pG2.getBounds().getHeight()));
				//click panel 1
				if(e.getX()<pG1.getBounds().getWidth() && e.getY()<pG1.getBounds().getHeight()) {
					pG1.setBackground(Color.LIGHT_GRAY);
//					System.out.println("e.getY()<pG1.getBounds().getHeight(): " + e.getY() + "<" + pG1.getBounds().getHeight() + " == True");
				}
				if(e.getX()<pG2.getBounds().getWidth() && e.getY() > pG2.getBounds().getY() && e.getY() < (pG2.getBounds().getY() + pG2.getBounds().getHeight())) { //tiene que estar en tre el origen del panel y el origen + altura del panel
					pG2.setBackground(Color.LIGHT_GRAY);
//					System.out.println("e.getY()<pG2.getBounds().getHeight(): " + e.getY() + "<" + pG2.getBounds().getHeight() + " == True");
				
				}
				if(e.getX()<pG3.getBounds().getWidth() && e.getY()<pG3.getBounds().getHeight()) {
					pG3.setBackground(Color.LIGHT_GRAY);
					
				}
			}
		});
	}
	public static void main(String[] args) {

		VentanaDecorada v1 = new VentanaDecorada();
		v1.setVisible(true);
		v1.setSize(1920, 1080);
	}

}
