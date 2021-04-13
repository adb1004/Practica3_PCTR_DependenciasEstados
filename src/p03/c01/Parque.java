package src.p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;

public class Parque implements IParque{

	private static final int MINPERSONAS=0;
	private static final int MAXPERSONAS=50;
	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	
	
	public Parque() {	// TODO
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
		// TODO
	}


	@Override
	public void entrarAlParque(String puerta){		// TODO
		
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		// TODO
				
		
		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");
		
		// TODO
		
		// Comprobar los invariantes
		checkInvariante();
		
	}
	
	// 
	// TODO Método salirDelParque
	//
	public void salirDelParque(String puerta){
		
		// Si no hay entradas por esa puerta, inicializamos
				if (contadoresPersonasPuerta.get(puerta) == null){
					contadoresPersonasPuerta.put(puerta, 0);
				}
				
				contadorPersonasTotales--;		
				contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)-1);
				
				// Imprimimos el estado del parque
				imprimirInfo(puerta, "Salida");
				
				// Comprobar los invariantes
				checkInvariante();
	}
	
	
	private void imprimirInfo (String puerta, String movimiento){
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); //+ " tiempo medio de estancia: "  + tmedio);
		
		// Iteramos por todas las puertas e imprimimos sus entradas
		for(String p: contadoresPersonasPuerta.keySet()){
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}
	
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
			Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
			while (iterPuertas.hasMoreElements()) {
				sumaContadoresPuerta += iterPuertas.nextElement();
			}
		return sumaContadoresPuerta;
	}
	
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales : "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		assert MINPERSONAS <= contadorPersonasTotales : "INV: La suma de contadores de las puertas debe como mínimo 0";
		assert MAXPERSONAS >= contadorPersonasTotales : "INV: La suma de contadores de las puertas debe como máximo 50";
	}

	protected void comprobarAntesDeEntrar(){	// TODO
		//
		// TODO
		//
	}

	protected void comprobarAntesDeSalir(){		// TODO
		//
		// TODO
		//
	}


}
