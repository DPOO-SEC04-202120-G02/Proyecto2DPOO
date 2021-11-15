package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import control.CoordinadorPos;
import modelo.Cliente;

public class PanelPos extends JPanel{
	
	private CoordinadorPos cooPos;
	private JButton btn_back;
	private PanelInfoCliente panelInfoCliente;
	private PanelEntrada panelEntrada;
	private PanelFactura panelFactura;
	private String home;
	
	public PanelPos(CoordinadorPos copos) {
		
		cooPos = copos;
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill=GridBagConstraints.BOTH;
		
		//Panel de informacion del cliente.
		c.gridx=0;
		c.gridy=0;
		c.gridheight=2;
		c.gridwidth=3;
		
		c.anchor=GridBagConstraints.FIRST_LINE_START;
		panelInfoCliente=new PanelInfoCliente(this);
		add(panelInfoCliente,c);
		
		//Info de la compra
		c.gridx=7;
		c.gridy=0;
		c.gridheight=6;
		c.anchor=GridBagConstraints.FIRST_LINE_END;
		panelFactura=new PanelFactura();
		add(panelFactura,c);
		
		//Panel entrada
		c.gridx=4;
		c.gridy=0;
		c.gridheight=2;
		c.gridwidth=3;
		c.anchor=GridBagConstraints.PAGE_START;
		panelEntrada=new PanelEntrada();
		add(panelEntrada,c);
		
		//Imagen producto
		
		  c.gridx=3; c.gridy=2; c.anchor=GridBagConstraints.CENTER;
		  home=System.getProperty("user.dir");
		  
		  try { BufferedImage myPicture; myPicture = ImageIO.read(new
		  File(home+"\\imagenes\\choco.png")); ImageIcon img =new ImageIcon( new
		  ImageIcon(myPicture).getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT)); 
		  JLabel picLabel = new JLabel(); picLabel.setIcon(img);
		  Dimension dim = new Dimension(300,200); picLabel.setPreferredSize(dim);
		  add(picLabel,c); } catch (IOException e) { // TODO Auto-generated catch block
		  e.printStackTrace(); }
		 
		//Boton de regresar al men� principal.
		
		c.anchor=GridBagConstraints.SOUTH;
		c.gridx=3;
		c.gridy=4;
		c.gridheight=1;
		c.gridwidth=2;
		
		btn_back=new JButton("Volv�r al men� principal");
		btn_back.setPreferredSize(new Dimension(300,30));
		add(btn_back,c);
	}
	
	//Metodos
	
	public void RegistrarCliente(String cedula_new, String edad_new, String sexo_new, String Estado_new, String Empleo_new,String nombre_new) {
		cooPos.registrarCliente(Integer.parseInt(cedula_new), Integer.parseInt(edad_new), sexo_new, Estado_new, Empleo_new,nombre_new);
	}
	
	public void NuevaCompra(int cedula) {
		if (cooPos.nuevaCompra(cedula)) {
			panelInfoCliente.nuevoCliente(cooPos.DarCliente(cedula));
		}
	}
	
}
