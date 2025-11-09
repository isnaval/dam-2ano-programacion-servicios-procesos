class FilA extends Thread{
	private Comptador comptador;
	
	public FilA(String n,Comptador c){
		setName(n);
		comptador=c;
	}
	
	public void run(){
		/* Per a que s'execute abans FilB i produisca una eixida no esperada
			try{
				this.sleep(2000);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		*/
		synchronized(comptador){
			for (int j=0;j<300;j++)
				comptador.incrementa();//incrementa el comptador

			System.out.println( getName() + " comptador val " + comptador.getValor());
		}
	}
}//FI FilA
