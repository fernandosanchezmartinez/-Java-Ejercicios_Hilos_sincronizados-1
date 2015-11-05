package solAlternativa2;

import java.util.concurrent.Semaphore;

class Puerta {
	public static boolean CerrojoA;
	public static boolean CerrojoB;
	public static int contador;
}

class LlaveA extends Thread {
	
	Semaphore SemaforoLlaveA;// se crea un variable propia tipo Semaphore

	public LlaveA(Semaphore SemaforoLlaveA) {// en el constructor se pasa ese
											// semáforo por parámetro
		this.SemaforoLlaveA = SemaforoLlaveA;
	}
	
	public void run() {
		/*while (!Puerta.CerrojoB)
			;*/
		try {
			SemaforoLlaveA.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Puerta.CerrojoA = true;
		System.out.println("LlaveA terminando");
		
		SemaforoLlaveA.release();
		
	}
}

class LlaveB extends Thread {
	
	Semaphore SemaforoLlaveB;// se crea un variable propia tipo Semaphore

	public LlaveB(Semaphore SemaforoLlaveB) {// en el constructor se pasa ese
											// semáforo por parámetro
		this.SemaforoLlaveB = SemaforoLlaveB;
	}
	public void run() {
		/*while (!Puerta.CerrojoA)
			;*/
		
		try {
			SemaforoLlaveB.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Puerta.CerrojoB = true;
		System.out.println("LlaveB terminando");
		
		SemaforoLlaveB.release();
	}
	
}

public class SolAlternativa2 {
	
	static Semaphore Semaforo = new Semaphore(1);
	
	public static void main(String[] args) throws InterruptedException {
		Puerta.CerrojoA = false;
		Puerta.CerrojoB = false;
		Thread a = new LlaveA(Semaforo);
		Thread b = new LlaveB(Semaforo);
		a.start();
		b.start();
		System.out.println("Comienzo del hilo principal");
		a.join();
		b.join();
		System.out.println("Fin del hilo principal");
	}
}
