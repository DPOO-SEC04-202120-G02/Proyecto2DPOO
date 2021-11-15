package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Cliente;

public class PanelInfoCliente extends JPanel{

	private JLabel lbl1cc,lblname,lblPuntos;
	private JButton btnbuscarCliente;
	private PanelPos pos_main;
	
	public PanelInfoCliente(PanelPos app){
		pos_main=app;
		setLayout(new GridLayout(4,1));
		
		lbl1cc=new JLabel("1005150175");
		add(lbl1cc);
		lblname=new JLabel("Martin Daniel Rincon");
		add(lblname);
		lblPuntos=new JLabel("21");
		add(lblPuntos);
		
		ActionListener action_btn_buscar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			FrameBuscarCliente buscarFrame=new FrameBuscarCliente(pos_main);
			}
		};
		btnbuscarCliente=new JButton("Buscar cliente");
		btnbuscarCliente.addActionListener(action_btn_buscar);
		btnbuscarCliente.setBackground(Color.GREEN);
		add(btnbuscarCliente);
		
		setBackground(Color.gray);
		setVisible(true);
	}
	
	public void nuevoCliente(Cliente cliente) {
		String cc=Integer.toString(cliente.getCedula());
		String nombre=cliente.getNombre();
		String puntos=String.valueOf(cliente.getPuntos());
		lbl1cc.setText(cc);
		lblname.setText(nombre);
		lblPuntos.setText(puntos);
	}
	
}
