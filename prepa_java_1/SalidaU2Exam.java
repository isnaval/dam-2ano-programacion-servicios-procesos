public class SalidaU2Exam {


    /**
     * EJERCICIO CON 4 CLASES 
     * - Clase cinta
     *      declaro variable kilos y los metodos cargar y desargar 
     * - Clase Robot A
     *      con sus atributos y consstructor y el metodo run
     * - Clase Robot B
     *      con sus atributos y consstructor y el metodo run
     * - Clase principal
     *      Genero una cinta y dos robot A/B e inicio el proceso
     *      Añado try/catch para tratamiento de posibles errores. 
     *      
     */


    static class Cinta {

        private int kilos = 0; 
        
        public synchronized void cargar (int kg, String robot) {
            kilos += kg; 
            System.out.println("[" + robot + "] Necesito " + kg + "kg, solo hay " + kilos + "kg. ESPERO...");
            notifyAll();
        }

        public synchronized void descargar(int kg, String robot) {
            while(kilos <kg ){
                System.out.println("[" + robot + "] Necesito " + kg + "kg, solo hay " + kilos + "kg. ESPERO...");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            kilos -=kg; 
            System.out.println("[" + robot + "] Descargo " + kg + "kg → Total: " + kilos + "kg");
            notifyAll();
        }

    } 

    static class RobotA extends Thread {
        private Cinta cinta; 
        private int kg; 
        private int segundos;
  
        public RobotA(Cinta cinta, int kg, int segundos){
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
                    Thread.sleep(segundos*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("[" + getName() + "] FIN");
        }
    }


    static class RobotB extends Thread {
        private Cinta  cinta; 
        private int kg;
        private int segundos;

        public RobotB(Cinta cinta, int kg, int segundos) {
            this.cinta = cinta; 
            this.kg = kg; 
            this.segundos = segundos;
           setName("Robot B");
        }

        @Override
            public void run() {
                // el peso de una malla de es 5 kg -- y siempre sacara mallas de a 5 kilos
                 for (int i = 0; i < 10; i++) {
                    cinta.descargar(kg, getName());
                    try {
                        Thread.sleep(segundos * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("[" + getName() + "] FIN");
            }
    }




    public static void main(String[] args) {
        System.out.println("====Cinta transportadora");
        Cinta cinta = new Cinta();
        RobotA robotA = new RobotA(cinta, 15, 5);
        RobotB robotB = new RobotB(cinta, 10, 3);

        robotA.start();
        robotB.start();

        try {
            robotA.join();
            robotB.join();      
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("\n=== FIN Pau!===");
    }

}