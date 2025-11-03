import java.lang.*;
public class T2S2Exemple4AliveUsa {

/**
 * 

 */


  public static void main(String[] args){
	  T2S2Exemple4Alive h=null;
	  h=new T2S2Exemple4Alive(1); //cree fil
	  
	  //Comprove l'estat abans de cridar a start
	  System.out.println("Abans cridada a start");
	  System.out.println("Està viu? = " + h.isAlive());
	  System.out.println("Estat:" + h.getState());
	  
	  System.out.println("Cride a start");
	  h.start(); //inicialitzar fil
	  
	  //Comprove l'estat després de cridar a start
	  System.out.println("Estat:" + h.getState());
	  System.out.println("Està viu?? = " + h.isAlive());
	  
	  //Cride a join i espere a que acabe
	  try{
		  h.join();
		  } catch (Exception ex){}
	

	  //Comprove l'estat després de finalitzar el fil
	  System.out.println("Després de finalitzar fil ");
	  System.out.println("Estat:" + h.getState());
	  System.out.println("Està viu? = " + h.isAlive());
	} //fi main
} //fi classe
