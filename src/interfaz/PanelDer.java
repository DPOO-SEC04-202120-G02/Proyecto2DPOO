package interfaz;

import java.awt.*;
import javax.swing.*;

public class PanelDer extends JPanel{
	private JButton boton;
	private JPanel panel;
	private JScrollPane scroll;
	private JLabel texto;
	private JLabel texto_1;
	private JLabel texto_2;
	private JLabel texto_3;
	
	public PanelDer() {
		setSize(300, 350);
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout(4,1));
        texto = new JLabel("Lote XX");
        texto_1 = new JLabel("LLEGADA: XX/XX/XXXX");
        texto_2 = new JLabel("PRECIO COMPRA: XX.XXX,XX");
        texto_3 = new JLabel("CANT: XX");
        panel.add(texto);
        panel.add(texto_1);
        panel.add(texto_2);
        panel.add(texto_3);
        scroll = new JScrollPane(panel);
        add(scroll);
        boton = new JButton("ELIMINAR VENCIDOS");
        boton.setBackground(Color.MAGENTA);
        boton.setForeground(Color.WHITE);
        add(boton);
        
        
	}
}