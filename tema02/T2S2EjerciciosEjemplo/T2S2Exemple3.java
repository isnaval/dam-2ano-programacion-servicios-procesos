public class T2S2Exemple3 extends Thread {
	// inicio una clase extendiendo la clase Thread y declaro dos variables
	
	//Atributs --------------------------------
	private int c; 
	private int fil;

	//Constructor --------------------------------
	public T2S2Exemple3 (int fil){
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
	}
} 
