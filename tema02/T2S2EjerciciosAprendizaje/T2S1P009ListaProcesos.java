
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * T2S1P009ListaProcesos.java
 * 
 * EJERCICIO 4.2: Lista de procesos
 * 
 * OBJETIVO:
 *   - Guardar la lista de procesos en ejecución
 * 
 * TAREAS:
 *   1. Ejecutar "tasklist /v" (Windows) o "ps -ef" (Linux)
 *   2. Leer toda la salida del comando
 *   3. Guardar el resultado en "processos.txt"
 *   4. Mostrar las primeras 10 líneas en consola
 *   5. Indicar cuántas líneas totales se guardaron
 * 
 * CONCEPTOS:
 *   - Comandos de gestión de procesos
 *   - Escritura en ficheros
 *   - Contadores y límites de visualización
 */

public class T2S1P009ListaProcesos {
    public static void main (String [] args ) {
        try {
            Runtime r = Runtime.getRuntime();
            String so = System.getProperty("os.name").toLowerCase();
            String comando; 
            String nombreArchivo = "procesos.txt";

            if(so.contains("win")) {
                comando = "tasklist /v"; 
            } else {
                comando = "ps -ef";
            }

            System.out.println("Obteniendo lista de procesos...");
            Process p = r.exec(comando);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            FileOutputStream fos = new FileOutputStream(nombreArchivo);
            PrintWriter pw = new PrintWriter(fos);

            String linea; 
            int contadorLineas = 0; 
            int LIMITE_PANTALLA = 10; 

            System.out.println("Primeras "+ LIMITE_PANTALLA  + " lineas:\n");

            while((linea = br.readLine()) != null) {
                pw.println(linea);
                if(contadorLineas < LIMITE_PANTALLA) {
                    System.out.println(linea);
                } else if (contadorLineas == LIMITE_PANTALLA) {
                    System.out.println("\n... (más líneas guardadas en el archivo)");
                }
                contadorLineas++;
            }

            br.close();
            pw.close();
            fos.close();
            p.waitFor();

            
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());

        
        } catch (InterruptedException e) {
            System.err.println("Proceso interrumpido: " + e.getMessage());          
        }
    }
}