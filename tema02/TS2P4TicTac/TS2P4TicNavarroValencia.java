package tema02.TS2P4TicTac;
public class TS2P4TicNavarroValencia extends Thread {

    public TS2P4TicNavarroValencia() {
        this.setName("TIC");
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("TIC");

            try {
                Thread.sleep(1009);
            }catch (InterruptedException e) {
                System.out.println("Thread TIC interrumpido");
                break;
            }
        }
    }   
}
