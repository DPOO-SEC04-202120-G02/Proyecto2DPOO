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
			
			RedimirPuntos(compra);//Verifica que el cliente tenga los puntos que quiere usar
			
			posmain.AplicarPromociones();//Revisa la compra y aplica las promociones si aplican
			Set<String> mySet = new HashSet<String>();//Se guardan los mensajes de las promociones aplicadas, sin repetecion.
			
			posmain.CerrarCompra();//Luego de modificada la compra por las promociones, se le dice a la logica que cierre la compra
			txtfactura.selectAll();
			txtfactura.replaceSelection("");
			float precio_total=0;
			for (Entrada entrada : compra.getEntradas()) {//Se muestra el valor de cada una de las entradas
				txtfactura.append(entrada.getNombre_producto()+"\n");
				txtfactura.append("Cantidad: "+Float.toString(entrada.getCantidad_producto())+"\n");
				txtfactura.append("Precio: $"+Float.toString(entrada.getPrecioT())+"\n"+" "+"\n");
				precio_total+=entrada.getPrecioT();
			}
			txtfactura.append("Puntos antes de la compra: "+Integer.toString(puntos_pre_compra)+"\n");
			txtfactura.append("Puntos redimidos: "+Integer.toString(compra.DarPuntos())+"\n");//Puntos gastados en la compra
			int delta_puntos=-compra.DarPuntos()+compra.DarPuntosAgregados();
			txtfactura.append("Puntos tras la compra: "+Integer.toString(puntos_pre_compra+delta_puntos)+"\n");
			txtfactura.append("Subtotal: "+String.valueOf(precio_total)+"\n");
			txtfactura.append("Promociones aplicadas ------------"+"\n");
			for (String msg:compra.DarPromocionesAplicadas()) {
				mySet.add(msg);
			}
			for (String msg : mySet) {
				txtfactura.append(msg+"\n");
			}
			txtfactura.append("Costo final tras redimir puntos------------"+"\n");
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
