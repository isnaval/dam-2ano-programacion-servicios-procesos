public class CompartirInf1{
	public static void main (String[] args){
		Comptador compt=new Comptador(100); //cree comptador valor inicial 100
		FilA a=new FilA("FilA",compt); //cree filA (incrementa) i li passe comptador
		FilB b=new FilB("FilB",compt); //cree filB (decrementa) i li passe comptador
		a.start(); //inicie filA
		b.start(); //inicie filB		
	}
}
