public class T2S2Exemple3Usa {
  public static void main(String[] args){
		// delcaro un objeto h con valor nuelo
		// genero una iteracion del 0 al 3 con los valores 012
		// asigno al objeto el valor de 1 y 2
		// inicio con un metodo start el objeto
		// imrpimo esl status cono un bolleano e inicio
		// vuelve a pasar lo mismo, se inicia un hilo al aleatorio 
		T2S2Exemple3 h=null;
		for (int i=0;i<3;i++){
			h=new T2S2Exemple3(i+1);
			h.start(); 
			System.out.println("status = " + h.isAlive());
		}
		System.out.println("3 Fils creats...");
	} 
} 
