package modelo;

abstract class Promocion {
	
	protected String mensaje;
	protected int codigo_producto;
	protected Fecha fecha_max; 
	public abstract boolean VerificarAplicacion(Compra compra);
	public abstract void AplicarACompra(Compra compra);
	
	public Promocion(String mensaje_in,String codigo,Fecha fecha) {
		mensaje=mensaje_in;
		codigo_producto=Integer.parseInt(codigo);
		fecha_max=fecha;
	}
	
	public String DarMensaje() {
		return mensaje;
	}
	
	public int DarCodigo() {
		return codigo_producto;
	}
	
	public Fecha Darfecha() {
		return fecha_max;
	}
	
}
