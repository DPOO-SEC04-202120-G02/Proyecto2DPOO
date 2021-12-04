package interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DialogoSelectorImagen extends JDialog implements ActionListener{
	private JTextField textField;
	private JButton button;
	private MainGUI principal;
	
	public DialogoSelectorImagen(MainGUI principal) {
	this.principal = principal;
	setTitle("Selector Imagen");
	this.setLayout(new FlowLayout());
	
	button = new JButton("EJECUTAR");
	button.addActionListener((ActionListener) this);
	textField = new JTextField();
	textField.setPreferredSize(new Dimension(250, 20));
	textField.setFont(new Font("Consolas", Font.PLAIN, 15));
	textField.setForeground(Color.BLACK);
	textField.setBackground(Color.WHITE);
	textField.setCaretColor(Color.BLACK);
	textField.setText("Ingrese ruta de la imagen");

	this.add(textField);
	this.add(button);
	this.pack();
	this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	if (e.getSource() == button) {
		String entrada = textField.getText();
		textField.setText("Ingrese ruta del archivo");
		this.setVisible(false);
		principal.ejecutarCambioImg(entrada);
	}
	}
}