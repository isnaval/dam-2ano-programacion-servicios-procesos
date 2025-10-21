import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;



/**
 * T2S1P011ComunicacionBi.java
 * 
 * EJERCICIO 5.2: Comunicación bidireccional
 * 
 * OBJETIVO:
 *   - Enviar y recibir datos del proceso
 * 
 * TAREAS:
 *   1. Ejecutar "java T2S1P011Receptor"
 *   2. Preparar un BufferedReader para leer (getInputStream)
 *   3. Preparar un PrintWriter para escribir (getOutputStream)
 *   4. Leer el mensaje que pide el proceso
 *   5. Enviarle la respuesta "Hola món"
 *   6. Leer su respuesta final
 *   7. Cerrar todos los recursos
 * 
 * CONCEPTOS:
 *   - Comunicación bidireccional
 *   - Lectura y escritura simultáneas
 *   - Flujos de entrada y salida
 * 
 * NOTA: Debes crear primero T2S1P011Receptor.java
 */

 public class T2S1P011ComunicacionBi {
    public static void main(String[] args) {
        
        try {
            Process p = new ProcessBuilder("java", "T2S1P011Receptor").start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            PrintWriter pw = new PrintWriter(p.getOutputStream());
            String linea = br.readLine();
            System.out.println("Proceso receptor dice: " + linea);
            pw.println("Hola mundo");
            pw.flush();

            while((linea = br.readLine()) != null) {
                System.out.println("Proceso receptor dice: " + linea);
            }
            
            pw.close();
            br.close();

            int codigo = p.waitFor();
            System.out.println("\nEl proceso receptor termino con el cógido "+ codigo );
            
        } catch (IOException  | InterruptedException e) {
            e.printStackTrace();
        }

    }

 }

