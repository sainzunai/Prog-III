package utils.botonQueLate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JButtonAnimado extends JButton {

	boolean ventanaAbierta = true;	//cuando se cierre la ventana se pondra false para parar el hilo

	int posX;
	int posY;
	int dimAlto;
	int dimAncho;
	
	int recorrido;
	int velocidad;

	String mensaje;

	JButton bJugar;

	JFrame ventana;

	/**
	 * BOTON SENCILLO
	 * @param posX	posicion inicial X	
	 * @param posY	posicion inicial X
	 * @param dimAncho	anchura del boton
	 * @param dimAlto	altura del boton
	 * @param mensajeBoton	mensaje en el boton
	 * @param ventana	mandar la ventana en la que se va a implementar. VALE CON PONER "this".
	 */
	public JButtonAnimado(int posX, int posY, int dimAncho, int dimAlto, String mensajeBoton, JFrame ventana) {
		// inicializo variables
		this.posX = posX;
		this.posY = posY;
		this.mensaje = mensajeBoton;
		this.ventana = ventana;
		this.dimAlto = dimAlto;
		this.dimAncho = dimAncho;

		setText(mensajeBoton);

		this.setBounds(this.posX, this.posY, this.dimAncho, this.dimAlto);	//establecer posicion inicial del boton

		ventana.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent arg0) {
				ventanaAbierta = false;
			}
		});
		animacionBoton();
	}

	private void animacionBoton() {
		Thread hilo = new Thread() {
			@Override
			public void run() {
				while (ventanaAbierta) {
					try {
						int x = 0;
						int y = 0;
						for (int i = 0; i < 100; i++) {
							x += 1;
							y += 1;
							setBounds(posX - x, posY - y, dimAncho + 2 * x, dimAlto + 2 * y); // posicion pixel -
																								// tamanyo
							System.out.println("Boton: " + mensaje + " Grande - XY= " + y + " i=" + i);
							Thread.sleep(20); // ESPERAR LO QUE HAGA FALTA
						}

						for (int i = 0; i < 100; i++) {

							x -= 1;
							y -= 1;
							setBounds(posX - x, posY - y, dimAncho + 2 * x, dimAlto + 2 * y);
							System.out.println("Boton: " + mensaje + " Pequeño - XY= " + y + " i=" + i);
							Thread.sleep(20); // ESPERAR LO QUE HAGA FALTA
						}

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		hilo.start();
	}
	/**
	  @param posX	- posicion inicial X	
	 * @param posY	- posicion inicial X
	 * @param dimAncho	- anchura del boton
	 * @param dimAlto	- altura del boton
	 * @param mensajeBoton	- mensaje en el boton
	 * @param ventana	- mandar la ventana en la que se va a implementar. VALE CON PONER "this".
	 * @param recorrido	- el recorrido que va a hacer el boton. Mayor valor, mayor recorrido. Recomendado 10.
	 * @param velocidad	-velocidad con la que va a latir. 10: rapido; 60: despacio. Recomendado 30.
	 */
	public JButtonAnimado(int posX, int posY, int dimAncho, int dimAlto, String mensajeBoton, JFrame ventana, int recorrido, int velocidad) {
		// inicializo variables
		this.posX = posX;
		this.posY = posY;
		this.mensaje = mensajeBoton;
		this.ventana = ventana;
		this.dimAlto = dimAlto;
		this.dimAncho = dimAncho;
		this.recorrido = recorrido;
		this.velocidad = velocidad;
		
		setText(mensajeBoton);

		this.setBounds(this.posX, this.posY, this.dimAncho, this.dimAlto);	//establecer posicion inicial del boton

		ventana.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent arg0) {
				ventanaAbierta = false;
			}
		});
		animacionBoton2();
	}

	private void animacionBoton2() {
		Thread hilo = new Thread() {
			@Override
			public void run() {
				while (ventanaAbierta) {
					try {
						int x = 0;
						int y = 0;
						for (int i = 0; i < recorrido; i++) {
							x += 1;
							y += 1;
							setBounds(posX - x, posY - y, dimAncho + 2 * x, dimAlto + 2 * y); // posicion pixel -
																								// tamanyo
							System.out.println("Boton: " + mensaje + " Grande - XY= " + y + " i=" + i);
							Thread.sleep(velocidad); // ESPERAR LO QUE HAGA FALTA
						}

						for (int i = 0; i < recorrido; i++) {

							x -= 1;
							y -= 1;
							setBounds(posX - x, posY - y, dimAncho + 2 * x, dimAlto + 2 * y);
							System.out.println("Boton: " + mensaje + " Pequeño - XY= " + y + " i=" + i);
							Thread.sleep(velocidad); // ESPERAR LO QUE HAGA FALTA
						}

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		hilo.start();
	}
}
