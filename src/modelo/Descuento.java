package modelo;

import java.util.Calendar;
import java.util.List;

public class Descuento extends Promocion{

	private Float porcentaje;

	public Descuento(String mensaje,String codigo,Fecha fecha,Float porcentaje_in) {
		super(mensaje,codigo,fecha);
		porcentaje=porcentaje_in;
	}

	public boolean VerificarAplicacion(Compra compra) {

		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		Fecha Fecha_actual=new Fecha(day,month+1,year);

		if(Fecha_actual.compFecha(super.fecha_max)) {
			return true;
		}

		return false;
	}

	public void AplicarACompra(Compra compra) {
		List<Entrada> entradas = compra.getEntradas();
		String nombre="";
		for (Entrada entrada : entradas) {
			if(entrada.getCodigo()==super.codigo_producto) {
				if(nombre.equals("")) {nombre=entrada.getNombre_producto();}
				entrada.setPrecioT(entrada.getPrecioT()*(1-porcentaje));
				compra.AddPromocion(super.mensaje);
			}
		}

	}

}
