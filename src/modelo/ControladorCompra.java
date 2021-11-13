package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ControladorCompra {
	
	private Compra compra_actual;
	private AdminProductos admind_prod;
	
	public ControladorCompra() {
		compra_actual= null;
		admind_prod = new AdminProductos();
		
		try {
			admind_prod.cargarProductos();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean addEntrada(int codigo, float cantidad) throws FileNotFoundException, ClassNotFoundException, IOException {
		admind_prod.cargarProductos();
		Producto prod = admind_prod.consultarProducto(codigo);
		if (admind_prod.verificarCantidad(codigo, cantidad)) {
			float precio=prod.getPrecioVenta()*cantidad;
			compra_actual.addEntrada(precio, prod.getNombre(), cantidad, codigo);
			try {
				admind_prod.restarCantidad(codigo, cantidad);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	public boolean nuevaCompra(Cliente cliente_n) {
		if (compra_actual!=null) {
			return false;
		}else{
			compra_actual= new Compra(cliente_n);
			return true;
		}
	}
	
	public Compra cerrarCompra() {//Se retorna la compra y se marca que no hay compras porcesandose.
		Compra c= compra_actual;
		return c;
	}
	
	public void set_compraAC2null() {
		compra_actual=null;
	}
	
}
