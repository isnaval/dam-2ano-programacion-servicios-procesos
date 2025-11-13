package tema02.TS2P4TicTac;
public class T2S2P4TicTacNavarroValencia extends Thread {

    public static void main(String[] args) {
        System.out.println("=== Iniciando práctica de Tic-Tac");

        // instancio a las dos clases de TIC y de TAC

        TS2P4TacNavarroValencia threadTac = new TS2P4TacNavarroValencia();
        TS2P4TicNavarroValencia threadTic = new TS2P4TicNavarroValencia();

        threadTac.start();
        threadTic.start();

        System.out.println("Main: Threads iniciados.\n");
        System.out.println("Salida separada: TICTACTICTAC...\n");
        System.out.println("Pulsar Control + C para finalizar\n");




    }
    

}
