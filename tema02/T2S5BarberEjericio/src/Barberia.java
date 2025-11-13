public public class Barberia {
    private int cadiras_disponibles;
    private boolean barbero_durmiendo = true;
    
    public Barberia(int num_cadiras) {
        this.cadiras_disponibles = num_cadiras;
    }
    
    public synchronized void entrar_cliente(String nombre) {
        if (cadiras_disponibles == 0) {
            System.out.println(nombre + " se va, no hay cadiras");
            return;
        }
        
        cadiras_disponibles--;
        System.out.println(nombre + " entra. Cadiras libres: " + cadiras_disponibles);
        
        if (barbero_durmiendo) {
            barbero_durmiendo = false;
            System.out.println(nombre + " despierta al barbero");
            notifyAll();
        }
        
        while (barbero_durmiendo) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }
    
    public synchronized void dormir_barbero() {
        while (cadiras_disponibles == 5) {
            System.out.println("Barbero duerme");
            barbero_durmiendo = true;
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Barbero cortando cabello");
    }
    
    public synchronized void terminar_corte() {
        cadiras_disponibles++;
        System.out.println("Barbero termina corte. Cadiras libres: " + cadiras_disponibles);
        notifyAll();
    }
    
    public synchronized void salir_cliente(String nombre) {
        System.out.println(nombre + " sale");
        notifyAll();
    }
} {
    
}
