public class T2S2Exemple4Alive extends Thread {

	// declaro una clase extiendo Thread
	// declaro dos variables como atributos de la clase
	// genero un constructor con el valor de fil donde fil = fil
	// utilizo el metodo run para asignar los valores al hijo del 0 al 5
	
	//Atributs --------------------------------
	private int c; //comptador fil
	private int fil;

	//Constructor --------------------------------
	public T2S2Exemple4Alive (int fil){
		this.fil=fil;
		System.out.println("Creant Fil: " + fil);
	}//fi constructor
	
	//Mètode Run -----------------------------------
	public void run(){
		c=0;
		while (c<=5){
			System.out.println ("Fil:"+ fil + " C= " + c);
			c++;
		}
	}//fi run
} //fi classe
