package modelo;

import java.io.Serializable;

public class Cliente implements Serializable {
	private int puntos;
	private int cedula;
	private int edad;
	private String sexo;
	private String estadoCivil;
	private String situacionLaboral;
	private String nombre;
	
	public Cliente(int cedula, int edad, String sexo, String estadoCivil, String situacionLaboral,String nombre) {
		this.cedula = cedula;
		this.edad = edad;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
		this.situacionLaboral = situacionLaboral;
		this.nombre=nombre;
		this.puntos=0;//Todos los clientes se crean con 0 puntos.
	}
	
	public int getPuntos() {
		return puntos;
	}
	
	public void sumarPuntos(float cantidad) {
		puntos += cantidad;
	}
	
	public int getCedula() {
		return cedula;
	}
	
	public String getNombre() {
		return nombre;
	}
	

}
