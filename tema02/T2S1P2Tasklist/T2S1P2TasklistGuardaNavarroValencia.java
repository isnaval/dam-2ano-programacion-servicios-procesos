import java.io.*;

public class T2S1P2TasklistGuardaNavarroValencia {

    private static final int EXIT_CODE_ERROR = 1;

    /**
     * FUNCION PRINCIPAL
     * Ejecuta un comando de Windows y guarda la salida en un archivo
     * P.EJ: java T2S1P2TasklistGuardaNavarroValencia tasklist procesos.txt
     * @param args
     */

    public static void main(String[] args) {
        
        if (args.length < 2) {
            System.out.println("Uso: java T2S1P2TasklistGuardaNavarroValencia <comando> <nombreArchivo>");
            System.exit(EXIT_CODE_ERROR);
        }

        String comando = args[0];
        String nombreArchivo = args[1];
        
        ejecutarYGuardar(comando, nombreArchivo);
    }

    /**
     * FUNCION PARA ejecutar comando y guardar su salida en archivo
     * Usa ProcessBuilder con cmd.exe para ejecutar comandos de Windows
     */
    private static void ejecutarYGuardar(String comando, String nombreArchivo) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", comando);
            Process proceso = pb.start();
            
            // Abrir archivo 
            FileOutputStream fos = new FileOutputStream(nombreArchivo);
            PrintWriter pw = new PrintWriter(fos);
            
            // Leer salida 
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            
            while ((linea = br.readLine()) != null) {
                System.out.println("Escribiendo: " + linea);
                pw.println(linea);
            }
            
            // Cerrar recursos
            br.close();
            pw.close();
            
            int exitCode = proceso.waitFor();
            System.out.println("Archivo " + nombreArchivo + " creado exitosamente");
            System.out.println("Código de salida: " + exitCode);
            
        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}