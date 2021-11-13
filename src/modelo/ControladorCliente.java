package modelo;

import java.util.HashMap;
import java.util.List;

public class ControladorCliente {
	
	ConectorArchivos conector;
	HashMap<Integer, Cliente> clientes;
	
	public ControladorCliente() {
		clientes = new HashMap<Integer, Cliente>();
		conector = new ConectorArchivos();
		CargarClientes();
	}
	
	public void registrarCliente(int cedula_new, int edad_new, String sexo_new, String Estado_new, String Empleo_new) {
		Cliente new_cliente= new Cliente(cedula_new, edad_new, sexo_new, Estado_new, Empleo_new);
		clientes.put(new_cliente.getCedula(), new_cliente);
		conector.GuardarCliente(new_cliente);//Guarda en la carpeta
		clientes.put(new_cliente.getCedula(), new_cliente);//Guarda en el HashMap para poder usar este cliente inmediatamente
	}
	
	public void CargarClientes() {
		clientes=conector.cargarClientes();
		for (HashMap.Entry<Integer, Cliente> Entry : clientes.entrySet()) {
			Cliente cliente_test=clientes.get(Entry.getKey());
			System.out.println(Integer.toString(cliente_test.getCedula()));
		}
	}
	
	public Cliente darCliente(int cedula) {
		Cliente cliente_return=clientes.get(cedula);
		return cliente_return; //Retorna null si la cedula ingresada no corresponde a ningun cliente
	}
	
	public void SumarPuntos(Compra compra, Cliente cliente) {
		float precio_total=0;
		for (Entrada entrada : compra.getEntradas()) {
			precio_total+=entrada.getPrecioT();
		}
		float puntos = precio_total/1000;
		cliente.sumarPuntos(puntos);
	}
	
}
