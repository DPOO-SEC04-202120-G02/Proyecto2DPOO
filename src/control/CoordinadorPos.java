package control;

import java.io.FileNotFoundException;
import java.io.IOException;

import modelo.Cliente;
import modelo.Compra;
import modelo.ControladorCliente;
import modelo.ControladorCompra;
import modelo.Producto;

public class CoordinadorPos {
	
	ControladorCliente ctrl_cliente;
	ControladorCompra ctrl_compra;
	
	public CoordinadorPos() {
		ctrl_cliente=new ControladorCliente();
		ctrl_compra=new ControladorCompra();
	}
	
	public void AplicarPromociones() {
		ctrl_compra.AplicarPromociones();
	}
	
	public boolean addCombo(String codigo) throws NumberFormatException, FileNotFoundException, ClassNotFoundException, IOException {
		return ctrl_compra.addCombo(codigo);
	}
	
	public boolean addEntrada(int codigo, float cantidad) throws FileNotFoundException, ClassNotFoundException, IOException {
		return ctrl_compra.addEntrada(codigo, cantidad);
	}
	
	public boolean nuevaCompra(int cedula) {
		Cliente cliente=ctrl_cliente.darCliente(cedula);
		if (cliente!=null){
			//System.out.println("Cliente no nulo");
			return ctrl_compra.nuevaCompra(cliente);
		}else {
			return false;
		}
	}
	
	public Cliente DarCliente(int cedula) {
		return ctrl_cliente.darCliente(cedula);
	}
	
	public Producto setIconEntrada(int codigo) {
		return ctrl_compra.setIconEntrada(codigo);
	}
	
	public void registrarCliente(int cedula_new, int edad_new, String sexo_new, String Estado_new, String Empleo_new,String Nombre_nuevo) {
		ctrl_cliente.registrarCliente(cedula_new, edad_new, sexo_new, Estado_new, Empleo_new,Nombre_nuevo);
	}
	
	public void cargarClientes() {
		ctrl_cliente.CargarClientes();
	}
	
	public Compra DarCompraActual() {
		return ctrl_compra.cerrarCompra();
	}
	
	public void cerrarCompra() {
		ctrl_cliente.SumarPuntos(ctrl_compra.cerrarCompra(), ctrl_compra.cerrarCompra().getCliente());
	}
	
	public void set_compraAC2null() {
		ctrl_compra.set_compraAC2null();
	}
	
}
