package modelo;

public class NoCantidadComboException extends Exception{

	public NoCantidadComboException() {
		super("El combo está vencido.");
	}

}
