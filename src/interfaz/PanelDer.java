package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import modelo.*;

public class PanelDer extends JPanel implements ActionListener{
	private JButton boton;
	private JScrollPane scroll;
	private JTextArea espacioLotes;
	private JPanel panel;
	private MainGUI principal;
	
	public PanelDer(MainGUI principal) {
		this.principal = principal;
		setSize(300, 350);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		add(Box.createRigidArea(new Dimension(0,5)));
		
        espacioLotes = new JTextArea();
        espacioLotes.setBackground(Color.WHITE);
        espacioLotes.setEditable(false);
        
        scroll = new JScrollPane(espacioLotes, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll);

        espacioLotes.append("Lote XX\n");
        espacioLotes.append("LLEGADA: XX/XX/XXXX\n");
        espacioLotes.append("PRECIO COMPRA: XX.XXX,XX\n");
        espacioLotes.append("CANT: XX\n");

        boton = new JButton("ELIMINAR VENCIDOS");
        boton.addActionListener((ActionListener) this);
        boton.setBackground(Color.MAGENTA);
        boton.setForeground(Color.WHITE);
        
        add(Box.createHorizontalGlue());
        add(Box.createRigidArea(new Dimension(0,15)));
        
        add(boton);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(Box.createRigidArea(new Dimension(0,15)));
        
	}

	public void displayInfoLotes(Producto producto) {
		ArrayList<Lote> lotes = producto.getLotes();
		espacioLotes.selectAll();
		espacioLotes.replaceSelection("");
		int cont = 0;
		for (Lote lote:lotes) {
			espacioLotes.append("Lote " + cont + "\n");
			Fecha fechaVenc = lote.getFechaVencimiento(); 
			espacioLotes.append("VENCIMIENTO: " + fechaVenc.getDia() + "/" + fechaVenc.getMes() + "/" + fechaVenc.getAño() + "\n");
			espacioLotes.append("PRECIO COMPRA: " + lote.getPrecioCompra() + "\n");
			espacioLotes.append("CANT: " + lote.getCantidad() + "\n");
			espacioLotes.append("\n");
			cont += 1;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	if (e.getSource() == boton) {
		principal.borrarVencidos();
	}
	}
}