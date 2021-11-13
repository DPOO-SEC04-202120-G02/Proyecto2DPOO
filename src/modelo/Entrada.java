package modelo;

public class Entrada {
	
	float precioT;
	String nombre_producto;
	float cantidad_producto;
	int codigo_producto;
	
	public Entrada(float precio, String nombre, float cantidad, int codigo) {
		precioT=precio;
		nombre_producto=nombre;
		cantidad_producto=cantidad;
		codigo_producto=codigo;
	}
	
	public float getPrecioT() {
		return precioT;
	}
	public String getNombre_producto() {
		return nombre_producto;
	}
	public float getCantidad_producto() {
		return cantidad_producto;
	}
	public int getCodigo() {
		return codigo_producto;
	}
}
