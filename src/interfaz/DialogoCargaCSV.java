package interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DialogoCargaCSV extends JDialog implements ActionListener{
	private JTextField textField;
	private JButton button;
	private PanelCargaLotes panelCargaLotes;
	
	public DialogoCargaCSV(PanelCargaLotes panelCargaLotes) {
	this.panelCargaLotes = panelCargaLotes;
	setTitle("Carga CSV");
	this.setLayout(new FlowLayout());
	
	button = new JButton("EJECUTAR");
	button.addActionListener((ActionListener) this);
	textField = new JTextField();
	textField.setPreferredSize(new Dimension(250, 20));
	textField.setFont(new Font("Consolas", Font.PLAIN, 15));
	textField.setForeground(Color.BLACK);
	textField.setBackground(Color.WHITE);
	textField.setCaretColor(Color.BLACK);
	textField.setText("Ingrese ruta del archivo");
	
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
		panelCargaLotes.ejecutarCargaCSV(entrada);
	}
	}
}
