package modelo;

import java.util.*;

public class Compra {
	
	List<Entrada> entradas;
	Cliente cliente;
	
	public Compra(Cliente cliente_compra) {
		entradas = new ArrayList<Entrada>();
		cliente = cliente_compra;
	}
	
	public void addEntrada(float precio, String nombre, float cantidad, int codigo) {
		Entrada Entrada_new= new Entrada(precio, nombre, cantidad, codigo);
		entradas.add(Entrada_new);
	}
	
	public float calcularPrecio() {
		
		float precio = 0;
		
		for (Entrada entrada : entradas) {
			precio+=entrada.getPrecioT();
		}
		
		return precio;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public List<Entrada> getEntradas(){
		return this.entradas;
	}
	
}
