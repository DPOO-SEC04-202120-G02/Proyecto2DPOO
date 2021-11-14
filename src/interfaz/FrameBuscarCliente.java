package interfaz;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.*;

public class FrameBuscarCliente extends JFrame{
	
	private PanelPos pos_main;
	private JLabel lbl1, lblCrear;
	private JButton btnBuscar;
	private JTextField txtBuscar;
	
	public FrameBuscarCliente(PanelPos app) {
		pos_main=app;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300,300);
		setResizable(false);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=2;
		JLabel lbl1=new JLabel("Cédula cliente: ");
		add(lbl1,c);
		
		c.gridx=3;
		c.gridy=0;
		c.gridwidth=3;
		JTextField txtbuscar =new JTextField();
		txtbuscar.setPreferredSize(new Dimension(100,30));
		add(txtbuscar,c);
		
		c.gridx=2;
		c.gridy=1;
		c.gridwidth=3;
		JButton btnBuscar =new JButton("Buscar");
		add(btnBuscar,c);
		
		c.gridx=2;
		c.gridy=2;
		c.gridwidth=3;
		JLabel lblCrear =new JLabel("Nuevo cliente");
		lblCrear.setForeground(Color.BLUE.darker());
		lblCrear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		MouseAdapter lblCrearListener = new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
			 CrearCliente();
			 }
		};
		lblCrear.addMouseListener(lblCrearListener);
		add(lblCrear,c);
		
		setVisible(true);
		
	}

	public void CrearCliente() {
		FrameCrearCliente FrameCrear= new FrameCrearCliente(pos_main);
	}
	
}
