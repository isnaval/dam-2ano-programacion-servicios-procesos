import java.io.IOException;


/**
 * T2S1P001AbrirAplicacion.java
 * 
 * EJERCICIO 1.1: Mi primer proceso
 * 
 * OBJETIVO:
 *   - Abrir una aplicación del sistema
 * 
 * TAREAS:
 *   1. Usar Runtime.getRuntime() para obtener el entorno de ejecución
 *   2. Ejecutar "notepad" en Windows o "gedit" en Linux
 *   3. Mostrar un mensaje confirmando que se abrió
 *   4. Esperar a que el usuario cierre la aplicación con waitFor()
 * 
 * CONCEPTOS:
 *   - Runtime
 *   - exec()
 *   - Process
 *   - waitFor()
 */

public class T2S1P001AbrirAplicacion {
    public static void main(String [] arg)   {
        try {
            // Runtime r = Runtime.getRuntime();
            // Process p = r.exec("notepad");
            // System.out.print("Aplicación abierta correctamente.");
            // p.waitFor();
            // System.out.print("Aplicacion se ha cerrado");

            ProcessBuilder pb = new ProcessBuilder("notepad");
            Process p = pb.start();
            System.out.println("Aplicación abierta correctamente.");
            p.waitFor();
            System.out.println("Aplicación se ha cerrado correcatamente");


        } catch (IOException e) {
            System.out.println("Error al abrir la aplicación: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("El proceso fue interrumpido: " + e.getMessage());

        }
    }
}
