package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelBuscarProd extends JPanel implements ActionListener{
	
	private JButton button; 
	private JTextField textField;
	private JLabel nombre;
	private JPanel panelSup;
	private MainGUI principal;
	
	public PanelBuscarProd(MainGUI principal) {
		this.principal = principal;
		setSize(300, 90);
		setLayout(new FlowLayout());
		
		button = new JButton("ACEPTAR");
		button.setForeground(Color.WHITE);
		button.setBackground(Color.GREEN);
		button.addActionListener((ActionListener) this); 
		panelSup = new JPanel();
		panelSup.setSize(300, 90);
        
        nombre =  new JLabel("CÓDIGO:");
        
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(235, 30));
		textField.setFont(new Font("Consolas", Font.PLAIN, 15));
		textField.setForeground(Color.BLACK);
		textField.setBackground(Color.WHITE);
		textField.setCaretColor(Color.BLACK);
		textField.setText("Ingrese código del producto");
	    
		panelSup.add(nombre);
		panelSup.add(textField);
		
	    
	    this.add(panelSup);
		this.add(button);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			String entrada = textField.getText();
			textField.setText("Ingrese código del producto");
			principal.ejecutarBusquedaProd(entrada);
	}
	}
}
