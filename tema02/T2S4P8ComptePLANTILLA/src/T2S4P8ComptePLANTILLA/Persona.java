package T2S4P8ComptePLANTILLA;

/*
 * 1. genero un objeto "Persona" que extiende Thread para poder...
 * 2. declaro la vaible c que es del tipo Compte
 * 2. declaro la variable nombre
 * 3. declaro el constructor de la persona que extiende los atributos de Compte
 * 4. ya que estamos usando Thread, utilizamos el emtodo run para iniciar una iteracion de 4 ciclos
 * 	- en casa ciclo se retira una cantidad de dinero con un nombre asignado
 */
class Persona extends Thread {
	private Compte c; 
	String nombre;
	public Persona(String n, Compte c) {
		super(n);
		this.c = c;
	}
	// run
	public void run() {
		for (int x = 1; x <= 4; x++) {
			c.RetirarDiners(10, getName());
		}
	}
}