package modelo;

import java.io.*;
import java.util.*;

public class ConectorPersistencia {
	
	public BufferedReader cargarLotes(String nombreArchivo) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		return br;
	}
	
	public HashMap<Integer, Producto> cargarProductos() throws FileNotFoundException, IOException, ClassNotFoundException{
		HashMap<Integer, Producto> productos = new HashMap<Integer, Producto>();
		String rutaDirectorio = System.getProperty("user.dir");
		File carpetaProductos = new File(rutaDirectorio + "\\Productos\\");
		File[] directoryListing = carpetaProductos.listFiles();
		if (directoryListing != null) {
		    for (File file : directoryListing) {
		      ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(rutaDirectorio + "\\Productos\\" + file.getName()));
		      Producto producto = (Producto) entrada.readObject();
		      productos.put(producto.getCodigo(), producto);
		    }
		}
		return productos;
	}
	
	public void crearProducto(Producto producto) throws FileNotFoundException, IOException {
		String nombreArchivo = Integer.toString(producto.getCodigo()) + ".txt";
		String rutaDirectorio = System.getProperty("user.dir");
		@SuppressWarnings("resource")
		ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaDirectorio + "\\Productos\\" + nombreArchivo));
		salida.writeObject(producto);
	}

}
