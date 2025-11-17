
public class CintaTransportadora {
    public static void main(String[] args) {
        System.out.println("=== CINTA TRANSPORTADORA ===\n");

        Cinta cinta = new Cinta();
        RobotA robotA = new RobotA(cinta, 15, 1);
        RobotB robotB = new RobotB(cinta, 10, 2);

        robotA.start();
        robotB.start();

        try {
            robotA.join();
            robotB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== FIN ===");
    }
}