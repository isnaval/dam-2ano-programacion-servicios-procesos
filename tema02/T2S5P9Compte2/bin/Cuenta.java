package tema02.T2S5P9Compte2.bin;
public class Cuenta {
    private int saldo;
    private int max_saldo; 

    public Cuenta(int saldo_inicial, int maximo) {
        this.saldo = saldo_inicial; 
        this.max_saldo = maximo;
    }

    public synchronized  void ingeso(int cantidad, String nombrePersona) {
        while(saldo + cantidad > max_saldo) {
            System.out.println(nombrePersona + " espera para ingresas. Salgo: " + saldo );
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        saldo += cantidad; 
        System.out.println("Ingreso: ("+ nombrePersona + "): " + cantidad + " - Saldo: " + saldo);
        notifyAll();
    }

    public synchronized void reintegrar(int cantidad, String nombrepersona){
        while( saldo < cantidad) {
            System.out.println(nombrepersona + " esperar retirar. Saldo: " + saldo);
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        saldo -= cantidad;
        System.out.println("Reintegrament (" + nombrepersona + "): " + cantidad + " - Saldo: " + saldo);
    }
    
}