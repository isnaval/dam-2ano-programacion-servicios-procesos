
public class Principal {
    public static void main(String[] args) {
        Barberia barberia = new Barberia(5);
        
        Barbero barbero = new Barbero(barberia);
        barbero.start();
        
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            
            Cliente cliente = new Cliente("Cliente" + i, barberia);
            cliente.start();
        }
    }
    
}
