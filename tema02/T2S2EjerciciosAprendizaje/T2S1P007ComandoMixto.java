
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * T2S1P007ComandoMixto.java
 * 
 * EJERCICIO 3.2: Comando con salida mixta
 * 
 * OBJETIVO:
 *   - Gestionar salida normal y errores simultáneamente
 * 
 * TAREAS:
 *   1. Crear un array con varios comandos (correctos e incorrectos)
 *   2. Ejecutar cada comando en un bucle
 *   3. Para cada comando, capturar salida normal Y errores
 *   4. Mostrar ambas salidas de forma organizada
 *   5. Indicar si cada comando tuvo éxito o falló
 * 
 * CONCEPTOS:
 *   - Ejecución múltiple de comandos
 *   - Gestión simultánea de flujos
 *   - Organización de salida
 */

public class T2S1P007ComandoMixto {
    public static void main (String [] args) {
        String [] comandos = { "cmd /c dir","cmd /c dirr","cmd /c echo Hola","cmd /c pinggg","cmd /c date /t"};

        for (int i = 0; i < comandos.length; i++){
            System.out.println("Ejecutando: " + comandos[i]);
            ejecutarComando(comandos[i]);
        }
    }

    public static void ejecutarComando(String comando ){

        try {
        
                //1
                Runtime r = Runtime.getRuntime();
                Process p = r.exec(comando);
                BufferedReader brSalida = new BufferedReader(new InputStreamReader(p.getInputStream()));
                System.out.println("\n Salida: ");
                
                //2
                String linea; 
                boolean huboSalida = false; 
                int contadorSalida = 0;
                
                while((linea = brSalida.readLine()) != null) {
                    if(contadorSalida <10){
                        System.out.println(" "+ linea);
                    } else if (contadorSalida == 10) {
                        System.out.println(" ...(más línesa omitidas)");
                    }
                    huboSalida = true;
                    contadorSalida++;
                }

                if(!huboSalida) {
                    System.out.println("   (vacia)");
                }

                BufferedReader brError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

                //3
                boolean huboError = false;
                while((linea = brError.readLine()) != null) {
                    if(!huboError) {
                        System.err.println("Errores: ");
                    }
                    System.err.println("   "+linea);
                    huboError = true;
                }
            
                brSalida.close();
                brError.close();

                int codigo = p.waitFor();
                System.out.println("Código de salida: " + codigo);

                if(codigo == 0) {
                        System.out.println("Ejecutado correctamente");

                } else {
                                    System.out.println("Falló (código: " + codigo + ")");

                }


        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Proceso interrumpido: " + e.getMessage());
        }        
    }
}