class FilA extends Thread{
	private Comptador comptador;
	
	public FilA(String n,Comptador c){
		setName(n);
		comptador=c;
	}
	
	public void run(){
		for (int j=0;j<300;j++) {
			comptador.incrementa();//incrementa el comptador
			try {
				sleep(100);
			}catch (InterruptedException e) {}
		}
		System.out.println( getName() + " comptador val " + comptador.getValor());
	}
}//FI FilA
