package EjercicioA;

/**
 * 
 * @author daniel.tendero
 *
 */
class Puerta {
	// CREAMOS LA CLASE PUERTA CON DOS VARIABLES EST�TICAS (ACCESIBLES DESDE
	// CUALQUIER CLASE DE MANERA ESTATICA)
	public static boolean abierta;
	public static int contador;
}

class Abrir extends Thread {
	public void run() {
		// CREAMOS UN HILO QUE TENDR� UN BUCLE QUE CUENTE HASTA MIL
		for (int i = 0; i < 1000; i++)
			// SI
			if (!Puerta.abierta) {
				Puerta.abierta = true;
			} else {
				i--;
				Puerta.contador++;
			}
		System.out.println("Abrir terminando");
	}
}

class Cerrar extends Thread {
	public void run() {
		for (int i = 0; i < 1000; i++)
			if (Puerta.abierta)
				Puerta.abierta = false;
			else {
				i--;
				Puerta.contador++;
			}
		System.out.println("Cerrar terminando");
	}
}

public class EjercicioA {
	public static void main(String[] args) throws InterruptedException {
		Puerta.abierta = true;
		Thread a = new Abrir();
		Thread c = new Cerrar();
		a.start();
		c.start();
		a.join();
		c.join();
		System.out.println("El resultado final es: " + Puerta.contador);
	}
}