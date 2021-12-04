package interfaz;
import java.util.HashSet;
import java.util.Set;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
		Compra compra = posmain.DarCompraActual();
		int puntos_pre_compra=compra.getCliente().getPuntos();
		//Puntos
		try {
			
			RedimirPuntos(compra);
			
			posmain.AplicarPromociones();
			Set<String> mySet = new HashSet<String>();
			
			posmain.CerrarCompra();
			txtfactura.selectAll();
			txtfactura.replaceSelection("");
			float precio_total=0;
			for (Entrada entrada : compra.getEntradas()) {
				txtfactura.append(entrada.getNombre_producto()+"\n");
				txtfactura.append("Cantidad: "+Float.toString(entrada.getCantidad_producto())+"\n");
				txtfactura.append("Precio: $"+Float.toString(entrada.getPrecioT())+"\n"+" "+"\n");
				precio_total+=entrada.getPrecioT();
			}
			txtfactura.append("Puntos antes de la compra: "+Integer.toString(puntos_pre_compra)+"\n");
			txtfactura.append("Puntos redimidos: "+Integer.toString(compra.DarPuntos())+"\n");
			int delta_puntos=-compra.DarPuntos()+(int) precio_total/1000;
			txtfactura.append("Puntos tras la compra: "+Integer.toString(puntos_pre_compra+delta_puntos)+"\n");
			txtfactura.append("Subtotal: "+String.valueOf(precio_total)+"\n");
			txtfactura.append("Promociones aplicadas ------------"+"\n");
			for (String msg:compra.DarPromocionesAplicadas()) {
				mySet.add(msg);
			}
			for (String msg : mySet) {
				txtfactura.append(msg+"\n");
			}
			txtfactura.append("Costo final a cancelar ------------"+"\n");
			precio_total-=15*compra.DarPuntos();
			txtfactura.append("Costo final de la compra: "+String.valueOf(precio_total));
			posmain.setBlank();
			posmain.set_compraAC2null();
		}catch(Exception e) {
			JFrame fs=new JFrame();
			e.printStackTrace();
			JOptionPane.showMessageDialog(fs, e.getMessage());
		}
	}
	
	public void RedimirPuntos(Compra compra) throws Exception{
		JFrame f= new JFrame();
		String puntos =JOptionPane.showInputDialog(f,"Cantidad de puntos que desea redimir:");
		int puntos_int = Integer.parseInt(puntos);
		compra.ValidarPuntos(puntos_int);
		compra.AgregarPuntos(puntos_int);
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
