package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ControladorCompra {
	
	private Compra compra_actual;
	private AdminProductos admind_prod;
	private ArrayList<Promocion> promociones;//Promociones cargadas al ejecutar el programa.
	private HashMap<String, Combo> combos;//Combos
	
	public ControladorCompra() {
		compra_actual= null;
		admind_prod = new AdminProductos();
		promociones= new ArrayList<Promocion>(); 
		combos=new HashMap<String, Combo>();
		try {
			admind_prod.cargarProductos();
			CargarDescuentos();
			CargarRegalos();
			CargarMultiplicadores();
			CargarCombos();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Producto setIconEntrada(int codigo) {
		return admind_prod.consultarProducto(codigo);
	}
	
	public void AplicarPromociones() {
		for (Promocion promocion : promociones) {
			if(promocion.VerificarAplicacion(compra_actual)) {//Primero verifica que la promocion no este vencida, si no, la aplica.
				promocion.AplicarACompra(compra_actual);
			}
		}
	}
	
	
	public void CargarCombos() {
		try {
			BufferedReader br = CargadorPromociones.DarInstancia().CargarCombos();
			br.readLine();
			
			String linea = br.readLine();
		
			while (linea != null) 
			{
				String[] partes = linea.split(",");
				String codigo = partes[0];
				String nombre = partes[1];
				String[] codProductos=partes[2].split(";");
				String[] cantProductos=partes[3].split(";");
				String descuento=partes[4];
				String fechaVencimiento = partes[5];
				String[] fechaVencSeparada = fechaVencimiento.split("/");
				int diaVenc = Integer.parseInt(fechaVencSeparada[0]);
				int mesVenc = Integer.parseInt(fechaVencSeparada[1]);
				int añoVenc = Integer.parseInt(fechaVencSeparada[2]);
				Fecha fechaVenc = new Fecha(diaVenc, mesVenc, añoVenc);
				
				Combo new_combo= new Combo(codigo, nombre, codProductos, cantProductos, descuento, fechaVenc);
				combos.put(codigo, new_combo);
				
				linea = br.readLine();
			}

			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void CargarDescuentos() {
		try {
			BufferedReader br = CargadorPromociones.DarInstancia().CargarDescuentos();
			br.readLine();
			
			String linea = br.readLine();
		
			while (linea != null) 
			{
				String[] partes = linea.split(",");
				String msg = partes[0];
				String codigo = partes[1];
				Float porcentaje =Float.parseFloat(partes[2]);
				
				String fechaVencimiento = partes[3];
				String[] fechaVencSeparada = fechaVencimiento.split("/");
				int diaVenc = Integer.parseInt(fechaVencSeparada[0]);
				int mesVenc = Integer.parseInt(fechaVencSeparada[1]);
				int añoVenc = Integer.parseInt(fechaVencSeparada[2]);
				Fecha fechaVenc = new Fecha(diaVenc, mesVenc, añoVenc);
				
				Descuento desc_new = new Descuento(msg,codigo,fechaVenc,porcentaje);
				promociones.add(desc_new);
				
				linea = br.readLine();
			}

			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void CargarRegalos() {
		try {
			BufferedReader br = CargadorPromociones.DarInstancia().CargarRegalos();
			br.readLine();
			
			String linea = br.readLine();
		
			while (linea != null) 
			{
				String[] partes = linea.split(",");
				String msg = partes[0];
				String codigo = partes[1];
				int u_pkg=Integer.parseInt(partes[2]);
				int u_ext=Integer.parseInt(partes[3]);
				
				String fechaVencimiento = partes[4];
				String[] fechaVencSeparada = fechaVencimiento.split("/");
				int diaVenc = Integer.parseInt(fechaVencSeparada[0]);
				int mesVenc = Integer.parseInt(fechaVencSeparada[1]);
				int añoVenc = Integer.parseInt(fechaVencSeparada[2]);
				Fecha fechaVenc = new Fecha(diaVenc, mesVenc, añoVenc);
				
				Regalo regalo_new = new Regalo(msg,codigo,fechaVenc,u_pkg,u_ext);
				promociones.add(regalo_new);
				
				linea = br.readLine();
			}

			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void CargarMultiplicadores() {
		try {
			BufferedReader br = CargadorPromociones.DarInstancia().CargarMultiplicadores();
			br.readLine();
			
			String linea = br.readLine();
		
			while (linea != null) 
			{
				String[] partes = linea.split(",");
				String msg = partes[0];
				String codigo = partes[1];
				int multip=Integer.parseInt(partes[2]);
				
				String fechaVencimiento = partes[3];
				String[] fechaVencSeparada = fechaVencimiento.split("/");
				int diaVenc = Integer.parseInt(fechaVencSeparada[0]);
				int mesVenc = Integer.parseInt(fechaVencSeparada[1]);
				int añoVenc = Integer.parseInt(fechaVencSeparada[2]);
				Fecha fechaVenc = new Fecha(diaVenc, mesVenc, añoVenc);
				
				Multiplicador multip_new = new Multiplicador(msg,codigo,fechaVenc,multip);
				promociones.add(multip_new);
				
				linea = br.readLine();
			}

			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean addCombo(String codigo) throws NumberFormatException, FileNotFoundException, ClassNotFoundException, IOException, NoCantidadComboException {
		Combo comboNew= combos.get(codigo);
		
		if(!comboNew.VerificarFecha()) {
			throw new NoCantidadComboException();//Me equivoque con el nombre de la excepcion.
		}
		
		String[] productos=comboNew.DarProductos();
		String[] Cantidades=comboNew.DarCantidades();
		int j=0;
		while(j<Cantidades.length) {
			if(!admind_prod.verificarCantidad(Integer.parseInt(productos[j]),Float.parseFloat(Cantidades[j]))) {
				return false;
			}
			j++;
		}
		
		for (int i = 0; i < Cantidades.length; i++) {
			Float cantidad=Float.parseFloat(Cantidades[i]);
			addEntrada(Integer.parseInt(productos[i]),cantidad);
			compra_actual.DescontarPrecioUltimaEntrada(comboNew.DarDescuento());
		}
		
		return true;
	}
	
	public boolean addEntrada(int codigo, float cantidad) throws FileNotFoundException, ClassNotFoundException, IOException {
		admind_prod.cargarProductos();
		Producto prod = admind_prod.consultarProducto(codigo);
		if (admind_prod.verificarCantidad(codigo, cantidad)) {
			float precio=prod.getPrecioVenta()*cantidad;
			compra_actual.addEntrada(precio, prod.getNombre(), cantidad, codigo);
			try {
				admind_prod.restarCantidad(codigo, cantidad);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	public boolean nuevaCompra(Cliente cliente_n) {
		if (compra_actual!=null) {	
			return false;
		}else{
			compra_actual= new Compra(cliente_n);
			return true;
		}
	}
	
	public Compra cerrarCompra() {//Se retorna la compra y se marca que no hay compras porcesandose.
		Compra c= compra_actual;
		return c;
	}
	
	public void set_compraAC2null() {
		compra_actual=null;
	}
	
}
