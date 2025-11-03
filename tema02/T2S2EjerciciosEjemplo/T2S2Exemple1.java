public class T2S2Exemple1 extends Thread {
	// extiendo la clase Thread
	// creo la variable de x
	private int x; 

	// genero el constructor  que me devuelve la clase x
	public T2S2Exemple1 (int x)
	{
		this.x=x;
	}
	
	// invoco la funcion run donde digo mediante una tieración que simpre se imprima 
	// dentro del fill siendo que x tiene un valor que aun no lo he pedido
		public void run(){
		for (int i=0;i<x;i++)
			System.out.println ("Dins del fil..");
		}
		
		// he usado la función main donde declaro el objeto x y donde asigno el valor de 10 a x y le 
		// lo lanzo 
		public static void main(String[] args){
		T2S2Exemple1 p=new T2S2Exemple1(10);
		p.start();
	} 

}