package prepa_java;
public class CintaTransportadora {

    static class Cinta {
        private int kilosActuales = 0; 
        private final int capacidadMaxima = 100; 

        public synchronized void cargar(int kilos, String robot) {
            kilosActuales += kilos;
            System.out.println("[" + robot + "] Cargando " + kilos + "kg. Total en la cinta: " + kilosActuales + "kg");
            notifyAll();
        }

        public synchronized void descargar(int kilosNecesarios, int numMallas, String robot ){
            while (kilosActuales < kilosNecesarios) {
                System.out.println("[" + robot + "] Necesito " + kilosNecesarios + "kg (" + numMallas + " mallas). " + "Solo hay " + kilosActuales + "kg. ESPERANDO...");

                try {
                    wait();
                } catch ( InterruptedException e) {
                    e.printStackTrace();
                }
            }

            kilosActuales -= kilosNecesarios;
                        System.out.println("[" + robot + "] SACADAS " + numMallas + " mallas (" + kilosNecesarios + "kg). "+ "Total en cinta: " + kilosActuales + "kg");
        
            notifyAll();

        }

        public synchronized int getKilosActuales() {
            return kilosActuales;
        }   
    }

    // ROBOT A
    static class RobotA extends Thread {
        private Cinta cinta;
        private int kilosPorCarga;
        private int segundosEntreCarga;
        
        public RobotA(Cinta cinta, int kilosPorCarga, int segundosEntreCarga) {
            this.cinta = cinta;
            this.kilosPorCarga = kilosPorCarga;
            this.segundosEntreCarga = segundosEntreCarga;
            setName("Robot A (Productor)");
        }
        
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {  // 10 cargas
                cinta.cargar(kilosPorCarga, getName());
                
                try {
                    Thread.sleep(segundosEntreCarga * 1000);  // Espera entre cargas
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("[" + getName() + "] TERMINÓ DE CARGAR");
        }
    }

    //ROBOT B
    static class RobotB extends Thread {
        private Cinta cinta;
        private int numMallas;
        private int kilosNecesarios;
        private int segundosEntreLlenado;
        
        public RobotB(Cinta cinta, int numMallas, int segundosEntreLlenado) {
            this.cinta = cinta;
            this.numMallas = numMallas;
            this.kilosNecesarios = numMallas * 5;  // Cada malla = 5 kilos
            this.segundosEntreLlenado = segundosEntreLlenado;
            setName("Robot B (Consumidor)");
        }
        
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {  // 10 llenados
                cinta.descargar(kilosNecesarios, numMallas, getName());
                
                try {
                    Thread.sleep(segundosEntreLlenado * 1000);  // Espera entre llenados
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("[" + getName() + "] TERMINÓ DE RECOGER");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== EXAMEN PRÁCTICO: CINTA TRANSPORTADORA DE NARANJAS ===\n");
        
        // CONFIGURACIÓN INICIAL
        System.out.println("--- CONFIGURACIÓN INICIAL ---");
        System.out.println("Robot A: 15kg cada 5 segundos");
        System.out.println("Robot B: 2 mallas (10kg) cada 3 segundos\n");
        
        Cinta cinta = new Cinta();
        
        // Crear threads
        RobotA robotA = new RobotA(cinta, 15, 5);
        RobotB robotB = new RobotB(cinta, 2, 3);
        
        // Iniciar threads
        robotA.start();
        robotB.start();
        
        // Esperar a que terminen
        try {
            robotA.join();
            robotB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n=== SIMULACIÓN TERMINADA ===");
        System.out.println("Kilos finales en cinta: " + cinta.getKilosActuales() + "kg\n");
        
        // CONFIGURACIÓN 1
        System.out.println("\n--- CONFIGURACIÓN 1 ---");
        System.out.println("Robot A: 20kg cada 5 segundos");
        System.out.println("Robot B: 3 mallas (15kg) cada 4 segundos\n");
        
        cinta = new Cinta();
        robotA = new RobotA(cinta, 20, 5);
        robotB = new RobotB(cinta, 3, 4);
        
        robotA.start();
        robotB.start();
        
        try {
            robotA.join();
            robotB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n=== CONFIGURACIÓN 1 TERMINADA ===");
        System.out.println("Kilos finales en cinta: " + cinta.getKilosActuales() + "kg\n");
        
        // CONFIGURACIÓN 2
        System.out.println("\n--- CONFIGURACIÓN 2 ---");
        System.out.println("Robot A: 18kg cada 6 segundos");
        System.out.println("Robot B: 2 mallas (10kg) cada 4 segundos\n");
        
        cinta = new Cinta();
        robotA = new RobotA(cinta, 18, 6);
        robotB = new RobotB(cinta, 2, 4);
        
        robotA.start();
        robotB.start();
        
        try {
            robotA.join();
            robotB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n=== CONFIGURACIÓN 2 TERMINADA ===");
        System.out.println("Kilos finales en cinta: " + cinta.getKilosActuales() + "kg");
    }

}
