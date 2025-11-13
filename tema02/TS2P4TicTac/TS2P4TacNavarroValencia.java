public class TS2P4TacNavarroValencia extends  Thread {
    
    public TS2P4TacNavarroValencia() {
        this.setName("TAC");
    }

    @Override
    public void run() {
        while (true) { 
            System.out.println("TAC");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread TAC interrumpido");
                break;
            }
        }
    }
    
}
