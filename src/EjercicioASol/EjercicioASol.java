package EjercicioASol;

/**
 * 
 * @author daniel.tendero
 *
 */
class Puerta {
	// CREAMOS LA CLASE PUERTA CON DOS VARIABLES ESTÁTICAS (ACCESIBLES DESDE
	// CUALQUIER CLASE DE MANERA ESTATICA)
	public static boolean abierta;
	public static int contador;
}

class Abrir extends Thread {
	public void run() {
		// CREAMOS UN HILO QUE TENDRÁ UN BUCLE QUE CUENTE HASTA MIL
		for (int i = 0; i < 1000; i++)
			// SI LA PUERTA NO ESTA ABIERTA , LA ABRIMOS
			if (!Puerta.abierta) {
				Puerta.abierta = true;
				Puerta.contador++;
			} else {// SI LA PUERTA YA ESTÁ ABIERTA, NO SUMAMOS EN EL BUCLE (i--
					// anula i++)
				i--;

			}
		// IMPRIMIMOS QUE SE HA TERMINADO LA EJECUCIÓN DEL HILO
		// Hilo abrir
		System.out.println("Abrir terminando");
	}
}

class Cerrar extends Thread {
	public void run() {
		// CREAMOS OTRO HILO , QUE TIENE UN BUCLE QUE CUENTE HASTA MIL
		for (int i = 0; i < 1000; i++)
			// SI LA PUERTA ESTA ABIERTA, LA CIERRA
			if (Puerta.abierta) {
				Puerta.abierta = false;
				Puerta.contador--;
			} else {// SI LA PUERTA NO ESTÁ ABIERTA , NO SUMA UNA EJECUCION DEL
					// BUclE
				i--;

			}
		// IMPRIME EL CIERRE DEL BUCLE
		System.out.println("Cerrar terminando");
	}
}

public class EjercicioASol {
	public static void main(String[] args) throws InterruptedException {
		// DECIMOS QUE LA PUERTA ESTA ABIERTA AL INICIO DE LA EJECUCÓN.
		Puerta.abierta = true;
		// INSTANCIAMOS LOS DOS HILOS
		Thread a = new Abrir();
		Thread c = new Cerrar();
		// ARRANCAMOS LOS DOS HILOS
		a.start();
		c.start();
		// DECIMOS AL HILO PRINCIPAL (MAIN) QUE ESPERE HASTA QUE TERMINEN LOS
		// HILOS
		a.join();
		c.join();
		// IMPRIMIMOS EL CONTADOR
		System.out.println("El resultado final es: " + Puerta.contador);
		// MI SOLUCIÓN PROPUESTA HA SIDO CAMBIAR LOS CONTADORES A CUANDO SE ABRE
		// Y SE CIERRA (CUANDO SE ABRE ++ Y CUANDO SE CIERRA --, NO ES UNA
		// SOLUCIÓN PERFECTA PERO DA 0 LA MAYORIA DE LAS VECES
	}
}