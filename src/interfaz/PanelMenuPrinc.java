package interfaz;

import java.awt.Color;

import javax.swing.*;

public class PanelMenuPrinc extends JPanel{
	private JButton boton;
	
	public PanelMenuPrinc() {
		setSize(300, 90);
        boton = new JButton("VOLVER AL MENÚ PRINCIPAL");
        boton.setBackground(Color.RED);
        boton.setForeground(Color.WHITE);
        add(boton);
	}

	
}
