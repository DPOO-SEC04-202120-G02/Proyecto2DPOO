package modelo;

import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;

public class Producto implements Serializable {
	private int codigo;
	private String nombre;
	private float precioVenta;
	private float gananciaVenta;
	private boolean empacado;
	private float perdidos;
	private ArrayList<Lote> lotes;
	private ArrayList<String> categorias;
	private ImageIcon imagen;
	
	public Producto(int codigo, String nombre, boolean empacado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.empacado = empacado;
		lotes = new ArrayList<Lote>();
		categorias =  new ArrayList<String>();
		BufferedImage myPicture;
		String directorio = System.getProperty("user.dir");
		try {
			myPicture = ImageIO.read(new File(directorio + "\\imagenes\\" + this.nombre + ".png"));
			this.imagen = new ImageIcon( new ImageIcon(myPicture).getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT)); 
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public boolean getEmpacado() {
		return empacado;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public float getGananciaVenta() {
		return gananciaVenta;
	}

	public void setGananciaVenta(float gananciaVenta) {
		this.gananciaVenta = gananciaVenta;
	}

	public void addLote(Lote lote) {
		lotes.add(lote);
	}
	
	public float getPerdidos(){
		return perdidos;
	}
	
	public void addCategoria(String nombreCategoria) {
		categorias.add(nombreCategoria);
	}
	
	public boolean eliminarLotesVencidos(Fecha fechaActual) {
		boolean retorno = false;
		for (Iterator<Lote> iterator = lotes.iterator(); iterator.hasNext();) {
		    Lote lote = iterator.next();
		    Fecha fechaVencimiento = lote.getFechaVencimiento();
			if (fechaVencimiento.compFecha(fechaActual) == true) {
				perdidos += lote.getCantidad();
				iterator.remove();
				retorno = true;
		    }
		}
		return retorno;
	}
	
	public float contarCantidad() {
		float cantidad = 0;
		for (Lote lote : lotes) {
			cantidad += lote.getCantidad();
		}
		return cantidad;
	}
	
	public void restarCantidad(float cantidad) {
		while (cantidad > 0) {
			for (Lote lote : lotes) {
				if (lote.getCantidad() > cantidad) {
					lote.setCantidad(lote.getCantidad() - cantidad);
					gananciaVenta += (precioVenta - lote.getPrecioCompra()) * cantidad;
					cantidad = 0;
					break;
					
				}
				else if (lote.getCantidad() <= cantidad)
					gananciaVenta += (precioVenta - lote.getPrecioCompra()) * lote.getCantidad();
					cantidad -= lote.getCantidad();
					lotes.remove(lote);
			}
		}
		
	}

	public ImageIcon getImg() {
		return imagen;
	}
		

}
