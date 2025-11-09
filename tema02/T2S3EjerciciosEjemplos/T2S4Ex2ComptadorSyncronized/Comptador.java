class Comptador {

	private int c=0; //atribut comptador

	//constructor
	Comptador (int c) {
		this.c=c;
	}

	public void incrementa(){
		c=c+1;	
		}

	public void decrementa(){	
		c=c-1;	
		}
	public int getValor(){
		return c;	
		}
	
}//COMPTADOR
