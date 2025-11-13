package tema02.T2S5P9Compte2.bin;
import java.util.Random;

public class Persona extends Thread{
    private Cuenta cuenta; 
    private String nombre; 

    public Persona(String nombre, Cuenta c) {
        this.nombre = nombre; 
        this.cuenta = c; 
    }

    public void run() {
        Random rnd = new Random();
        for(int i =0; i <2; i++) {
            int dinero = (int)(rnd.nextDouble()*501.0);
            cuenta.ingeso(dinero, nombre);

            dinero = (int)(rnd.nextDouble()*501.0);
            cuenta.reintegrar(dinero, nombre);
        }
        
    }

    
}
