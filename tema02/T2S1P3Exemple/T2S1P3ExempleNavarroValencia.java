package tema02.T2S1P3Exemple;
import java.io.*;
/**
     * FUNCION PRINCIPAL
     * Lee un mensaje desde la entrada estándar y lo imprime
     * Este programa es llamado por T2S1P3CridaLecturaNavarroValencia
     * @param args
 */

public class T2S1P3ExempleNavarroValencia {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Recibido: " + br.readLine());
    }
}