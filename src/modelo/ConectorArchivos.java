package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class ConectorArchivos {
	
	String home;//Home directory
	
	public ConectorArchivos() {
		home=System.getProperty("user.dir");
		//Crea la carpeta si es que no existe en el directorio.
		File theDir = new File(home + "\\clientes_folder\\");
		if (!theDir.exists()){
		    theDir.mkdirs();
		}
	}
	
	public void GuardarCliente(Cliente cliente) {
		String FileName = Integer.toString(cliente.getCedula()) + ".txt";
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(home + "\\clientes_folder\\" + FileName));
			os.writeObject(cliente);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HashMap<Integer, Cliente> cargarClientes() {
		
		HashMap<Integer, Cliente> clientes = new HashMap<Integer, Cliente>();
		
		File f = new File(home + "\\clientes_folder\\");
		File[] files = f.listFiles();
		for (File file : files) {
			try {
				ObjectInputStream in = new ObjectInputStream(
				new FileInputStream(home + "\\clientes_folder\\" + file.getName()));
				Cliente c= (Cliente) in.readObject();
				clientes.put(c.getCedula(), c);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return clientes;
	}
	
}
