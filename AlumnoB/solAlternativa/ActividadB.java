package solAlternativa;

/**
 * 
 * @author fernando.sanchez
 *
 */
class Puerta {// clase que gestiona las Puertas
	public static boolean CerrojoA;
	public static boolean CerrojoB;
	public static int contador;
}

// Ésta clase LlaveB se corresponde con el segundo hilo (extiende de Thread)
class LlaveA extends Thread {
	public void run() {// ejecuta las acciones del hilo

		Puerta.CerrojoA = true;// La puerta del cerrojoA se abre
		if (Puerta.CerrojoB)// Si es puerta cerrojoB
			Puerta.contador++;// se suma uno al contador
		Puerta.CerrojoA = false;// se cierra la puerta cerrojoA, ha
								// terminado

		System.out.println("LlaveA terminando");
	}
}

// Ésta clase LlaveA se corresponde con el segundo hilo (extiende de Thread)
class LlaveB extends Thread {
	public void run() {// ejecuta las acciones del hilo

		Puerta.CerrojoB = true;// se abre la puerta B
		if (Puerta.CerrojoA)// si es puerta cerrojo A
			Puerta.contador++;// se suma uno al contador
		Puerta.CerrojoB = false;// se cierra la puerta B, ha terminado

		System.out.println("LlaveB terminando");
	}
}

/**
 * 
 * @author fernando.sanchez
 *
 */
public class ActividadB {
	/**
	 * Ésta es la clase que contiene el main y es la principal
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Puerta.CerrojoA = true;// 
		Puerta.CerrojoB = false;
		Thread a = new LlaveA();// se crean los hilos
		Thread b = new LlaveB();
		a.start();// se inicializan los hilos
		b.start();
		a.join();// permite mantener a la espera un hilo hasta que termine el
		// siguiente
		b.join();

	}
}
