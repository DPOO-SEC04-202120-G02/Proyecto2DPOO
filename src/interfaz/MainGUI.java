package interfaz;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import control.*;
import modelo.*;

public class MainGUI extends JFrame{
	private JPanel panelBanner;
	private JPanel panelCont;
	private JPanel panelIzq;
	private PanelDer panelDer;
	private PanelCen panelCen;
	private PanelMenuPrinc panelMenuPrinc;
	private PanelCargaLotes panelCargaLotes;
	private PanelBuscarProd panelBuscarProd;
	private CoordInventario coordInv;
	
	
	public MainGUI(CoordInventario coordInv) {
		this.coordInv = coordInv;
		try {
			coordInv.cargarProductos();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setTitle("Sistema Inventario");
		setLayout(new GridLayout(2, 1));
        setSize(900, 580);
        setResizable( true );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        panelBanner = new JPanel();
        panelBanner.setSize(900, 230);
        panelBanner.setBackground(Color.GREEN);
        String home = System.getProperty("user.dir");
      
			BufferedImage myPicture;
			try {
				myPicture = ImageIO.read(new File(home+"\\imagenes\\banner.png"));
				JLabel picLabel = new JLabel();
				ImageIcon img = new ImageIcon( new ImageIcon(myPicture).getImage().getScaledInstance(900, 230, Image.SCALE_DEFAULT));
				picLabel.setIcon(img);
				//Dimension dim = new Dimension(800,200);
				//picLabel.setPreferredSize(dim);
				panelBanner.add(picLabel);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        panelBanner.setVisible(true);
        add(panelBanner);
        
        panelCont = new JPanel();
        panelCont.setSize(900, 350);
        panelCont.setBackground(Color.BLACK);
        panelCont.setVisible(true);
        panelCont.setLayout(new GridLayout(1, 3));
        add(panelCont);
        
        panelIzq = new JPanel();
        panelIzq.setSize(300, 350);
        panelIzq.setBackground(Color.GRAY);
        panelIzq.setLayout(new GridLayout(3, 1));
        
        panelMenuPrinc = new PanelMenuPrinc();
        panelIzq.add(panelMenuPrinc);
        panelCargaLotes = new PanelCargaLotes(this);
        panelIzq.add(panelCargaLotes);
        panelBuscarProd =  new PanelBuscarProd(this);
        panelIzq.add(panelBuscarProd);
        
        panelCont.add(panelIzq);
        
        panelCen = new PanelCen();
        panelCont.add(panelCen);
        
        panelDer = new PanelDer();
        panelCont.add(panelDer);
        
        panelCont.setVisible(true);
        panelIzq.setVisible(true);
        panelCen.setVisible(true);
        
        setVisible(true);
        
	}
	
	public void ejecutarCargaCSV(String entrada) {
		try {
			coordInv.cargarLotes(entrada);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void ejecutarBusquedaProd(String entrada) {
		int codigo = Integer.parseInt(entrada);
		Producto producto = coordInv.consultarInfoProducto(codigo);
		panelCen.displayInfoProducto(producto);	
	}


}
