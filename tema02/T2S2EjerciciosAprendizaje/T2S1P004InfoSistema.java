
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * T2S1P004InfoSistema.java
 * 
 * EJERCICIO 2.2: Información del sistema
 * 
 * OBJETIVO:
 *   - Leer información detallada del sistema
 * 
 * TAREAS:
 *   1. Ejecutar "cmd /c systeminfo" (Windows) o "uname -a" (Linux)
 *   2. Leer toda la salida del comando
 *   3. Mostrar la información en pantalla
 *   4. Contar cuántas líneas se leyeron
 * 
 * CONCEPTOS:
 *   - Comandos de información del sistema
 *   - Lectura de salidas largas
 *   - Contadores
 */

public class T2S1P004InfoSistema {
    public static void main (String [] args) {
        try {
            Runtime r = Runtime.getRuntime();
            String so = System.getProperty("os.name").toLowerCase();
            String comando;

            if(so.contains("win")) {
                comando = "cmd /c systeminfo";
                System.out.println("Obteniendo información del sistema...");
            } else {
                comando = "uname -a";
                System.out.println("Obteniendo información del sistema...");
            }

            Process p = r.exec(comando);
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String linea;
            int contadorLineas = 0;

            while((linea = br.readLine()) != null) {
                System.out.println(linea);
                contadorLineas++;
            }

            br.close();
            p.waitFor();


            
        } catch (IOException e) {
                    System.err.println("Error de E/S: " + e.getMessage());

        } catch (InterruptedException e) {
                        System.err.println(" Proceso interrumpido: " + e.getMessage());

        }
    }
}