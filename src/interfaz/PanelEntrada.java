package interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class PanelEntrada extends JPanel{
	
	private JLabel lblcodigo,lblcantidad;
	private JTextField txtcodigo,txtcantidad;
	private JButton btnAgregar;
	
	public PanelEntrada() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill=GridBagConstraints.BOTH;
		
		c.gridx=0;
		c.gridy=0;
		lblcodigo=new JLabel("Cod�go: ");
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
		add(btnAgregar,c);
	}

}