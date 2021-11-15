package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameCrearCliente extends JFrame{

	private PanelPos pos_main;
	private JPanel panel_campos;
	private JLabel lblcc,lblname,lblEdad,lblsexo,lblestadocivil,lblsitlab;
	private JTextField txtcc,txtname,txtEdad,txtsexo,txtestadocivil,txtsitlab;
	private JButton btnsave;

	public FrameCrearCliente(PanelPos app) {
		pos_main=app;
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(600,600);
		setResizable(false);
		JPanel panel_campos=new JPanel();
		panel_campos.setLayout(new GridLayout(6,2));

		JLabel lblcc=new JLabel("Cedula: ");
		JLabel lblname=new JLabel("Nombre: ");
		JLabel lblEdad=new JLabel("Edad: ");
		JLabel lblsexo=new JLabel("Sexo: ");
		JLabel lblestadocivil=new JLabel("Estado civil: ");
		JLabel lblsitlab=new JLabel("Situacion laboral: ");

		JTextField txtcc =new JTextField();
		JTextField txtname =new JTextField();
		JTextField txtEdad =new JTextField();
		JTextField txtsexo =new JTextField();
		JTextField txtestadocivil =new JTextField();
		JTextField txtsitlab =new JTextField();

		panel_campos.add(lblcc);
		panel_campos.add(txtcc);

		panel_campos.add(lblname);
		panel_campos.add(txtname);

		panel_campos.add(lblEdad);
		panel_campos.add(txtEdad);

		panel_campos.add(lblsexo);
		panel_campos.add(txtsexo);

		panel_campos.add(lblestadocivil);
		panel_campos.add(txtestadocivil);

		panel_campos.add(lblsitlab);
		panel_campos.add(txtsitlab);

		add(panel_campos,BorderLayout.CENTER);

		JButton btnsave= new JButton("Guardar");
		ActionListener action_save = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("wadawda");
				RegistrarCliente(txtcc.getText(),txtEdad.getText(),txtsexo.getText(),txtestadocivil.getText(),txtsitlab.getText(),txtname.getText());
				setVisible(false); //you can't see me!
				dispose(); //Destroy the JFrame object
			}
		};
		btnsave.addActionListener(action_save);
		add(btnsave,BorderLayout.SOUTH);
		setVisible(true);
	}

	public void RegistrarCliente(String cedula_new, String edad_new, String sexo_new, String Estado_new, String Empleo_new,String Nombre_new) {
		pos_main.RegistrarCliente(cedula_new, edad_new, sexo_new, Estado_new, Empleo_new,Nombre_new);
	}



}
