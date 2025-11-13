public class Cliente extends Thread {
    private Barberia barberia;
    private String nombre;
    
    public Cliente(String nombre, Barberia b) {
        this.nombre = nombre;
        this.barberia = b;
    }
    
    public void run() {
        barberia.entrar_cliente(nombre);
        
        try {
            sleep(2000);
        } catch (InterruptedException e) {
        }
        
        barberia.salir_cliente(nombre);
    }
}