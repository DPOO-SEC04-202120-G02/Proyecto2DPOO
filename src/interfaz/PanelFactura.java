package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Compra;
import modelo.Entrada;

public class PanelFactura extends JPanel{
	
	private JTextArea txtfactura;
	private JButton btnCerrarCompra;
	private PanelPos posmain;
	
	public PanelFactura(PanelPos main) {
		posmain=main;
		setLayout(new BorderLayout());
		txtfactura=new JTextArea();
		txtfactura.setEditable(false);
		add(txtfactura,BorderLayout.CENTER);
		
		btnCerrarCompra=new JButton("Cerrar compra");
		btnCerrarCompra.addActionListener(btnListener);
		add(btnCerrarCompra,BorderLayout.SOUTH);
	}
	
	public void CerrarCompra() {
		Compra compra = posmain.cerrarCompra();
		txtfactura.selectAll();
		txtfactura.replaceSelection("");
		float precio_total=0;
		for (Entrada entrada : compra.getEntradas()) {
			txtfactura.append(entrada.getNombre_producto()+"\n");
			txtfactura.append("Cantidad: "+Float.toString(entrada.getCantidad_producto())+"\n");
			txtfactura.append("Precio: $"+Float.toString(entrada.getPrecioT())+"\n"+" "+"\n");
			precio_total+=entrada.getPrecioT();
		}
		txtfactura.append("Costo final de la compra: "+String.valueOf(precio_total));
		
		posmain.set_compraAC2null();
	}
	
	public void reiniciarDisplay() {
		txtfactura.setText(null);
	}
	
	ActionListener btnListener =new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CerrarCompra();
		}
	};
	
}
