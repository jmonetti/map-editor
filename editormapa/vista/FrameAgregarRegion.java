package vista;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalBorders;

import controlador.ControladorBotonActualizar;

public class FrameAgregarRegion extends JFrame {
	
	//Componentes
	private JLayeredPane panel;
	private JTextField[] datos;
	private JLabel[] etiquetas;
	private JButton actualizar;
	
	private static int CantidadDatos = 2;
	
	private static FrameAgregarRegion Instance = null; //Instancia unica del frame
	
	/**
	 * Inicializa la instancia del frame
	 * @param titulo Titulo de la ventana
	 */
	private FrameAgregarRegion(String titulo){
		//Llamo al constructor del padre
		super(titulo);
		this.setResizable(false); //Hago que no pueda cambiar su tamaņo

		//Creo el panel
		panel = new JLayeredPane();
		//Defino el tamaņo de la pantalla
		Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		//Establezco el tamaņo y las coordenadas
	    this.setBounds(tamanoPantalla.width / 3, tamanoPantalla.height / 3, tamanoPantalla.width / 3, tamanoPantalla.height / 3);
	    
		//Creo las etiquetas del panel y las agrego
		this.crearEtiquetas();
		
		//Creo las cajas de texto
		this.crearCajasDeTexto();
		
		//Creo los botones
	    this.agregarBotones(panel);
		
	    //agrego el panel al frame
		this.getContentPane().add(panel);
		
		//conecto la referencia a la instancia
		Instance = this;
		
		//Hago esta vista visible
		this.setVisible(true);
	}

	/**
	 * @return La unica instancia de esta ventana.
	 * Si no existe la crea y la devuelve.
	 */
	public static FrameAgregarRegion getInstance(){
		if (Instance == null)
			return new FrameAgregarRegion(" Agregar Region");
		else
			return Instance;
	}
	
	/**
	 * Crea los botones y los agrega al panel
	 * @param panel
	 */
	private void agregarBotones(JLayeredPane panel) {
		Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		actualizar = new JButton("Crear Region");
		actualizar.setBounds(tamanoPantalla.width / 10, tamanoPantalla.height / 4, 160, 30);
		panel.add(actualizar);
		actualizar.addActionListener(new ControladorBotonActualizar(this));
	}
	
	/**
	 * Crea, configura y agrega las etiquetas al panel de la vista
	 */
	private void crearEtiquetas(){
		//Defino el tamaņo de la pantalla
		Dimension tamaņoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		//Creo las etiquetas, las configuro y las agrego al panel
		this.etiquetas = new JLabel[CantidadDatos];
		Border borde = new MetalBorders.Flush3DBorder();
		for (int cont = 0; cont < CantidadDatos; cont++){
			this.etiquetas[cont] = new JLabel();
			this.etiquetas[cont].setBorder(borde);
			this.etiquetas[cont].setFont( new Font("Helvetica", Font.BOLD, 13 ) );
			this.etiquetas[cont].setBounds(10, 44 * cont + 5,tamaņoPantalla.width / 3 - 20, 20);
			this.panel.add(etiquetas[cont]);
		}
		this.etiquetas[0].setText(" Region: ");
		this.etiquetas[1].setText(" Dinero : ");
	}
	
	/**
	 * Crea, configura y agrega las cajas de texto al panel de la vista
	 */
	private void crearCajasDeTexto(){
		//Defino el tamaņo de la pantalla
		Dimension tamaņoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		//Creo las etiquetas, las configuro y las agrego al panel
		this.datos = new JTextField[CantidadDatos];
		Border borde = new MetalBorders.Flush3DBorder();
		for (int cont = 0; cont < CantidadDatos; cont++){
			this.datos[cont] = new JTextField();
			this.datos[cont].setBorder(borde);
			this.datos[cont].setFont( new Font("Helvetica", Font.BOLD, 13 ) );
			this.datos[cont].setBounds(10, 44 * cont + 25,tamaņoPantalla.width / 3 - 20, 20);
			this.panel.add(datos[cont]);
		}
	}
	/**
	 * Vacia el contenido de las cajas de texto
	 */
	public void limpiarCajasDeTexto(){
		for (int cont = 0; cont < CantidadDatos; cont++){
			this.datos[cont].setText("");
		}
	}
	/**
	 * @return El nombre de la region, ingresado en el campo region
	 */
	public String getNombreRegion(){
			return this.datos[0].getText();
	}
	
	/**
	 * @return El dinero de la region, ingresado en el campo dinero
	 */
	public int getDineroRegion(){
		int Dinero = 0;
		try{
			Dinero = Integer.parseInt(this.datos[1].getText());
		} catch (Exception e){
			JOptionPane.showMessageDialog(VistaPrincipal.getInstance().getJDesktopPane(), "Dinero mal ingresado");
			return Dinero;
		}
		return Dinero;
	}
}

