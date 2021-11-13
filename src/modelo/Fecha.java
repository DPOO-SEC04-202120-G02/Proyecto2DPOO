package modelo;

import java.io.*;

public class Fecha implements Serializable {
	private int dia;
	private int mes;
	private int a�o;
	
	public Fecha(int dia, int mes, int a�o) {
		this.dia = dia;
		this.mes = mes;
		this.a�o = a�o;
	}
	
	public String toString() {
		return (Integer.toString(dia) + "/" + Integer.toString(mes) + "/" + Integer.toString(a�o)); 
	}
	
	public int getDia() {
		return dia;
	}
	
	public int getMes() {
		return mes;
	}
	
	public int getA�o() {
		return a�o;
	}
	
	public boolean compFecha(Fecha fechaAComp) {
		boolean retorno = false;
		
		if (fechaAComp.getA�o() < a�o) {
			retorno = true;
		}
		else if ((fechaAComp.getA�o() == a�o) && (fechaAComp.getMes() < mes)) {
			retorno = true;
		}
		else if ((fechaAComp.getA�o() == a�o) && (fechaAComp.getMes() == mes) && (fechaAComp.getDia() < dia)) {
			retorno = true;
		}
		
		return retorno;
	}
	

}