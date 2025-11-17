
public class Cinta {
    private int kilos = 0;

    public synchronized void cargar(int kg, String robot) {
        kilos += kg; 
        System.out.println("[" + robot + "] Carga " + kg + "kg. Total: " + kilos + "kg");
        notifyAll();
    }

    public synchronized void descargar(int kg, String robot) {
        while (kilos < kg) {
            try {
                System.out.println("[" + robot + "] Necesita " + kg + "kg, solo hay " + kilos + "kg. ESPERA...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        kilos -= kg;
        System.out.println("[" + robot + "] Descarga " + kg + "kg. Total: " + kilos + "kg");
        notifyAll();
    }
}