import java.lang.*;
public class T2S2Exemple5toStringUsa {
  public static void main(String[] args){
		T2S2Exemple4Alive h=null;
		h=new T2S2Exemple4Alive(1); //cree fil

		h.start(); //iniciar fil
    	
		//Comprove estat després de cridar a start
		System.out.println("Informació id fil:" + h.getId());
	    System.out.println("Informació fil = " + h.toString());
	} //fi main
} //fi clase
