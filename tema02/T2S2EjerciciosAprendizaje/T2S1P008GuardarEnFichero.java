import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * T2S1P008GuardarEnFichero.java
 * 
 * EJERCICIO 4.1: Guardar en fichero
 * 
 * OBJETIVO:
 *   - Redirigir la salida del proceso a un archivo
 * 
 * TAREAS:
 *   1. Ejecutar "cmd /c dir" (Windows) o "ls -l" (Linux)
 *   2. Leer la salida del comando
 *   3. Crear un archivo "salida.txt" con FileOutputStream
 *   4. Escribir cada línea en el archivo con PrintWriter
 *   5. También mostrar la salida en pantalla
 *   6. Cerrar todos los recursos
 * 
 * CONCEPTOS:
 *   - FileOutputStream
 *   - PrintWriter
 *   - Redirección de salida
 *   - Gestión de recursos
 */

public class T2S1P008GuardarEnFichero {
    public static void main(String [] args ){

        try {
             Runtime r = Runtime.getRuntime();
        String so = System.getProperty("os.name").toLowerCase();
        String comando;
        String nombreArchivo = "../salida.txt";

        if (so.contains("win")) {
            comando = "cmd /c dir";
        } else {
            comando = "ls -l";
        }

        System.out.println("Ejecutandose comando: " + comando);

        Process p = r.exec(comando);
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

        FileOutputStream fos = new FileOutputStream(nombreArchivo);

        PrintWriter pw = new PrintWriter(fos);
        String linea; 
        int contadorLineas = 0;

        while((linea = br.readLine()) != null) {
            pw.println(linea);
            System.out.println(linea);
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