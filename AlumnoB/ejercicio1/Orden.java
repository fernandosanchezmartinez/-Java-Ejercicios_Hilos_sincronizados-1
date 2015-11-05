package ejercicio1;

import java.util.concurrent.Semaphore;
/**
 * 
 * @author fernando.sanchez
 *
 */

/**
 * Esta es la clase que representa al hilo que gestiona los n�meros
 */
class Hilo_num extends Thread {

	Semaphore SemaforoNum;// se crea un variable propia tipo Semaphore

	public Hilo_num(Semaphore SemaforoNum) {// en el constructor se pasa ese
											// sem�foro por par�metro
		this.SemaforoNum = SemaforoNum;
	}

	public void run() {// se encarga de las acciones del hilo

		for (int i = 0; i < 10; i++) {// se recorre el bucle y se imprimen los
										// n�meros del 1-10
			System.out.println(i);
			System.out.flush();
		}

		SemaforoNum.release();// mediante �sta funci�n el semaforo suma 1 ya que
								// ha quedado libre
	}
}

/**
 * Esta es la clase que representa al hilo que gestiona las letras
 */
class Hilo_letras extends Thread {

	Semaphore SemaforoLetras;// se crea un variable propia tipo Semaphore

	public Hilo_letras(Semaphore SemaforoLetras) {// en el constructor se pasa
													// ese sem�foro por
													// par�metro

		this.SemaforoLetras = SemaforoLetras;
	}

	public void run() { // se encarga de las acciones del hilo

		try {
			SemaforoLetras.acquire();// mediante �sta funci�n el sem�foro resta
										// 1 y como vale 0, no se puede restar y
										// pasa al siguiente
		} catch (Exception e) {
			System.out.println(e);
		}
		for (char letra = 65; letra < 75; letra++) {// se imprimen las 10
													// primeras letras
			System.out.println(letra);
		}

		SemaforoLetras.release();// el hilo queda liberado y se sumam 1
	}
}

public class Orden {
	static Semaphore Semaforo = new Semaphore(0);

	public static void main(String[] args) throws InterruptedException {
		Hilo_num hilo1 = new Hilo_num(Semaforo);
		Hilo_letras hilo2 = new Hilo_letras(Semaforo);

		hilo2.start();
		hilo1.start();
		//hilo1.join();
		//hilo2.join();

	}

}
