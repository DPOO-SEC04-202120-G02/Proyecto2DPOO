package interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PanelCargaLotes extends JPanel implements ActionListener {
	private JButton boton;
	private DialogoCargaCSV dialogoCargaCSV;
	private MainGUI principal;
	
	public PanelCargaLotes(MainGUI principal) {
		this.principal = principal;
	
		dialogoCargaCSV = new DialogoCargaCSV(this);
		dialogoCargaCSV.setVisible(false);
		setSize(300, 90);
        boton = new JButton("CARGAR LOTES CSV");
        boton.setBackground(Color.BLUE);
        boton.setForeground(Color.WHITE);
        boton.addActionListener((ActionListener) this);
        add(boton);
      
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	if (e.getSource() == boton) {
		dialogoCargaCSV.setVisible(true);
		
	}
	}


	public void ejecutarCargaCSV(String entrada) {
		principal.ejecutarCargaCSV(entrada);
		
	}
}
