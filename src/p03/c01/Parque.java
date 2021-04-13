package src.p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;

public class Parque implements IParque{

	private static final int MINPERSONAS=0;
	private int maxPersonas;
	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	
	
	public Parque(int maxPersonas) {	
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
		this.maxPersonas=maxPersonas;
	}


	@Override
	public synchronized void entrarAlParque(String puerta){				
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		//Comprobamos si cumple que el total de personas no supere el maximo.
		comprobarAntesDeEntrar();
				
		
		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");
		
		//Notificamos a todos los hilos
		notifyAll();
			
		// Comprobar los invariantes
		checkInvariante();
		
	}
	
	// 
	//  Método salirDelParque
	//
	public synchronized void salirDelParque(String puerta){
		
		// Si no hay entradas por esa puerta, inicializamos
				if (contadoresPersonasPuerta.get(puerta) == null){
					contadoresPersonasPuerta.put(puerta, 0);
				}
				
				//Comprobamos si cumple que el total de personas no sea menor que el minimo.
				comprobarAntesDeSalir();
				
				// Disminuimos el contador total y el individual
				contadorPersonasTotales--;		
				contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)-1);
				
				// Imprimimos el estado del parque
				imprimirInfo(puerta, "Salida");
				
				//Notificamos a todos los hilos
				notifyAll();
				
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
		assert maxPersonas >= contadorPersonasTotales : "INV: La suma de contadores de las puertas debe como máximo 50";
	}

	protected synchronized void comprobarAntesDeEntrar(){	
		if(contadorPersonasTotales == maxPersonas) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
	}

	protected synchronized void comprobarAntesDeSalir(){
		if(contadorPersonasTotales == MINPERSONAS) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
		}
	}


}
