public class T2S2P4TicTacNavarroValencia extends Thread {
    
    // TODO: Atributos
    private int iteraciones;

    // TODO: Constructor
    public T2S2P4TicTacNavarroValencia(int iteraciones) {
        this.iteraciones = iteraciones;
    }

    @Override
    public void run() {
        // TODO: Implementar lógica del hilo
        for (int i = 0; i < iteraciones; i++) {
            System.out.println("Tic Tac: " + i);
            try {
                Thread.sleep(1000); // 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // TODO: Crear hilo
        T2S2P4TicTacNavarroValencia hilo = new T2S2P4TicTacNavarroValencia(5);
        
        // TODO: Iniciar hilo
        hilo.start();
        
        System.out.println("Hilo TicTac iniciado");
    }
}
