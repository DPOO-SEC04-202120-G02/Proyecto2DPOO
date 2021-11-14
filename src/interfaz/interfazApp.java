package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.*;


import control.*;
import modelo.*;


public class interfazApp extends JFrame{
	
	private JPanel panel_main,panel_botones;
	private JButton btn_pos,btn_inv;
	private String home;//Home directory
	
	CoordinadorPos CooPos;
	CoordInventario cooInv;
	PanelPos panelpos;
	
	
	
	public interfazApp() {
		CooPos = new CoordinadorPos();
		cooInv = new CoordInventario();
		panelpos = new PanelPos();
		
		setTitle("ventana swing");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,800);
		setResizable(false);
		
		panel_main= new JPanel();
		panel_main.setLayout(new BorderLayout());
		
		home=System.getProperty("user.dir");
		
		try {
			BufferedImage myPicture;
			myPicture = ImageIO.read(new File(home+"\\imagenes\\banner.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			Dimension dim = new Dimension(800,200);
			picLabel.setPreferredSize(dim);
			panel_main.add(picLabel,BorderLayout.NORTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		panel_botones=new JPanel();
		panel_botones.setBackground(Color.LIGHT_GRAY);
		panel_botones.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weighty=1;
		btn_pos=new JButton("Sistema pos");
		ActionListener action_btn_pos = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				BorderLayout layout = (BorderLayout)panel_main.getLayout();
				panel_main.remove(layout.getLayoutComponent(BorderLayout.CENTER));
				
				panel_main.add(panelpos,BorderLayout.CENTER);
				panel_main.revalidate();
			}
		};
		
		btn_pos.addActionListener(action_btn_pos);
		
		
		Dimension dim_btn_pos = new Dimension(300,100);
		btn_pos.setPreferredSize(dim_btn_pos);
		btn_pos.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
		btn_pos.setBackground(Color.GREEN);
		btn_pos.setForeground(Color.BLACK);
		c.gridx=2;
		c.gridy=1;
		panel_botones.add(btn_pos,c);
		
		btn_inv=new JButton("Sistema inventario");
		btn_inv.setPreferredSize(dim_btn_pos);
		btn_inv.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
		btn_inv.setBackground(Color.GREEN);
		btn_inv.setForeground(Color.BLACK);
		c.gridx=2;
		c.gridy=2;
		panel_botones.add(btn_inv,c);
		panel_main.add(panel_botones,BorderLayout.CENTER);
		
		add(panel_main);
		setVisible(true);
		
 	}
	
	
	public static void main(String[] args) {
		new interfazApp();
	}

}

