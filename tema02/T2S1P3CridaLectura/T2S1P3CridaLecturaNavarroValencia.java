import java.io.*;

public class T2S1P3CridaLecturaNavarroValencia {

    private static final int EXIT_CODE_ERROR = 1;

    /**
     * FUNCION PRINCIPAL
     * Envía un mensaje a otro programa Java y lee su respuesta
     * P.EJ: java T2S1P3CridaLecturaNavarroValencia "Hola Mundo"
     * @param args
     */
    public static void main(String[] args) {
        
        if (args.length < 1) {
            System.out.println("Uso: java T2S1P3CridaLecturaNavarroValencia <mensaje>");
            System.exit(EXIT_CODE_ERROR);
        }
        
        String mensaje = args[0];
        enviarYLeer(mensaje);
    }

    /**
     * FUNCION PARA enviar mensaje a otro proceso y leer su respuesta
     * Usa ProcessBuilder para ejecutar otro programa Java
     */
    private static void enviarYLeer(String mensaje) {
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "T2S1P3ExempleNavarroValencia");
            Process proceso = pb.start();

            // envio
            OutputStream os = proceso.getOutputStream();
            os.write((mensaje + "\n").getBytes());
            os.flush();
            os.close(); 
            
            // leo
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("Respuesta: " + linea);
            }
            br.close();
            
            // leo si hay errores
            BufferedReader brErr = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
            String lineaErr;
            while ((lineaErr = brErr.readLine()) != null) {
                System.err.println("ERROR> " + lineaErr);
            }
            brErr.close();

            int exitCode = proceso.waitFor();
            System.out.println("Código de salida: " + exitCode);
            
        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}