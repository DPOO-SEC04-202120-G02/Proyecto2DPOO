package interfaz;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInfoCliente extends JPanel{

	private JLabel lbl1cc,lblname,lblPuntos;
	
	public PanelInfoCliente(){
		setLayout(new GridLayout(3,1));
		
		lbl1cc=new JLabel("1005150175");
		add(lbl1cc);
		lblname=new JLabel("Martin Daniel Rincon");
		add(lblname);
		lblPuntos=new JLabel("21");
		add(lblPuntos);
		
		setBackground(Color.gray);
		setVisible(true);
	}

}
