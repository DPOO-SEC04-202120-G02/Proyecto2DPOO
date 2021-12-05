package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ControladorCompra {
	
	private Compra compra_actual;
	private AdminProductos admind_prod;
	private ArrayList<Promocion> promociones;//Promociones cargadas al ejecutar el programa.
	
	public ControladorCompra() {
		compra_actual= null;
		admind_prod = new AdminProductos();
		promociones= new ArrayList<Promocion>(); 
		try {
			admind_prod.cargarProductos();
			//CargarDescuentos();
			CargarRegalos();
			CargarMultiplicadores();
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
		for (Promocion promocion : promociones) {
			System.out.println(promocion.DarCodigo());
			System.out.println(promocion.DarMensaje());
		}
	}
	
	public Producto setIconEntrada(int codigo) {
		return admind_prod.consultarProducto(codigo);
	}
	
	public void AplicarPromociones() {
		for (Promocion promocion : promociones) {
			if(promocion.VerificarAplicacion(compra_actual)) {
				promocion.AplicarACompra(compra_actual);
			}
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
