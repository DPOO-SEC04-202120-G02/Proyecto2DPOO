package modelo;

import java.util.*;
import java.io.*;

public class AdminProductos {
	HashMap<Integer, Producto> productos;
	ConectorPersistencia persistencia;
	
	public AdminProductos() {
		productos =  new HashMap<Integer, Producto>();
		persistencia = new ConectorPersistencia();
	}
		
	public void addProducto(Producto producto) {
		Integer idProducto = producto.getCodigo();
		productos.put(idProducto, producto);
	}
	
	public Producto consultarProducto(int idProducto) {
		return productos.get(idProducto);
	}
	
	public void cargarLotes(String nombreArchivo) throws IOException {
	    BufferedReader br = persistencia.cargarLotes(nombreArchivo);
	    br.readLine();
		String linea = br.readLine();
	
		while (linea != null) 
		{
			String[] partes = linea.split(",");
			int idProducto = Integer.parseInt(partes[0]);
			String nombreProducto = partes[1];
			boolean empacado = false;
			if (partes[2].equals("empacado")) {
				empacado = true;
			}
			int numLote = Integer.parseInt(partes[3]);
			float cantidad = Float.parseFloat(partes[4]);
			float precioCompra = Float.parseFloat(partes[5]);
			float precioVenta = Float.parseFloat(partes[6]);
			String fechaVencimiento = partes[7];
			String[] fechaVencSeparada = fechaVencimiento.split("/");
			int diaVenc = Integer.parseInt(fechaVencSeparada[0]);
			int mesVenc = Integer.parseInt(fechaVencSeparada[1]);
			int añoVenc = Integer.parseInt(fechaVencSeparada[2]);
			String nombreCategoria = partes[8];
			
			if (productos.containsKey(idProducto)) {
				Producto producto = consultarProducto(idProducto);
				producto.setPrecioVenta(precioVenta);
				producto.addCategoria(nombreCategoria);
				Fecha fechaVenc = new Fecha(diaVenc, mesVenc, añoVenc);
				Lote lote = new Lote(numLote, precioCompra, cantidad, fechaVenc);
				producto.addLote(lote);
				persistencia.crearProducto(producto);
			}
			else {
				Fecha fechaVenc = new Fecha(diaVenc, mesVenc, añoVenc);
				Lote lote = new Lote(numLote, precioCompra, cantidad, fechaVenc);
				Producto producto = new Producto(idProducto, nombreProducto, empacado);
				producto.setPrecioVenta(precioVenta);
				producto.addCategoria(nombreCategoria);
				producto.addLote(lote);
				addProducto(producto);
				persistencia.crearProducto(producto);
			}
			
			linea = br.readLine();
		}

		br.close();
	}
	
	public void eliminarLotesVencidos(int idProducto, Fecha fechaActual) throws FileNotFoundException, IOException{
		Producto producto = productos.get(idProducto);
		boolean eliminacion = producto.eliminarLotesVencidos(fechaActual);
		if (eliminacion == true) {
			persistencia.crearProducto(producto);
		}
	}
	
	public void cargarProductos() throws FileNotFoundException, ClassNotFoundException, IOException {
		HashMap<Integer, Producto> productosCargados =  persistencia.cargarProductos();
		productos = productosCargados;
	}
	
	public boolean verificarCantidad(int idProducto, float cantidad) {
		boolean salida = false;
		Producto producto = consultarProducto(idProducto);
		if (producto.contarCantidad() > cantidad) {
			salida = true;
		}
		return salida;
	}
	
	public void restarCantidad(int idProducto, float cantidad) throws FileNotFoundException, IOException {
		Producto producto = consultarProducto(idProducto);
		producto.restarCantidad(cantidad);
		persistencia.crearProducto(producto);
	}
}