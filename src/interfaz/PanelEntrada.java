package interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class PanelEntrada extends JPanel{
	
	private JLabel lblcodigo,lblcantidad;
	private JTextField txtcodigo,txtcantidad;
	private JButton btnAgregar, btncombo;
	private PanelPos posMain;
	
	public PanelEntrada(PanelPos main) {
		posMain=main;
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill=GridBagConstraints.BOTH;
		
		c.gridx=0;
		c.gridy=0;
		lblcodigo=new JLabel("Codígo: ");
		add(lblcodigo,c);

		c.gridx=0;
		c.gridy=1;
		lblcantidad=new JLabel("Cantidad: ");
		add(lblcantidad,c);
		
		c.gridx=1;
		c.gridy=0;
		c.gridwidth=2;
		txtcodigo=new JTextField();
		add(txtcodigo,c);
		
		c.gridx=1;
		c.gridy=1;
		c.gridwidth=2;
		txtcantidad=new JTextField();
		add(txtcantidad,c);
		
		c.gridx=0;
		c.gridy=2;
		c.gridwidth=3;
		
		btnAgregar=new JButton("Agregar");
		ActionListener agregar_listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NuevaEntrada();
				txtcantidad.setText("");
				txtcodigo.setText("");
			}
		};
		btnAgregar.addActionListener(agregar_listener);
		add(btnAgregar,c);
		
		c.gridx=0;
		c.gridy=3;
		c.gridwidth=3;
		
		btncombo=new JButton("Buscar combo");
		ActionListener agregar_listenerCombo = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AgregarCombo();
			}
		};
		btncombo.addActionListener(agregar_listenerCombo);
		add(btncombo,c);
	
	}
	
	public void NuevaEntrada() {
		try {
			if(posMain.addEntrada(Integer.parseInt(txtcodigo.getText()),Float.parseFloat(txtcantidad.getText()))) {
				 JFrame f=new JFrame();  
				 posMain.setIconEntrada(Integer.parseInt(txtcodigo.getText()));
				 JOptionPane.showMessageDialog(f,"Entrada agregada exitosamente.","Alert",JOptionPane.WARNING_MESSAGE);   
			}else {
				 JFrame f=new JFrame();  
				 JOptionPane.showMessageDialog(f,"Cantidad de producto no disponible.","Alert",JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void AgregarCombo() {
		JFrame f= new JFrame();
		String codigoCombo =JOptionPane.showInputDialog(f,"Codigo del combo que desea agregar:");
		try {
			if(posMain.AgregarCombo(codigoCombo)) {
				 JFrame f2=new JFrame();  
				 JOptionPane.showMessageDialog(f2,"Combo agregado exitosamente.","Alert",JOptionPane.WARNING_MESSAGE);   
			}else {
				 JFrame f2=new JFrame();  
				 JOptionPane.showMessageDialog(f2,"Cantidad de producto no disponible.","Alert",JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
