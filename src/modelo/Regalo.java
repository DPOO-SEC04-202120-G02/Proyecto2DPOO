package modelo;

import java.util.Calendar;
import java.util.List;

public class Regalo extends Promocion{
	
	private int unidades_pkg;//Tamaño del grupo de unidades al que se aplica el regalo
	private int unidades_extra;//Unidades extra por cada grupo
	
	public Regalo(String mensaje,String codigo,Fecha fecha,int u_min,int u_ext) {
		super(mensaje,codigo,fecha);
		unidades_extra=u_ext;
		unidades_pkg=u_min;
	}
	
	public boolean VerificarAplicacion(Compra compra) {
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		Fecha Fecha_actual=new Fecha(day,month+1,year);
		
		if(Fecha_actual.compFecha(super.fecha_max)) {
			return true;
		}
		
		boolean resultado=false;
		List<Entrada> entradas = compra.getEntradas();
		int cantidad=0;
		int i=0;
		while (!resultado && i<entradas.size()) {
			if(entradas.get(i).getCodigo()==super.codigo_producto) {
				cantidad+=entradas.get(i).getCantidad_producto();
			}
			i++;
			if(cantidad>=unidades_pkg) {
				compra.AddPromocion(super.mensaje);
				resultado=true;
			}
		}
		return resultado;
	}
	
	public void AplicarACompra(Compra compra) {
		List<Entrada> entradas = compra.getEntradas();
		int cantidad=0;
		String nombre="";
		for (Entrada entrada : entradas) {
			if(entrada.getCodigo()==super.codigo_producto) {
				cantidad+=entrada.getCantidad_producto();
				if(nombre.equals("")) {nombre=entrada.getNombre_producto();}
			}
		}
		int n=((int) (cantidad/unidades_pkg))*unidades_extra;//Cantidad de producto regalada
		compra.addEntrada(0, nombre+"(Regalo)", n, super.codigo_producto);
	}

}
