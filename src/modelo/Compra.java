package modelo;

import java.util.*;

public class Compra {

	List<Entrada> entradas;
	List<String> Promociones;//Los mensajes de las promociones aplicadas.
	Cliente cliente;
	int puntos;

	public Compra(Cliente cliente_compra) {
		entradas = new ArrayList<Entrada>();
		cliente = cliente_compra;
		puntos = 0;//Puntos a usar por el cliente
		Promociones=new ArrayList<String>(); 
	}

	public void AddPromocion(String msg) {
		Promociones.add(msg);
	}
	
	public void addEntrada(float precio, String nombre, float cantidad, int codigo) {
		Entrada Entrada_new= new Entrada(precio, nombre, cantidad, codigo);
		entradas.add(Entrada_new);
	}
	
	public int DarPuntosExtra() {
		float precio_total=0;
		for (Entrada entrada: entradas) {
			precio_total+=entrada.getPrecioT();
		}
		return (int) precio_total/1000;
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
	
	public List<String> DarPromocionesAplicadas(){
		return Promociones;
	}
	
	public int DarPuntos() {
		return puntos;
	}

}
