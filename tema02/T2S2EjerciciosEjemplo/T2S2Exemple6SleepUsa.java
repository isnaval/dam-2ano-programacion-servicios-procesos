public class T2S2Exemple6SleepUsa {
  public static void main(String[] args){
	  T2S2Exemple4Alive h=null;
	  h=new T2S2Exemple4Alive(1); //cree fil
	  h.start(); //iniciar fil
	  
	  try {
		  //Pausa 4 segons
		  h.sleep(4000);
		  } catch (InterruptedException e) {
                          }
	} //fi main
} //fi classe
