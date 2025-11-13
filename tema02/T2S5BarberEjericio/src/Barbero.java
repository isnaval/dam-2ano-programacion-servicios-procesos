public class Barbero extends Thread {
    private Barberia barberia;
    
    public Barbero(Barberia b) {
        this.barberia = b;
    }
    
    public void run() {
        while (true) {
            barberia.dormir_barbero();
            
            try {
                sleep(2000);
            } catch (InterruptedException e) {
            }
            
            barberia.terminar_corte();
        }
    }
}