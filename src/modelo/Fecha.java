package modelo;

import java.io.*;

public class Fecha implements Serializable {
	private int dia;
	private int mes;
	private int año;
	
	public Fecha(int dia, int mes, int año) {
		this.dia = dia;
		this.mes = mes;
		this.año = año;
	}
	
	public String toString() {
		return (Integer.toString(dia) + "/" + Integer.toString(mes) + "/" + Integer.toString(año)); 
	}
	
	public int getDia() {
		return dia;
	}
	
	public int getMes() {
		return mes;
	}
	
	public int getAño() {
		return año;
	}
	
	public boolean compFecha(Fecha fechaAComp) {
		boolean retorno = false;
		
		if (fechaAComp.getAño() < año) {
			retorno = true;
		}
		else if ((fechaAComp.getAño() == año) && (fechaAComp.getMes() < mes)) {
			retorno = true;
		}
		else if ((fechaAComp.getAño() == año) && (fechaAComp.getMes() == mes) && (fechaAComp.getDia() < dia)) {
			retorno = true;
		}
		
		return retorno;
	}
	

}