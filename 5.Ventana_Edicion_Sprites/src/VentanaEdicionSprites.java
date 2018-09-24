import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaEdicionSprites extends JFrame {
	JPanel pSprites;
	JPanel pPreview;
	JPanel pArena;
	JPanel pSecuencia;   
	JPanel pAnimacion;
	JPanel pComplementoAnimacion;
	JPanel pBotones;
	
	JPanel pCentro;
	JPanel pNorte;
	JPanel pSur;
	
	public VentanaEdicionSprites() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ventana Edicion Sprites");
		setSize(1280, 720);
		
		//INICIALIZAR VARIABLES
		pSprites = new JPanel();
		pPreview = new JPanel();
		pArena = new JPanel();
		pSecuencia = new JPanel();
		pAnimacion = new JPanel();
		pComplementoAnimacion = new JPanel();
		pBotones = new JPanel();
		
		pCentro = new JPanel();
		pNorte = new JPanel();
		pSur = new JPanel();
		
		//PINTADO DE LOS BORDES PARA VISUALIZAR MEJOR
		
		pSprites.setBorder(BorderFactory.createLineBorder(Color.black));
		pCentro.setBorder(BorderFactory.createLineBorder(Color.yellow));
		pNorte.setBorder(BorderFactory.createLineBorder(Color.blue));
		pSur.setBorder(BorderFactory.createLineBorder(Color.magenta));
		pPreview.setBorder(BorderFactory.createLineBorder(Color.white));
		pArena.setBorder(BorderFactory.createLineBorder(Color.white));  
//		pSecuencia.setBorder(BorderFactory.createLineBorder(Color.white));  
//		pAnimacion.setBorder(BorderFactory.createLineBorder(Color.white));  
//		pComplementoAnimacion.setBorder(BorderFactory.createLineBorder(Color.white));  
//		pBotones.setBorder(BorderFactory.createLineBorder(Color.white));  
		
		
		//ASIGNAR COMPONENTES A CONTENEDORES
		this.getContentPane().add(pSprites, BorderLayout.WEST);
		this.getContentPane().add(pCentro, BorderLayout.CENTER);
		
		pCentro.add(pNorte, BorderLayout.NORTH);
		pCentro.add(pSur, BorderLayout.SOUTH);
		
		pNorte.setLayout(new GridLayout(0, 2));	//divido el panel norte en dos
		pNorte.add(pPreview);
		pNorte.add(pArena);
		
		
		
		pSprites.add(new JLabel("Sprites"));
		pPreview.add(new JLabel("PREVIEW"));
		pArena.add(new JLabel("Arena"));
}

	public static void main(String[] args) {
			VentanaEdicionSprites v1 = new VentanaEdicionSprites();
			v1.setVisible(true);
			
			
	}

}
