package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CargadorPromociones {
	
	private static CargadorPromociones instancia = null;
	private String homeDC;
	
	public CargadorPromociones() {
		homeDC=System.getProperty("user.dir");
	}

	public static CargadorPromociones DarInstancia() {
		if(instancia == null) {
			instancia = new CargadorPromociones();
		}
		return instancia;
	}
	
	public BufferedReader CargarDescuentos() throws FileNotFoundException{
		BufferedReader br = new BufferedReader(new FileReader(homeDC+"\\promociones\\"+"Descuentos.csv"));
		return br;
	}
	
	public BufferedReader CargarRegalos() throws FileNotFoundException{
		BufferedReader br = new BufferedReader(new FileReader(homeDC+"\\promociones\\"+"Regalos.csv"));
		return br;
	}
	
	public BufferedReader CargarMultiplicadores() throws FileNotFoundException{
		BufferedReader br = new BufferedReader(new FileReader(homeDC+"\\promociones\\"+"MultiplicadoresPuntos.csv"));
		return br;
	}
	
}
