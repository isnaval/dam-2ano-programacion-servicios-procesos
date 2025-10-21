import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * T2S1P011Receptor.java
 * 
 * PROGRAMA AUXILIAR para T2S1P011ComunicacionBi
 * 
 * OBJETIVO:
 *   - Ser llamado por otro programa Java y comunicarse con él
 * 
 * TAREAS:
 *   1. Crear un Scanner para leer de System.in
 *   2. Mostrar el mensaje "Introduce un mensaje:"
 *   3. Leer una línea de entrada con nextLine()
 *   4. Responder con "He recibido: " + el mensaje
 *   5. Cerrar el Scanner
 * 
 * CONCEPTOS:
 *   - Scanner
 *   - System.in
 *   - Comunicación entre procesos Java
 */

 public class T2S1P011Receptor {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(System.out, true);

            //1
            pw.println("Receptor: Introduce un mensaje por favor");

            //2
            String recibido = br.readLine();
            pw.println("Receptor: He recibido -> " + recibido);

            //3
            pw.println("Receptor: Comunicación finalizada. Adiós");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }

//  Scanner scanner = new Scanner(Sytem.in);
//  System.out.println("Escribe una frase: ");
//  String frase = scanner.System.in;
//  System.out.println("Frase: " + frase);
