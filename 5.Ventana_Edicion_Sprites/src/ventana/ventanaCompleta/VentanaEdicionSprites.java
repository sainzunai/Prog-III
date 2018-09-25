package ventana.ventanaCompleta;

// Iconos descargados de https://www.freepik.com/free-icons

// Imports cl�sicos en casi cualquier programa de ventanas con Swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Import para ficheros
import java.io.*;
//Import para Java Collections y otras utilidades
import java.util.*;


/** Ejercicio de creaci�n de ventanas complejas
 * En este mismo paquete ver�s un fichero 
 *   <a href="file:./ej-sprites-1.jpg">ej-sprites-1.jpg</a>
 * con una propuesta de ventana. Dise�a con las variantes que veas oportuna esa ventana (en principio, sin programarla).<br/>
 * Hay otro fichero con marcas:
 *   <a href="file:./ej-sprites-2.jpg">ej-sprites-2.jpg</a>
 * sobre el que puedes observar los comentarios de cada parte de la ventana. Lo que se pretende es:<br/>
 *  - Objetivo de la ventana: editar y visualizar animaciones de sprites partiendo de gr�ficos ya existentes<br/>
 *  - Funcionamiento: al pulsar el bot�n (2) aparecer� una selecci�n de ficheros (JFileChooser) que permitir�
 *    seleccionar una CARPETA cualquiera del disco <br/>
 *  - Los ficheros png/jpg/gif [recomendado pngs transparentes] que est�n en esa carpeta aparecer�n en (1) <br/>
 *  - (1) es un JList con desplazamiento (dentro de un JScrollPane) donde aparecer�n los nombres de ficheros existentes en la carpeta <br/>
 *  - Entre el bot�n (2) y la lista (1) aparece el nombre de la carpeta seleccionada (JLabel)<br/>
 *  - Al cambiar la carpeta se crea una selecci�n nueva vac�a (lista (5)) <br/>
 *  - Al hacer doble click en un fichero de (1) se a�ade al final de la lista (5) <br/>
 *  - Al seleccionar un fichero en (5) se ve en el panel de preview (3), centrado con respecto al panel <br/>
 *  - Los componentes que hay a la derecha de la lista (5) tienen los siguientes significados: <br/>
 *  &nbsp;&nbsp;- Un deslizador (JSlider) de nivel de zoom, desde 10% hasta 200% <br/>
 *  &nbsp;&nbsp;- Un deslizador (JSlider) de rotaci�n, desde 0� hasta 360� <br/>
 *  &nbsp;&nbsp;- Un JTextField de desplazamiento a derecha de pixels (0 por defecto) <br/>
 *  &nbsp;&nbsp;- Un JTextField de desplazamiento abajo de pixels (0 por defecto) <br/>
 *  &nbsp;&nbsp;- Una indicaci�n de milisegundos de cada imagen dentro de la secuencia del sprite (100 por defecto) <br/>
 *  &nbsp;&nbsp;Estos valores indicados configuran cada imagen dentro de la secuencia (y por tanto deben almacenarse y restaurarse por cada imagen de la secuencia). Lo dem�s es para toda la secuencia:<br/>
 *  &nbsp;&nbsp;- Checkbox de ciclo (al visualizar la secuencia, tras la �ltima imagen vuelve a empezar la primera) <br/>
 *  &nbsp;&nbsp;- Botones de arriba/abajo que suben o bajan una posici�n la imagen seleccionada con respecto al resto de la lista (5) <br/>
 *  &nbsp;&nbsp;- Debajo de (5) dos JTextField de ancho y alto en p�xels de toda la secuencia <br/>
 *  &nbsp;&nbsp;- (6) Botones de nuevo/save/load para reiniciar, guardar o cargar una secuencia configurada (en save y load aparecer� un cuadro de di�logo que pide el nombre y localizaci�n) <br/>
 *  - (7) es un panel donde se define la animaci�n de movimiento del sprite en la "arena" (4). Su contenido es: <br/>
 *  &nbsp;&nbsp;- P�xels de origen x e y de la animaci�n dentro de la arena<br/>
 *  &nbsp;&nbsp;- Velocidad inicial de la animaci�n en p�xels por segundo, con un cuadro de texto y un JSlider entre 0 y 200<br/>
 *  &nbsp;&nbsp;- �ngulo inicial de la animaci�n en grados, con un cuadro de texto y un JSlider entre 0 y 90<br/>
 *  &nbsp;&nbsp;- Gravedad de la animaci�n, con un JSlider entre 0.0 y 10.0  (9.8 por defecto)<br/>
 *  - (8) es un panel donde se complementa la animaci�n de movimiento del sprite. Contenido: <br/>
 *  &nbsp;&nbsp;- Rotaci�n de la animaci�n en grados por segundo, con un cuadro de texto y un JSlider entre 0 y 360 (0 por defecto)<br/>
 *  &nbsp;&nbsp;- Zoom de la animaci�n en % por segundo, con un cuadro de texto y un JSlider entre 50 y 200 (100 por defecto)<br/>
 *  &nbsp;&nbsp;- Checkbox de si la animaci�n se hace c�clica (infinita) <br/>
 *  &nbsp;&nbsp;- Checkbox de si la animaci�n se hace con retorno (va y vuelve) <br/>
 *  - (9) son los tres botones de animaci�n:<br/>
 *  &nbsp;&nbsp;- Anima solo la secuencia sin mover el sprite en la arena<br/>
 *  &nbsp;&nbsp;- Hace el movimiento sin animar la secuencia (toma el sprite seleccionado actualmente)<br/>
 *  &nbsp;&nbsp;- Realiza a la vez las dos animaciones, la de secuencia y la de movimiento<br/>
 *  Otras anotaciones:<br/>
 *  - Valora los layouts m�s adecuados para cada panel. Intenta utilizar los m�s sencillos.<br/>
 *  - Los cuadros de texto que van con sliders asociados deben alimentarse mutuamente (si se cambia el slider cambia el texto y viceversa). Intenta hacerlo de una forma met�dica en lugar de repitiendo c�digo.<br/>
 *  - Haz una ventana interna (JInternalFrame) en lugar de un JFrame normal y as� podr�a integrarse con otras ventanas de la misma aplicaci�n<br/>
 *  - La Arena deber�a ocupar el m�ximo espacio posible de la ventana. El resto de los paneles el m�nimo necesario.<br/>
 *  - En la zona (9) se podr�a a�adir una JProgressBar que vaya mostrando la progresi�n de la animaci�n en curso<br/>
 *  - A�ade tooltips a las partes que consideres interesante<br/>
 *  - Si quieres probar los SplitPane puedes hacer uno entre (3) y (4)<br/>
 *  
 * <br/>
 * Programaci�n posterior:<br/>
 *  Hay que definir modelos de datos para las listas (�de qu� tipo cada JList y cada modelo?)<br/>
 *  Hilos para los plays (�cu�ntos? �cu�ndo?)<br/>
 *  Hay que usar alguna estructura (ArrayList por ejemplo) para guardar toda la configuraci�n de la secuencia (�varios arraylists o un arraylist de una clase nueva?)<br/>
 */
@SuppressWarnings("serial")  // Anotaci�n para evitar el warning del serial (relacionado con Serializable -tema 3-)
public class VentanaEdicionSprites extends JFrame {  // Vamos a definir una clase que permite instanciar ventanas y por ello hereda de ventana
	// Atributos de componentes -todos los componentes a los que vamos a acceder o modificar los definimos como atributos para que sean f�cilmente accesibles
	// Normalmente se hacen private pero aqu� los hacemos de paquete para que pueda utilizarlos directamente el controlador
	JList<File> lSprites;  // Lista de sprites (1)
	JLabel lCarpetaSel;  // Texto de la carpeta seleccionada
	JButton bBuscar;  // Bot�n de b�squeda de carpeta
	JPanel pPreview;  // Panel de preview (3)
	JPanel pArena;  // Panel de "arena" (4)
	JList<File> lSecuencia;  // Lista de secuencia (5)
	JTextField tfAncho;  // cuadro de texto p�xels de ancho
	JTextField tfAlto;  // cuadro de texto p�xels de alto
	JSlider slZoom;  // Slider de zoom
	JSlider slRotacion;  // Slider de rotaci�n
	JTextField tfoffsetX;  // cuadro de texto desplazamiento horizontal
	JTextField tfoffsetY;  // cuadro de texto desplazamiento vertical
	JTextField tfDuracion;  // cuadro de texto duraci�n en milisegundos
	JCheckBox cbCiclo;  // Checkbox de ciclo
	JButton bArriba;  // Bot�n de desplazamiento arriba en secuencia
	JButton bAbajo;  // Bot�n de desplazamiento abajo en secuencia
	JButton bNueva;  // Bot�n de nueva secuencia
	JButton bGuardar;  // Bot�n de guardar secuencia
	JButton bCargar;  // Bot�n de cargar secuencia
	JTextField tfOrigenX;  // cuadro de texto desplazamiento horizontal origen
	JTextField tfOrigenY;  // cuadro de texto desplazamiento vertical origen
	JTextField tfVelocidad;  // cuadro de texto velocidad origen
	JSlider slVelocidad;  // Slider de velocidad origen
	JTextField tfAngulo;  // cuadro de texto �ngulo origen
	JSlider slAngulo;  // Slider de �ngulo origen
	JTextField tfGravedad;  // cuadro de texto gravedad
	JSlider slGravedad;  // Slider de gravedad
	JTextField tfRotacionAnim;  // cuadro de texto rotaci�n animaci�n
	JSlider slRotacionAnim;  // Slider de rotaci�n animaci�n
	JTextField tfZoomAnim;  // cuadro de texto zoom animaci�n
	JSlider slZoomAnim;  // Slider de zoom animaci�n
	JCheckBox cbCicloAnim;  // Checkbox de ciclo animaci�n
	JCheckBox cbRetornoAnim;  // Checkbox de retorno animaci�n
	JButton bSecuencia;  // Bot�n de play de secuencia
	JButton bAnim;  // Bot�n de play de animaci�n
	JButton bSecuenciaAnim;  // Bot�n de play de secuencia+animaci�n
	// Atributos de modelos de datos asociados a los componentes
	DefaultListModel<File> mSprites;
	DefaultListModel<File> mSecuencia;
	
	// Controlador de la ventana
	private ControladorVentanaSprites miControlador;
	
	/** Constructor de ventana de edici�n de sprites<br/>
	 * Inicializa y asocia un controlador ControladorVentanaSprites
	 */
	public VentanaEdicionSprites() {
		// Inicializa el controlador
		miControlador = new ControladorVentanaSprites( this );
		
		// Inicializaci�n de elementos generales de la ventana
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE ); // Al cerrar la ventana se hace un dispose()
		setTitle( "Edici�n de sprites - Prog. III" );

		// Creaci�n de contenedores con sus layouts
		pPreview = new JPanel( null );
		pArena = new JPanel( null );
		JPanel pi1 = new JPanel( new BorderLayout() );
		JPanel pi2 = new JPanel( new GridLayout(2,1) );
		JPanel pi3 = new JPanel();
		JPanel pC = new JPanel( new BorderLayout() );
		JSplitPane pCSup = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
		JPanel pCSup1 = new JPanel( new BorderLayout() );
		JPanel pCSup2 = new JPanel( new BorderLayout() );
		JPanel pCInf = new JPanel( new BorderLayout() );
		JPanel pSec = new JPanel( new BorderLayout() );
		JPanel pSec1 = new JPanel( new FlowLayout( FlowLayout.RIGHT ) );
			JPanel pSec1a = new JPanel( new GridLayout( 2, 1 ) );
			JPanel pSec1b = new JPanel( new FlowLayout( FlowLayout.RIGHT ) );
			JPanel pSec1c = new JPanel( new FlowLayout( FlowLayout.RIGHT ) );
		JPanel pSec2 = new JPanel( new BorderLayout() );
			JPanel pSec2a = new JPanel( new GridLayout( 6, 1 ) );
			JPanel pSec2a1 = new JPanel( new FlowLayout( FlowLayout.LEFT, 1, 0 ) ); // Offset horizontal entre elementos 1 px, vertical 0 px
			JPanel pSec2a2 = new JPanel( new FlowLayout( FlowLayout.LEFT, 1, 0 ) );
			JPanel pSec2a3 = new JPanel( new FlowLayout( FlowLayout.LEFT, 1, 0 ) );
			JPanel pSec2a4 = new JPanel( new FlowLayout( FlowLayout.LEFT, 1, 0 ) );
			JPanel pSec2a5 = new JPanel( new FlowLayout( FlowLayout.CENTER, 1, 0 ) );
			JPanel pSec2a6 = new JPanel( new FlowLayout( FlowLayout.CENTER, 1, 0 ) );
		JPanel pSec3 = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		JPanel pAnim = new JPanel( new BorderLayout() );
		JPanel pAnim1 = new JPanel( new BorderLayout() );
		JPanel pAnim2 = new JPanel( new BorderLayout() );
			JPanel pAnim2a = new JPanel( new GridLayout( 4, 1 ) );
			JPanel pAnim2b = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
			JPanel pAnim2c = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
			JPanel pAnim2d = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
			JPanel pAnim2e = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		JPanel pAnim3 = new JPanel( new BorderLayout() );
			JPanel pAnim3a = new JPanel( new GridLayout( 4, 1 ) );
			JPanel pAnim3b = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
			JPanel pAnim3c = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
			JPanel pAnim3d = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
			JPanel pAnim3e = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		JPanel pAnim4 = new JPanel();
		
		// Creaci�n de modelos de datos para los componentes
		mSprites = new DefaultListModel<>();
		mSecuencia = new DefaultListModel<>();
		
		// Creaci�n de componentes
		lSprites = new JList<>( mSprites );
		JScrollPane spSprites = new JScrollPane( lSprites );
		lCarpetaSel = new JLabel( " " ); // Si tiene alg�n espacio ocupa el tama�o necesario de alto (luego se cambiar�)
		bBuscar = new JButton( "Buscar" );
		lSecuencia = new JList<>( mSecuencia );
		JScrollPane spSecuencia = new JScrollPane( lSecuencia );
		tfAncho = new JTextField( "200", 3 );
		tfAlto = new JTextField( "200", 3 );
		slZoom = new JSlider( JSlider.HORIZONTAL, 10, 100, 100 );
		slRotacion = new JSlider( JSlider.HORIZONTAL, 0, 360, 0 );
		tfoffsetX = new JTextField( "0", 4 );
		tfoffsetY = new JTextField( "0", 4 );
		tfDuracion = new JTextField( "100", 4 );
		cbCiclo = new JCheckBox( "ciclo" );
		bArriba = new JButton( new ImageIcon( VentanaEdicionSprites.class.getResource( "img/arrow-up24.png" )));
		bAbajo = new JButton( new ImageIcon( VentanaEdicionSprites.class.getResource( "img/arrow-down24.png" )));
		bNueva = new JButton( new ImageIcon( VentanaEdicionSprites.class.getResource( "img/new24.png" )));
		bGuardar = new JButton( new ImageIcon( VentanaEdicionSprites.class.getResource( "img/save24.png" )));
		bCargar = new JButton( new ImageIcon( VentanaEdicionSprites.class.getResource( "img/load24.png" )));
		tfOrigenX = new JTextField( "0", 3 );
		tfOrigenY = new JTextField( "100", 3 );
		tfVelocidad = new JTextField( "0", 3 );
		slVelocidad = new JSlider( JSlider.HORIZONTAL, 0, 200, 0 );
		tfAngulo = new JTextField( "0", 2 );
		slAngulo = new JSlider( JSlider.HORIZONTAL, 0, 90, 0 );
		tfGravedad = new JTextField( "9.8", 3 );
		slGravedad = new JSlider( JSlider.HORIZONTAL, 0, 100, 98 );
		tfRotacionAnim = new JTextField( "0", 3 );
		slRotacionAnim = new JSlider( JSlider.HORIZONTAL, 0, 360, 0 );
		tfZoomAnim = new JTextField( "100", 3 );
		slZoomAnim = new JSlider( JSlider.HORIZONTAL, 50, 200, 100 );
		cbCicloAnim = new JCheckBox( "Ciclo" );
		cbRetornoAnim = new JCheckBox( "Retorno" );
		bSecuencia = new JButton( "Sec", new ImageIcon( VentanaEdicionSprites.class.getResource( "img/play24.png" )));
		bAnim = new JButton( "Anim", new ImageIcon( VentanaEdicionSprites.class.getResource( "img/play24.png" )));
		bSecuenciaAnim = new JButton( "Sec+Anim", new ImageIcon( VentanaEdicionSprites.class.getResource( "img/play24.png" )));

		// Asignaci�n de componentes a contenedores
		add( pi1, BorderLayout.WEST );
			pi1.add( new JLabel( "Sprites" ) );
			pi1.add( spSprites, BorderLayout.CENTER );
			pi1.add( pi2, BorderLayout.SOUTH );
				pi2.add( lCarpetaSel, BorderLayout.NORTH );
				pi2.add( pi3, BorderLayout.SOUTH );
					pi3.add( bBuscar );
		add( pC, BorderLayout.CENTER );
			pC.add( pCSup, BorderLayout.CENTER );
				pCSup.setLeftComponent( pCSup1 );
					pCSup1.add( pPreview, BorderLayout.CENTER );
				pCSup.setRightComponent( pCSup2 );
					pCSup2.add( pArena, BorderLayout.CENTER );
			pC.add( pCInf, BorderLayout.SOUTH );
				pCInf.add( pSec, BorderLayout.WEST );
					pSec.add( pSec1, BorderLayout.NORTH );
						pSec1.add( pSec1a );
							pSec1a.add( pSec1b );
								pSec1b.add( slZoom );
								pSec1b.add( new JLabel( new ImageIcon( VentanaEdicionSprites.class.getResource( "img/zoom24.png" )) ) );
							pSec1a.add( pSec1c );
								pSec1c.add( slRotacion );
								pSec1c.add( new JLabel( new ImageIcon( VentanaEdicionSprites.class.getResource( "img/rotate24.png" )) ) );
					pSec.add( pSec2, BorderLayout.CENTER );
						pSec2.add( spSecuencia, BorderLayout.CENTER );
						pSec2.add( pSec2a, BorderLayout.EAST );
							pSec2a.add( pSec2a1 );
								pSec2a1.add( tfoffsetX );
								pSec2a1.add( new JLabel( new ImageIcon( VentanaEdicionSprites.class.getResource( "img/angle-arrow-right24.png" )) ));
							pSec2a.add( pSec2a2 );
								pSec2a2.add( tfoffsetY);
								pSec2a2.add( new JLabel( new ImageIcon( VentanaEdicionSprites.class.getResource( "img/angle-arrow-down24.png" )) ));
							pSec2a.add( pSec2a3 );
								pSec2a3.add( tfDuracion );
								pSec2a3.add( new JLabel( "msg" ) );
							pSec2a.add( pSec2a4 );
								pSec2a4.add( cbCiclo );
							pSec2a.add( pSec2a5 );
								pSec2a5.add( bArriba );
								pSec2a5.add( bAbajo );
							pSec2a.add( pSec2a6 );
								pSec2a6.add( bNueva );
								pSec2a6.add( bGuardar );
								pSec2a6.add( bCargar );
					pSec.add( pSec3, BorderLayout.SOUTH );
						pSec3.add( tfAncho );
						pSec3.add( new JLabel( new ImageIcon( VentanaEdicionSprites.class.getResource( "img/doublearrow-horizontal24.png" )) ));
						pSec3.add( tfAlto );
						pSec3.add( new JLabel( new ImageIcon( VentanaEdicionSprites.class.getResource( "img/doublearrow-vertical24.png" )) ));
				pCInf.add( pAnim, BorderLayout.CENTER );
					pAnim.add( pAnim1, BorderLayout.CENTER );
						pAnim1.add( pAnim2, BorderLayout.WEST );
							pAnim2.add( pAnim2a, BorderLayout.NORTH );
								pAnim2a.add( pAnim2b );
									pAnim2b.add( new JLabel( "Origen" ) );
									pAnim2b.add( tfOrigenX );
									pAnim2b.add( new JLabel( new ImageIcon( VentanaEdicionSprites.class.getResource( "img/arrow-right24.png" )) ) );
									pAnim2b.add( tfOrigenY );
									pAnim2b.add( new JLabel( new ImageIcon( VentanaEdicionSprites.class.getResource( "img/arrow-down24.png" )) ) );
								pAnim2a.add( pAnim2c );
									pAnim2c.add( new JLabel( "V.ini" ) );
									pAnim2c.add( tfVelocidad );
									pAnim2c.add( new JLabel( "px/sg" ) );
									pAnim2c.add( slVelocidad );
								pAnim2a.add( pAnim2d );
									pAnim2d.add( new JLabel( "      " ) );
									pAnim2d.add( tfAngulo );
									pAnim2d.add( new JLabel( "�" ) );
									pAnim2d.add( new JLabel( new ImageIcon( VentanaEdicionSprites.class.getResource( "img/angle24.png" )) ) );
									pAnim2d.add( slAngulo );
								pAnim2a.add( pAnim2e );
									pAnim2e.add( new JLabel( "Gravedad" ) );
									pAnim2e.add( tfGravedad );
									pAnim2e.add( new JLabel( "px/sg2" ) );
									pAnim2e.add( slGravedad );
						pAnim1.add( pAnim3, BorderLayout.CENTER );
							pAnim3.add( pAnim3a, BorderLayout.NORTH );
								pAnim3a.add( pAnim3b );
									pAnim3b.add( new JLabel( "Rotaci�n" ) );
									pAnim3b.add( tfRotacionAnim );
									pAnim3b.add( new JLabel( "�/sg" ) );
									pAnim3b.add( slRotacionAnim );
								pAnim3a.add( pAnim3c );
									pAnim3c.add( new JLabel( "Zoom" ) );
									pAnim3c.add( tfZoomAnim );
									pAnim3c.add( new JLabel( "%" ) );
									pAnim3c.add( slZoomAnim );
								pAnim3a.add( pAnim3d );
									pAnim3d.add( cbCicloAnim );
								pAnim3a.add( pAnim3e );
									pAnim3e.add( cbRetornoAnim );
					pAnim.add( pAnim4, BorderLayout.SOUTH );
						pAnim4.add( bSecuencia );
						pAnim4.add( bAnim );
						pAnim4.add( bSecuenciaAnim );

		// Formato de contenedores
		pi1.setBorder( BorderFactory.createTitledBorder( "Sprites") );
		pCSup1.setBorder( BorderFactory.createTitledBorder( "Preview 100%") );
		pCSup2.setBorder( BorderFactory.createTitledBorder( "Arena") );
		pSec.setBorder( BorderFactory.createTitledBorder( "Secuencia") );
		pAnim.setBorder( BorderFactory.createTitledBorder( "Animaci�n") );
		pAnim2.setBorder( BorderFactory.createRaisedBevelBorder() );
		pAnim3.setBorder( BorderFactory.createRaisedBevelBorder() );
		pPreview.setPreferredSize( new Dimension( 200, 200 ) );
		pPreview.setBackground( Color.WHITE );
		pArena.setBackground( Color.WHITE );
		// Formato de componentes
		slZoom.setPreferredSize( new Dimension( 100, 25 ) );
		slRotacion.setPreferredSize( new Dimension( 100, 25 ) );
		slVelocidad.setPreferredSize( new Dimension( 100, 25 ) );
		slAngulo.setPreferredSize( new Dimension( 100, 25 ) );
		slGravedad.setPreferredSize( new Dimension( 100, 25 ) );
		slZoomAnim.setPreferredSize( new Dimension( 100, 25 ) );
		slRotacionAnim.setPreferredSize( new Dimension( 100, 25 ) );
		spSprites.setPreferredSize( new Dimension( 150, 400 ));
		spSecuencia.setPreferredSize( new Dimension( 150, 200 ));
		
		// �ltimos elementos de la ventana
		pack(); // Pone a la ventana el tama�o m�nimo que necesite el layout
		// setSize( 1200, 800 ); // Tama�o inicial de la ventana - ser�a una alternativa. El pack() del final pone el tama�o m�nimo que necesite el layout
		setLocationRelativeTo( null ); // Posici�n relativa al escritorio (centrada en el escritorio)
		
		// Inicializaci�n de los gestores de eventos (usando el controlador)
		bBuscar.addActionListener( new ActionListener( ) {
			@Override
			public void actionPerformed(ActionEvent e) {
				miControlador.clickBBuscar();
			}
		});
		slAngulo.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				miControlador.scrollAngulo();
			}
		});
		
		lSprites.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					
				}
			}
		});
		
	}
	
	
	
	/** Devuelve el controlador de la ventana
	 * @return
	 */
	public ControladorVentanaSprites getController() {
		return miControlador;
	}
}
