
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * T2S1P010EnviarDatos.java
 * 
 * EJERCICIO 5.1: Enviar datos al proceso
 * 
 * OBJETIVO:
 *   - Escribir datos en la entrada del proceso
 * 
 * TAREAS:
 *   1. Ejecutar el comando "cmd /c date"
 *   2. Obtener el flujo de salida del proceso con getOutputStream()
 *   3. Crear un PrintWriter con ese flujo
 *   4. Enviar una fecha (ejemplo: "20-10-2025")
 *   5. Hacer flush() para enviar los datos
 *   6. Leer y mostrar la respuesta del proceso
 * 
 * CONCEPTOS:
 *   - getOutputStream()
 *   - PrintWriter para escribir al proceso
 *   - flush()
 *   - Entrada de procesos
 */

public class T2S1P010EnviarDatos {
   
    public static void main (String [] args) {
        try {
            Runtime r = Runtime.getRuntime();
            Process p = new ProcessBuilder ("cmd", "/c", "date").start();
            OutputStream os = p.getOutputStream();
            PrintWriter pw = new PrintWriter(os);

            Date hoy = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            String fechaFormateada = formato.format(hoy);
            
            pw.println(fechaFormateada);
            pw.flush();

            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"CP850"));
            // utilizamos CP850 - para leer todos los caracteres

            String linea; 
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            
            pw.close();
            br.close();
            int codigo = p.waitFor();
            System.out.println("\nEl proceso finalizó con código: " + codigo);

            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } 
    }
}
