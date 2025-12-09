

public class Barberia {
    private int sillasLibres;
    private boolean barberoOcupado = false;
    
    public Barberia(int numSillas) {
        this.sillasLibres = numSillas;
    }
    
    public synchronized void entrar_cliente(String nombreCliente) {
        if (sillasLibres == 0) {
            System.out.println(nombreCliente + " se va, no hay sillas");
            return;
        }
        
        sillasLibres--;
        System.out.println(nombreCliente + " esperando. Sillas libres: " + sillasLibres);
        
        if (!barberoOcupado) {
            barberoOcupado = true;
            notifyAll();
        }
    }
    
    public synchronized void salir_cliente(String nombreCliente) {
        System.out.println(nombreCliente + " sale");
        notifyAll();
    }
    
    public synchronized void cortarCabello() {
        while (sillasLibres == 5) {
            try {
                System.out.println("Barbero esperando clientes");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Barbero cortando cabello");
    }
    
    public synchronized void finalizarCorte() {
        sillasLibres++;
        System.out.println("Corte terminado. Sillas libres: " + sillasLibres);
        notifyAll();
    }
}