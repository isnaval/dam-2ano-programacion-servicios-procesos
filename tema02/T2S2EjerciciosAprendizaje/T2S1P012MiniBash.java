import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * T2S1P012MiniBash.java
 * 
 * EJERCICIO 6: Mini terminal (QuasiBash)
 * 
 * OBJETIVO:
 *   - Crear una mini terminal que ejecute comandos
 *   - Integrar TODO lo aprendido
 * 
 * TAREAS:
 *   1. Crear un bucle infinito que muestre un prompt "minibash^> "
 *   2. Leer comandos del usuario desde teclado
 *   3. Si el usuario escribe "exit", salir del programa
 *   4. Para cada comando:
 *      a. Ejecutarlo con Runtime.exec()
 *      b. Capturar salida normal (getInputStream)
 *      c. Capturar errores (getErrorStream)
 *      d. Mostrar ambas salidas
 *      e. Verificar código de salida
 *   5. Detectar el SO para usar "cmd /c" en Windows
 * 
 * CONCEPTOS:
 *   - Bucles interactivos
 *   - Entrada del usuario
 *   - Ejecución dinámica de comandos
 *   - Gestión completa de procesos
 *   - Integración de todos los conceptos
 * 
 * EXTRA:
 *   - Puedes crear un método ejecutarComando(String comando)
 *     para organizar mejor el código
 */

public class T2S1P012MiniBash {
    public static void main(String[] args) {
        
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        String comando; 
        System.out.println("=== Mini Bash ===");
        System.out.println("Escribe comandos del sistema (o exit para salir)");
        System.out.println();

        try{
            while (true) { 
                System.out.println("minibash> ");
                comando = teclado.readLine();
                if(comando == null || comando.trim().equalsIgnoreCase("exit")){
                    System.out.println("Saliendo del Minisbash ...");
                    break;
                } 
                ejecutarComando(comando);
            }

        } catch (IOException e) {
            System.err.println("Error al leer el comando" + e.getMessage());
        }
    }

    public static void  ejecutarComando (String comando) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            Process proceso;

            if (os.contains("win")) {
                proceso = new ProcessBuilder("cmd", "/c", comando).start();
            } else {
                proceso = new ProcessBuilder("bash", "-c", comando).start();
            }

            BufferedReader salida = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            BufferedReader errores = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));

            String linea;
            while ((linea = salida.readLine()) != null) {
                System.out.println(linea);
            }
            while ((linea = errores.readLine()) != null) {
                System.err.println(linea);
            }

            int codigoSalida = proceso.waitFor();
            System.out.println("(Código de salida: " + codigoSalida + ")");
            System.out.println();

            salida.close();
            errores.close();

        } catch (IOException | InterruptedException e) {
            System.err.println("Error al ejecutar el comando: " + e.getMessage());
         

        }
    }
}