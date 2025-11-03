public class T2S2Exemple7 extends Thread {
	
	public void run(){
		System.out.println ("Dins del Fil: " + this.getName() + " Prioritat: " + this.getPriority() + " ID: " + this.getId());
	}

	public static void main (String[] args){
	
	 T2S2Exemple7 h=null;
	
	for (int i=0;i<3; i++){
			h= new T2S2Exemple7(); 

			h.setName ("FIL " + i); 
			h.setPriority (i+1); 
			
			h.start(); 

			System.out.println ("Informació del " + h.getName() + ": " + h.toString());			
		}
	System.out.println ("3 Fils creats...");

	}//fi main

}//fi classe
