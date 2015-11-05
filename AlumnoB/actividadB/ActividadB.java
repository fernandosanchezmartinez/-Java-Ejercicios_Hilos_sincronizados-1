package actividadB;

/**
 * 
 * Ésta clase Puerta inicializa 3 variables, 2 de ellas boleanas(corresponden
 * con los cerrojos A/B); y una variable int que representa un contador.
 */
class Puerta2 {
	public static boolean CerrojoA;
	public static boolean CerrojoB;
	public static int contador;
}

/**
 * 
 * @author fernando.sanchez || Ésta clase LlaveA se corresponde con el primer
 *         hilo(extiende de Thread)
 */
class LlaveA extends Thread {
	public void run() {// recoge las acciones del Thread
		while (!Puerta2.CerrojoB)
			// mientras que la puerta no tenga cerrojoB. 
			Puerta2.CerrojoA = true;// la puerta tiene CerrojoA (true)

		;
		System.out.println("LlaveA terminando");// se imprime que la LlaveA ha
		// terminado

	}
}

/**
 * 
 * @author fernando.sanchez || Ésta clase LlaveB se corresponde con el segundo
 *         hilo (extiende de Thread)
 */
class LlaveB extends Thread {
	public void run() {// recoge las acciones del Thread
		while (!Puerta2.CerrojoA)
			// mientras que la puerta no tenga cerrojoB
			Puerta2.CerrojoB = true;// la puerta tiene CerrojoB (true)

		;
		System.out.println("LlaveB terminando");

	}
}

/**
 * 
 * @author fernando.sanchez Ésta es la clase principal
 */
public class ActividadB {
	/**
	 * Ésta es la clase que contiene el main y es la principal
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Puerta2.CerrojoA = false;// las puertas aparecen false al iniciar
		Puerta2.CerrojoB = false;
		Thread a = new LlaveA();// se crean los 2 hilos
		Thread b = new LlaveB();
		a.start();// se inicializan los hilos
		b.start();
		System.out.println("Comienzo del hilo principal");
		a.join();// permite mantener a la espera un hilo hasta que termine el
					// siguiente
		b.join();
		System.out.println("Fin del hilo principal");
	}
}
