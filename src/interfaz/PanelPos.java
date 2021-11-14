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

public class PanelPos extends JPanel{
	
	private JButton btn_back;
	private PanelInfoCliente panelInfoCliente;
	private String home;
	
	public PanelPos() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//Imagen producto
		c.gridx=1;
		c.gridy=1;
		
		home=System.getProperty("user.dir");
		
		try {
			BufferedImage myPicture;
			myPicture = ImageIO.read(new File(home+"\\imagenes\\choco.png"));
			ImageIcon img =new ImageIcon( new ImageIcon(myPicture).getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT));
			JLabel picLabel = new JLabel();
			picLabel.setIcon(img);
			Dimension dim = new Dimension(300,200);
			picLabel.setPreferredSize(dim);
			add(picLabel,c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Panel de informacion del cliente.
		c.gridx=0;
		c.gridy=0;
		c.anchor=GridBagConstraints.FIRST_LINE_START;
		panelInfoCliente=new PanelInfoCliente();
		add(panelInfoCliente,c);
		//Boton de regresar al menú principal.
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx=1;
		c.gridy=1;
		c.anchor=GridBagConstraints.PAGE_END;
		btn_back=new JButton("Volvér al menú principal");
		btn_back.setPreferredSize(new Dimension(300,30));
		add(btn_back,c);
	}
}
