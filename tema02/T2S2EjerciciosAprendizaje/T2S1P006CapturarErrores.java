
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * T2S1P006CapturarErrores.java
 * 
 * EJERCICIO 3.1: Capturar errores
 * 
 * OBJETIVO:
 *   - Distinguir entre salida normal y errores
 * 
 * TAREAS:
 *   1. Ejecutar un comando INCORRECTO: "cmd /c dirr" (con doble 'r')
 *   2. Capturar la salida normal con getInputStream()
 *   3. Capturar los errores con getErrorStream()
 *   4. Mostrar ambas salidas por separado
 *   5. Verificar el código de salida para saber si hubo error
 * 
 * CONCEPTOS:
 *   - getErrorStream()
 *   - Diferencia entre salida normal y errores
 *   - Códigos de salida
 */

public class T2S1P006CapturarErrores {
    public static void main(String[] args) {
        try {

            //(1)
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("cmd /c dirr");

            //(2)
            InputStream salidaNormal = p.getInputStream();
            InputStreamReader isrNormal = new InputStreamReader(salidaNormal);
            BufferedReader brSalida = new BufferedReader(isrNormal);

            System.out.println("Salida Normal: ");
            String linea; 
            boolean huboSalida = false; 

            while((linea = brSalida.readLine()) != null) {
                System.out.println(" "+ linea);
                huboSalida = true;
            }

            if (!huboSalida) {
                System.out.println(" (vacia)");
            }

            //(3)
            InputStream salidaError = p.getErrorStream();
            InputStreamReader isrError = new InputStreamReader(salidaError);
            BufferedReader brError = new BufferedReader(isrError);

            System.out.println("Salida error: ");
            boolean huboError = false;

            while((linea = brError.readLine()) != null) {
                System.err.println(" "+ linea);
                huboError = true; 
            }

            if(!huboError) {
                System.out.println(" (vacia)");
            }

            brSalida.close();
            brError.close();
            int codigoSalida = p.waitFor();

            System.out.println("Codigo de salida: " + codigoSalida);
            if(codigoSalida == 0) {
                System.out.println("Comando ejecutado correctamente");
            } else {
                System.out.println("Comando fallado");
            }
            
        } catch (IOException e) {
                        System.err.println("Error de E/S: " + e.getMessage());

        } catch (InterruptedException e) {
                    System.err.println("Proceso interrumpido: " + e.getMessage());
        }

    }
}