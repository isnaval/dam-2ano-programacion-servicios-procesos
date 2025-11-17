
public class RobotA extends Thread {
    private Cinta cinta; 
    private int kg; 
    private int segundos; 

    public RobotA(Cinta cinta, int kg, int segundos) {
        this.cinta = cinta;
        this.kg = kg;
        this.segundos = segundos;
        setName("Robot A");
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            cinta.cargar(kg, getName());
            try {
                Thread.sleep(segundos * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[" + getName() + "] FIN");
    }
    
}
