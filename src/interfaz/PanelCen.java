package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import modelo.*;

public class PanelCen extends JPanel implements ActionListener{
	private JLabel nombre;
	private JLabel precioActual;
	private JLabel gananciaReg;
	private JLabel perdida;
	private JPanel contenedor;
	private JPanel fill;
	private JPanel fill_1;
	private JButton boton;
	private MainGUI principal;
	
	public PanelCen(MainGUI principal) {
		this.principal = principal;
		setSize(300, 350);
		setLayout(new GridLayout(3, 1));
		fill = new JPanel();
		fill.setSize(300, 75);
		add(fill);
		
		contenedor = new JPanel();
		contenedor.setSize(300, 200);
		contenedor.setLayout(new GridLayout(4, 1));
        
		this.nombre = new JLabel("                    Nombre: XXX");
		contenedor.add(nombre);
		
		this.precioActual = new JLabel("                    Precio actual: $X.XXX,XX", SwingConstants.LEFT);
		contenedor.add(precioActual);
		
		this.gananciaReg = new JLabel("                    Ganancia registrada: $XXX", SwingConstants.LEFT);
		contenedor.add(gananciaReg);
		
		this.perdida = new JLabel("                    Perdida por vencimiento: XXX", SwingConstants.LEFT);
		contenedor.add(perdida);
		
		add(contenedor);
		
		fill_1 = new JPanel();
		fill_1.setSize(300, 75);
		add(fill_1);
		
		boton = new JButton("ELEGIR IMAGEN");
        boton.addActionListener((ActionListener) this);
        boton.setBackground(Color.BLUE);
        boton.setForeground(Color.WHITE);
        fill_1.add(boton);		
	}
	

	public void displayInfoProducto(Producto producto) {
		String infoNombre = producto.getNombre();
		float infoPrecio = producto.getPrecioVenta();
		float infoGanancia = producto.getGananciaVenta();
		float infoPerdida = producto.getPerdidos();
		
		nombre.setText("               Nombre: " + infoNombre);
		precioActual.setText("               Precio actual: $" + infoPrecio);
		gananciaReg.setText("               Ganancia registrada: $" + infoGanancia);
		perdida.setText("               Perdida por vencimiento: " + infoPerdida);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	if (e.getSource() == boton) {
		new DialogoSelectorImagen(principal);
	}
	}
}
