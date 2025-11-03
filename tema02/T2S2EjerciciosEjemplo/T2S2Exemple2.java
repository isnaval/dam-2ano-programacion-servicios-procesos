public class T2S2Exemple2 extends Thread {
	//Extiendo la clase Thread
	// declaro dos variables la c y el fill
	private int c; 
	private int fil;

	//Constructor --------------------------------
	public T2S2Exemple2 (int fil){
		this.fil=fil;
		System.out.println("Creant Fil: " + fil);
	}
	
	//Mètode Run -----------------------------------
	public void run(){
		c=0;
		while (c<=5){
			System.out.println ("Fil:"+ fil + " C= " + c);
			c++;
		}
	}
	
	//Mètode main
	public static void main(String[] args){
	// declaro un objeto que es h con un valor nulo
	T2S2Exemple2 h=null;
	for (int i=0;i<3;i++){
		// le asigno un nuevo valor a h que es 1 2 y 3
		h=new T2S2Exemple2(i+1); 
		h.start(); 
		}		
	System.out.println("3 Fils creats...");
	} 

} 