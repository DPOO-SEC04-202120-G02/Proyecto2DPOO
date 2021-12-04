package modelo;

public class Descuento extends Promocion{

	private Float porcentaje;
	
	public Descuento(String mensaje,String codigo,Fecha fecha,Float porcentaje_in) {
		super(mensaje,codigo,fecha);
		porcentaje=porcentaje_in;
	}
	
}
