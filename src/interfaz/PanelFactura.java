package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelFactura extends JPanel{
	
	private JTextField txtfactura;
	private JButton btnCerrarCompra;
	
	public PanelFactura() {
		setLayout(new BorderLayout());
		txtfactura=new JTextField();
		add(txtfactura,BorderLayout.CENTER);
		
		btnCerrarCompra=new JButton("Cerrar compra");
		add(btnCerrarCompra,BorderLayout.SOUTH);
	}

}
