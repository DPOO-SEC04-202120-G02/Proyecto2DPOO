package modelo;

import java.util.List;

public class Multiplicador extends Promocion{
	
	private int multiplicador;
	
	public Multiplicador(String mensaje,String codigo,Fecha fecha,int multiplicador_in) {
		super(mensaje,codigo,fecha);
		
		multiplicador=multiplicador_in;
	}
	
	
}
