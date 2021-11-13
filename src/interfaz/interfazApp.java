package interfaz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import control.*;
import modelo.*;

public class interfazApp {
	
	CoordinadorPos CooPos;
	CoordInventario cooInv;
	
	public interfazApp() {
		CooPos = new CoordinadorPos();
		cooInv = new CoordInventario();
 	}
	
	public void ejecutarDireccionamiento() throws ClassNotFoundException, IOException
	{
		
		System.out.println("Bienvenido al direccionamiento de sistemas\n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenuDireccionamiento();
				
				int opcion_seleccionada = Integer.parseInt(input("Por favor digite la opción que desea utilizar :"));
				if (opcion_seleccionada == 1) {
					ejecutarAplicacionPos();
				}
				else if (opcion_seleccionada == 2 )
				{
					ejecutarAplicacionInv();
				}
				
				else if (opcion_seleccionada == 3)
				{
					System.out.println("Saliendo de la aplicacion ...");
					continuar = false;
				}
				else
				{
					System.out.println("Por favor seleccione una opcion valida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los nÃºmeros de las opciones.");
			}
		}
	}
	
	public void ejecutarAplicacionPos()
	{
		
		System.out.println("Funcionamiento de Sistemas POS\n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenuPos();
				// mostrarSistemas_POS_SI_Integrados();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
				if (opcion_seleccionada == 1 && this.CooPos != null)
					registrarCliente();
				else if (opcion_seleccionada == 5 )
				{
					System.out.println("Saliendo de la aplicacion ...");
					continuar = false;
				}else if (opcion_seleccionada == 2 )
				{
					int cedula = Integer.parseInt(input("Cedula del cliente que registra la compra."));
					nuevaCompra(cedula);
					
				}else if (opcion_seleccionada == 3 )
				{	
					System.out.println("Ingrese los datos de la entrada que quiere hacer a su compra.");
					int codigo = Integer.parseInt(input("Codigo del producto que desea comprar: "));
					float cantidad = Float.valueOf(input("Cantidad del producto que desea comprar (si el producto se vende por empaques, se registra el entero más cercano al nuemro ingresado): "));
					try {
						nuevaEntrada(codigo,cantidad);
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
				}else if (opcion_seleccionada == 4 )
				{
					cerrarCompra();
				}
					
				else if (this.CooPos == null)
				{
					System.out.println("Para poder ejecutar esta opcion hay que verificar el inicio del sistema.");
				}
				else
				{
					System.out.println("Por favor seleccione una opcion valida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los nÃºmeros de las opciones.");
			}
		}
	}
	
	public void ejecutarAplicacionInv() throws IOException, ClassNotFoundException
	{
		cooInv.cargarProductos();
		System.out.println("Funcionamiento de Sistema Inventario\n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenuInv();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opcion: "));
				if (opcion_seleccionada == 1 && this.cooInv != null) {
					String rutaArchivo = input("Por favor digite la ruta del archivo que desea leer: ");
					cooInv.cargarLotes(rutaArchivo);
				}
				else if (opcion_seleccionada == 2) {
					String fechaCompleta = input("Por favor ingrese la fecha de hoy en el formato dd/mm/aaaa: ");
					String[] partesFecha = fechaCompleta.split("/");
					int dia = Integer.parseInt(partesFecha[0]);
					int mes = Integer.parseInt(partesFecha[1]);
				    int año = Integer.parseInt(partesFecha[2]);
					int idProducto = Integer.parseInt(input("Por favor ingrese el código del producto sobre el cual quiere realizar esta operación: "));
					cooInv.eliminarLotesVencidos(idProducto, dia, mes, año);	
				}
				else if (opcion_seleccionada == 3 ){
					int idProducto = Integer.parseInt(input("Por favor ingrese el código del producto sobre el cual quiere realizar esta operación: "));
					float salida = cooInv.consultarDesempeñoFin(idProducto);
					Producto producto = cooInv.consultarInfoProducto(idProducto);
					System.out.println("Para el producto " + producto.getNombre() + " el desempeño financiero ha sido: " + salida + ".\nPara este producto se han perdido: " + producto.getPerdidos());
				}
				else if (opcion_seleccionada == 4 ){
					int idProducto = Integer.parseInt(input("Por favor ingrese el código del producto sobre el cual quiere realizar esta operación: "));
					Producto producto = cooInv.consultarInfoProducto(idProducto);
					System.out.println("Para el producto " + producto.getNombre() + " hay la siguiente disponibilidad en el inventario: " + producto.contarCantidad());
				}
				else if (opcion_seleccionada == 5 ){
					ejecutarDireccionamiento();
				}
					
				else if (this.CooPos == null)
				{
					System.out.println("Para poder ejecutar esta opcion hay que verificar el inicio del sistema.");
				}
				else
				{
					System.out.println("Por favor seleccione una opcion valida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los nÃºmeros de las opciones.");
			}
		}
	}
	
	public void nuevaEntrada(int codigo, float cantidad) throws FileNotFoundException, ClassNotFoundException, IOException {
		if(CooPos.addEntrada(codigo, cantidad)) {
			System.out.println("Se ha registrado la entrada.");
		}else {
			System.out.println("Cantidad insuficiente en inventario.");
		}
	}
	
	public void nuevaCompra(int cedula) {
		if (CooPos.nuevaCompra(cedula)){
			System.out.println("Se ha iniado una nueva compra para el cliente con cedula "+Integer.toString(cedula));
		}else {
			System.out.println("No se pudó crear. Verifique que la cedula sea correcta y que no hayan compras procesandose actualmente");
		}
	}
	
	public void cerrarCompra() {
		Compra compra=CooPos.cerrarCompra();
		float precio_total=0;
		System.out.printf("%-20s %-20s %-20s %-20s\n","Codigo","Nombre","Cantidad","Costo");
		for (Entrada entrada : compra.getEntradas()) {
			System.out.printf("%-20s %-20s %-20s %-20s\n", entrada.getCodigo(), entrada.getNombre_producto(),entrada.getCantidad_producto(),entrada.getPrecioT());
			precio_total+=entrada.getPrecioT();
		}
		System.out.println("Costo final de la compra: "+String.valueOf(precio_total));
		CooPos.set_compraAC2null();
	}
	
	public void registrarCliente() {
		int cedula_new = Integer.parseInt(input("Cedula del nuevo cliente: "));
		int edad_new = Integer.parseInt(input("Edad del nuevo cliente: "));
		String sexo_new = input("Sexo del nuevo cliente: ");
		String EstadoCivil_new = input("Estado civil del nuevo cliente: ");
		String Empleo_new = input("situacion laboral del nuevo cliente: ");
		CooPos.registrarCliente(cedula_new, edad_new, sexo_new, EstadoCivil_new, Empleo_new);
	}
	
	public void mostrarMenuDireccionamiento() {
		System.out.println("\nVISTA DE DIRECCIONAMIENTO.\n");
		System.out.println("1. Sistema POS.\n");
		System.out.println("2. Sistema Inventario.\n");
	}
	
	public void mostrarMenuPos() {
		System.out.println("\nVISTA SISTEMA POS.\n");
		System.out.println("1. Registrar cliente.\n");
		System.out.println("2. Registrar una nueva compra.\n");
		System.out.println("3. Registrar una nueva entrada a la compra actual.\n");
		System.out.println("4. Cerrar la compra actual e imprimir la factura.\n");
		System.out.println("5. Volver al menú de redireccionamiento.\n");
	}
	
	public void mostrarMenuInv() {
		System.out.println("\nVISTA SISTEMA INVENTARIO.\n");
		System.out.println("1. Cargar lotes desde un archivo.\n");
		System.out.println("2. Eliminar los lotes vencidos de un producto.\n");
		System.out.println("3. Consultar el desempeño financiero de un producto.\n");
		System.out.println("4. Consultar la información de un producto.\n");
		System.out.println("5. Volver al menú de redireccionamiento.\n");
	}
	
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		try
		{
			interfazApp consola = new interfazApp();
			consola.ejecutarDireccionamiento();
		}
		catch (Exception e)
		{
			System.out.println("Problema al crear la consola de la aplicacion: " + e.getMessage());
		}
	}

}

