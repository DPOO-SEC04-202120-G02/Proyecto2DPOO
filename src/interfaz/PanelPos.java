package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
		
		c.gridx=3;
		c.gridy=0;
		
		home=System.getProperty("user.dir");
		
		try {
			BufferedImage myPicture;
			myPicture = ImageIO.read(new File(home+"\\imagenes\\choco.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			Dimension dim = new Dimension(300,300);
			picLabel.setPreferredSize(dim);
			add(picLabel,c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c.gridx=0;
		c.gridy=0;
		panelInfoCliente=new PanelInfoCliente();
		add(panelInfoCliente,c);
		c.gridx=1;
		c.gridy=1;
		btn_back=new JButton("Volvér al menú principal");
		add(btn_back,c);
	}
}
