package T2S4P8ComptePLANTILLA;

class Compte {

	/**
	 * 1. declaro la variable saldo
	 * 2. declaro el construcctor con una variable que es el saldo
	 * 3. declaro el metodo get saldo - lo comleto
	 * 4. declaro el metodo restar para cuando saco algun dinero del saldo
	 * 5. declaro un metodo para retirar saldo si es que se puede
	 * 	- en este metodo utilizo una variable nueva que es cant = la cantidad de dinero que quiero sacar del saldo
	 */

	private int saldo;

	Compte(int s) {
		saldo = s; 
	}

	synchronized  int getSaldo() { // synchronized  no ponerlo me daba error
		return saldo;

	}

	synchronized void restar(int cantidad) { // synchronized  no ponerlo me daba error
		saldo = saldo - cantidad;

	}

	void RetirarDiners(int cant, String nom) {
		if (getSaldo() >= cant) {
			System.out.println(nom + " va a retirar saldo (saldo actual es: " + getSaldo() + ")");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
			}

			restar(cant);
			System.out.println(nom + " retira => : " + cant + ". Saldo Actual: " + getSaldo() + "");
		} else {
			System.out.println(nom + " No pot retirar diners no hi ha saldo. Saldo Actual: " + getSaldo() + "");
		}

	}

}
