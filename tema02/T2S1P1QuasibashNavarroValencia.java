import java.io.*;

public class T2S1P1QuasibashNavarroValencia {

    private static final int EXIT_CODE_ERROR = 1; 

    /**
     * FUNCION PRINCIPAL
     * cuando compilo y ejecuto el codigo se deje ejecutar con la función de window que interese
     * P.EJ: java T2S1P1QuasibashNavarroValencia dir ó ping ó ipconfig ...
     * @param args
     */

    public static void main(String[] args) {
      
        if (args.length < 1) {
            System.out.println("Uso: java T2S1P1QuasibashNavarroValencia <comando>");
            System.exit(EXIT_CODE_ERROR);
        }

        String comando = String.join(" ", args);
        ejecutarComando(comando);

    }

    /**
     * FUNCION PARA ejecutarComando con el argumento comando
     * he usado ProcessBuilder que es mas aplicado actualmente
     */
    
private static void ejecutarComando(String comando) {
    try {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", comando);
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        
        Process proceso = pb.start();
        int exitCode = proceso.waitFor();
        
        System.out.println("Código de salida: " + exitCode);
        
    } catch (IOException | InterruptedException e) {
        System.err.println("Error: " + e.getMessage());
    }
}
    
} 

