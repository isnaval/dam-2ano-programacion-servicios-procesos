class FilB extends Thread{
	private Comptador comptador;
	
	public FilB(String n,Comptador c){
		setName(n);
		comptador=c;
	}
	
	public void run(){
		synchronized(comptador){
			for (int j=0;j<300;j++)
				comptador.decrementa();//decrementa el comptador

			System.out.println(getName() + " comptador val " + comptador.getValor());
		}
	}
}//FI FilB
