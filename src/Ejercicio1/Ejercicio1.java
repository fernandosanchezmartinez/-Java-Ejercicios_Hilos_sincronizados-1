package Ejercicio1;

import java.util.concurrent.Semaphore;

class OrdenHilo extends Thread {
	String datoOperacion;
	Semaphore semaforo;

	public OrdenHilo(String datoOperacion, Semaphore semaforo) {
		this.datoOperacion = datoOperacion;
		this.semaforo = semaforo;
	}

	public void run() {
		// COMPRUEBO SI ES NUMEROS O LETRAssa
		if (datoOperacion.equalsIgnoreCase("numeros")) {
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
			}
			semaforo.release();
		} else if (datoOperacion.equalsIgnoreCase("letras")) {
			// ARRAY DE CARACTERES
			try {
				semaforo.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			char arrayLetras[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
					'j' };
			for (int i = 0; i < arrayLetras.length; i++) {
				// IMPRIMO ESE ARRAY
				System.out.println(arrayLetras[i]);
			}

		}

	}
}

/**
 * 
 * @author daniel.tendero
 *
 */
public class Ejercicio1 {
	public static void main(String args[]) {
		// HE CREADO DOS HILOS (la clase es la misma) y a cada uno le paso un
		// parametro diferente por el constructor, uno imprimirá letrasy otro
		// numeros.
		Semaphore semaforo = new Semaphore(0);
		// LES PASAMOS EL SEMAFORO INICIALIZADO A 0 COMO PARÄMETRO

		OrdenHilo hilo1 = new OrdenHilo("letras", semaforo);// Crea un nuevo
															// hilo
		OrdenHilo hilo2 = new OrdenHilo("numeros", semaforo);// Crea un nuevo
																// hilo

		// Arranco los hilos
		hilo1.start();
		hilo2.start();

	}
}