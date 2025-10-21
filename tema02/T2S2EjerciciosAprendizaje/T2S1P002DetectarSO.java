import java.io.IOException;

/**
 * T2S1P002DetectarSO.java
 * 
 * EJERCICIO 1.2: Detectar el sistema operativo
 * 
 * OBJETIVO:
 *   - Ejecutar comandos diferentes según el sistema operativo
 * 
 * TAREAS:
 *   1. Detectar el SO con System.getProperty("os.name")
 *   2. Mostrar qué SO se detectó
 *   3. Si es Windows, ejecutar "notepad"
 *   4. Si es Linux, ejecutar "gedit"
 *   5. Manejar el caso de SO no reconocido
 * 
 * CONCEPTOS:
 *   - System.getProperty()0
 *   - Condicionales para SO
 *   - Adaptabilidad multiplataforma
 */

public class T2S1P002DetectarSO {
    public static void main(String [] args) throws IOException {
        try {
            Runtime r = Runtime.getRuntime();
            String so = System.getProperty("os.name").toLowerCase();
            System.out.println("SO detectado: " + so);

            Process p; 

            if(so.contains("win")) {
                System.out.println("Abriendo Notepad...");
                p = r.exec("notepad");
            } else if (so.contains("nux")) {
                System.out.println("Abriendo Gedit");
                p = r.exec("gedit");
            } else {
                System.out.println("SO no reconocido");
                return;
            }

            System.out.println("Aplicacion abierta");
            p.waitFor();
            System.out.println("Aplicacion cerrada");

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
                    } catch (InterruptedException e) {
            System.out.println("INTERRUMPIDO: " + e.getMessage());
        }
    }
}
