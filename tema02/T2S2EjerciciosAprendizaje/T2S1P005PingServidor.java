
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * T2S1P005PingServidor.java
 * 
 * EJERCICIO 2.3: Ping a un servidor
 * 
 * OBJETIVO:
 *   - Ejecutar ping y mostrar el resultado
 * 
 * TAREAS:
 *   1. Ejecutar "ping -n 4 google.com" (Windows) o "ping -c 4 google.com" (Linux)
 *   2. Leer y mostrar la salida del ping
 *   3. Obtener el código de salida del proceso
 *   4. Indicar si el ping fue exitoso (código 0) o falló
 * 
 * CONCEPTOS:
 *   - Comandos con parámetros
 *   - Códigos de salida
 *   - Validación de resultados
 */

public class T2S1P005PingServidor {
    public static void main(String[] args) {
        try {
            Runtime r = Runtime.getRuntime();
            String so = System.getProperty("os.name").toLowerCase();
            String comando; 

            if(so.contains("win")) {
                comando = "ping -n 8 google.com";
            } else {
                comando = "ping -c 4 google.com";
            }

            System.out.println("Haciendo ping a google.com... ");

            Process p = r.exec(comando);
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String linea; 
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            br.close();
            int codigoSalida = p.waitFor();
            System.out.println("Código de salida: " + codigoSalida);

            if (codigoSalida == 0) {
                System.out.println("Ping exitoso -- servidor responde");
            } else {
                System.out.println("Ping fallido");
            }
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());

        } catch (InterruptedException e) {
            System.err.println("Proceso interrumpido: " + e.getMessage());

        }
    }
}