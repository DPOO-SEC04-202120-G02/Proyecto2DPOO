package modelo;

import java.util.*;

public class Compra {

	List<Entrada> entradas;
	Cliente cliente;
	int puntos;

	public Compra(Cliente cliente_compra) {
		entradas = new ArrayList<Entrada>();
		cliente = cliente_compra;
		puntos = 0;
	}

	public void addEntrada(float precio, String nombre, float cantidad, int codigo) {
		Entrada Entrada_new= new Entrada(precio, nombre, cantidad, codigo);
		entradas.add(Entrada_new);
	}

	public float calcularPrecio(){

		float precio = 0;

		for (Entrada entrada : entradas) {
			precio+=entrada.getPrecioT();
		}

		precio-=puntos*15;

		return precio;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public List<Entrada> getEntradas(){
		return this.entradas;
	}

	public void AgregarPuntos(int new_puntos) throws Exception{
		if(new_puntos>cliente.getPuntos()) {
			throw new Exception("No dispone de esa cantidad de puntos");
		}
		puntos=new_puntos;
	}

	public void ValidarPuntos(int puntos_test) throws Exception{
		float precio = 0;

		for (Entrada entrada : entradas) {
			precio+=entrada.getPrecioT();
		}

		precio-=puntos_test*15;

		if(precio<0) {
			throw new Exception("No puede redimir más de "+(precio+=15*puntos_test)/15+" puntos para esta compra.");
		}

	}
	
	public int DarPuntos() {
		return puntos;
	}

}
