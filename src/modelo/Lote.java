package modelo;

import java.io.*;

public class Lote implements Serializable {
	private int numero;
	private float precioCompra;
	private float cantidad;
	private Fecha fechaVencimiento;
	
	public Lote(int numero, float precioCompra, float cantidad, Fecha fechaVencimiento) {
		this.numero = numero;
		this.precioCompra = precioCompra;
		this.cantidad = cantidad;
		this.fechaVencimiento = fechaVencimiento;
	}

	public Fecha getFechaVencimiento() {
		return fechaVencimiento;
	}

	public float getCantidad() {
		return cantidad;
	}
	
	public float getPrecioCompra() {
		return precioCompra;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
		
	}
	
	

}