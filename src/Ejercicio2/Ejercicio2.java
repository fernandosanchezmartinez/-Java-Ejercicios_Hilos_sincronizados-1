package Ejercicio2;

import java.util.concurrent.Semaphore;

class puesto extends Thread {
	private int identif;
	private Semaphore semaforo;
	private int cochesPorPuesto, sobranteCoches;

	public int getIdentif() {
		return identif;
	}

	public void setIdentif(int identif) {
		this.identif = identif;
	}

	public puesto(int identif, Semaphore semaforo, int cochesPorPuesto,
			int sobranteCoches) {
		this.identif = identif;
		this.semaforo = semaforo;
		this.cochesPorPuesto = cochesPorPuesto;
		this.sobranteCoches = sobranteCoches;

	}

	public void run() {
		// System.out.println("hola"+ cochesPorPuesto);
		int aleatorio = (int) Math.random() * 1000;
		for (int i = 1; i < cochesPorPuesto + 1; i++) {
			try {
				semaforo.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vehiculo veh = new vehiculo(i + aleatorio, this.identif);
			veh.start();
			semaforo.release();
		}
	}
}

class vehiculo extends Thread {
	private int identif;
	private int puestAtendio;

	public int getIdentif() {
		return identif;
	}

	public void setIdentif(int identif) {
		this.identif = identif;

	}

	public vehiculo(int identif, int puestAtendio) {
		this.identif = identif;
		this.puestAtendio = puestAtendio;
	}

	public void run() {
		System.out.println("El puesto " + puestAtendio
				+ " atendio al vehiculo " + identif);
	}
}

public class Ejercicio2 {

	public static void main(String[] args) {
		int vehRandom = (int) (Math.random() * 30) + 20;
		int pueRandom = (int) (Math.random() * 4) + 1;
		System.out.println(vehRandom + " Vehículos serán atendidos por "
				+ pueRandom + " puestos.");
		int cochesPorPuesto = vehRandom / pueRandom;
		int sobranteCoches = vehRandom % pueRandom;
		for (int i = 0; i < pueRandom; i++) {
			Semaphore semaforo = new Semaphore(1);
			puesto puesto = new puesto(i, semaforo, cochesPorPuesto,
					sobranteCoches);
			puesto.start();
		}
	}
}
