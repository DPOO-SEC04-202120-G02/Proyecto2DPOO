package modelo;

import java.util.List;

public class Combo {

	private String codigo;
	private String nombre;
	private String[] codigosProductos;
	private String[] cantidadesProductos;
	private Float descuento;
	private Fecha fecha_vencimiento;
	
	public Combo(String codigox,String nombrex, String[] codigosProductosx,String[] CantidadesProductosx,String descuentox, Fecha vencimiento) {
		codigo = codigox;
		fecha_vencimiento=vencimiento;
		codigosProductos=new String[codigosProductosx.length];
		cantidadesProductos= new String[codigosProductosx.length];
		nombre=nombrex;
		descuento = Float.parseFloat(descuentox);
		for (int i = 0; i < codigosProductosx.length; i++) {
			codigosProductos[i]=codigosProductosx[i];
			cantidadesProductos[i]=CantidadesProductosx[i];
		}
	}

	public String DarNombre() {
		return nombre;
	}
	
	public String[] DarProductos() {
		return codigosProductos;
	}
	
	public String[] DarCantidades(){
		return cantidadesProductos;
	}
	
	public Float DarDescuento() {
		return descuento;
	}
	
}
