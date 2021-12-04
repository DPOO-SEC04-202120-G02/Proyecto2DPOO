package interfaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelMenuPrinc extends JPanel{
	private JButton boton;
	private MainGUI principal;
	
	public PanelMenuPrinc(MainGUI principal) {
		this.principal = principal;
		setSize(300, 90);
        boton = new JButton("VOLVER AL MENÚ PRINCIPAL");
        boton.addActionListener(action_btn);
        boton.setBackground(Color.RED);
        boton.setForeground(Color.WHITE);
        add(boton);
	}

	ActionListener action_btn = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			principal.regresar();
		}
	};
	
}
