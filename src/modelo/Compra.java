package modelo;

import java.util.*;

public class Compra {

	List<Entrada> entradas;
	List<String> Promociones;// Los mensajes de las promociones aplicadas.
	Cliente cliente;
	int puntos;
	int puntos_extra;// Puntos dados por bonificaciones

	public Compra(Cliente cliente_compra) {
		entradas = new ArrayList<Entrada>();
		cliente = cliente_compra;
		puntos = 0;// Puntos a usar por el cliente
		Promociones = new ArrayList<String>();
		puntos_extra = 0;
	}

	public void addPuntosExtra(int pts) {
		puntos_extra += pts;
	}

	public void AddPromocion(String msg) {
		Promociones.add(msg);
	}

	public void addEntrada(float precio, String nombre, float cantidad, int codigo) {
		Entrada Entrada_new = new Entrada(precio, nombre, cantidad, codigo);
		entradas.add(Entrada_new);
	}
	
	public void DescontarPrecioUltimaEntrada(Float descuento) {
		Entrada last=entradas.get(entradas.size()-1);
		last.Descontar(descuento);
	}

	public int DarPuntosAgregados() {//Los puntos que la compra le da al cliente. 
		float precio_total=0; 
		for (Entrada entrada: entradas)
		{ 
			precio_total+=entrada.getPrecioT(); 
		} 
		return (int) precio_total/1000 + puntos_extra; 
		}


	public Cliente getCliente() {
		return this.cliente;
	}

	public List<Entrada> getEntradas() {
		return this.entradas;
	}

	public void AgregarPuntos(int new_puntos) throws Exception {
		if (new_puntos > cliente.getPuntos()) {
			throw new Exception("No dispone de esa cantidad de puntos");
		}
		puntos = new_puntos;
	}

	public void ValidarPuntos(int puntos_test) throws Exception {
		float precio = 0;

		for (Entrada entrada : entradas) {
			precio += entrada.getPrecioT();
		}

		precio -= puntos_test * 15;

		if (precio < 0) {
			throw new Exception(
					"No puede redimir más de " + (precio += 15 * puntos_test) / 15 + " puntos para esta compra.");
		}

	}

	public List<String> DarPromocionesAplicadas() {
		return Promociones;
	}

	public int DarPuntos() {//Retorna los puntos que se gastarán en esta compra.
		return puntos;
	}

	public int DarPuntosExtra() {//Retorna unicamente los puntos extra
		return puntos_extra;
	}

}
