
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * T2S1P003LeerComandoSimple.java
 * 
 * EJERCICIO 2.1: Leer comando simple
 * 
 * OBJETIVO:
 *   - Leer la salida de un comando del sistema
 * 
 * TAREAS:
 *   1. Ejecutar "cmd /c dir" (Windows) o "ls -l" (Linux)
 *   2. Capturar la salida con getInputStream()
 *   3. Leer línea por línea con BufferedReader
 *   4. Mostrar cada línea en la consola de Java
 *   5. Esperar a que el proceso termine
 * 
 * CONCEPTOS:
 *   - getInputStream()
 *   - BufferedReader
 *   - InputStreamReader
 *   - Lectura de flujos
 */

public class T2S1P003LeerComandoSimple {
    public static void main (String [] args) {
        try {
            Runtime r = Runtime.getRuntime();
            String so = System.getProperty("os.name").toLowerCase();
            String comando;

            if (so.contains("win")) {
                comando = "cmd /c dir";
            } else {
                comando = "ls -l";
            }

            System.out.println("Ejecuandose " + comando);
            Process p = r.exec(comando);
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String linea; 
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            br.close();
            p.waitFor();

            
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("PROCESO INTERRUMPIDO: " + e.getMessage());

        }
    }
}