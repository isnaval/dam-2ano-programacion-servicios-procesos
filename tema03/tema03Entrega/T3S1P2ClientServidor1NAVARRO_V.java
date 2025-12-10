import java.io.*;
import java.net.*;

/**
 * DEFINO EL FLUJO  DE DATOS
 * 1. Declaro variables para puerto y host (parametro de entrada)
 * 2. Declaro un constructor vacio
 * 
 * 3. Metodo para iniciar el servidor
 *   3.1. declaro un servidor con argumento del purto
 *   3.2. Genero un bucle infinito para atender clientes
 *   3.3. Flujos de entrada y salida
 *   3.4. Flujos de mensajes
 * 4. Método para iniciar el cliente
 *   4.1. declaro un socket con argumento del host y puerto
 *   4.2. Flujos de entrada y salida
 *   4.3. Flujos de mensajes
 * 5. Método main para ejecutar
 * 
 * IMPORTANTE: a la hora de iiniciar el servidor el argumento sera "servidor"
 */

public class T3S1P2ClientServidor1NAVARRO_V {
    //1. Declaro variables para puerto (5000) y host (parametro de entrada)
    static final int PUERTO = 5000;
    static final String HOST = "localhost";

    //2. Declaro un constructor vacio 
    public T3S1P2ClientServidor1NAVARRO_V() {
    }

    //3. Metodo para iniciar el servidor
     //3.1. declaro un servidor con argumento del purto
     //3.2. Genero un bucle infinito para atender clientes
    public void iniciarServidor() {
        try {
            ServerSocket ssServidor = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando en el puerto " + PUERTO);
            Socket sCliente = ssServidor.accept();
            System.out.println("Cliente conectado");
            //3.3. Flujos de entrada y salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(sCliente.getInputStream()));
            PrintWriter salida = new PrintWriter(sCliente.getOutputStream(), true);

            //3.4. Flujos de mensajes
            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Cliente: " + mensaje);
                switch (mensaje) {
                    case "Hola":
                        salida.println("Hola sóc el servidor");
                        break;
                    case "Com estàs?":
                        salida.println("Molt bé");
                        break;
                    case "Adeu":
                        salida.println("Fins després");
                        break;
                }

                if (mensaje.equals("Adeu")) break;
            }

            // Cierro y libero conexiones
            sCliente.close();
            ssServidor.close();
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    //4. Método para iniciar el cliente
     //4.1. declaro un socket con argumento del host y puerto
     //4.2. Flujos de entrada y salida
     //4.3. Flujos de mensajes
    public void iniciarCliente() {
        try {
            // declaro 
            Socket sCliente = new Socket(HOST, PUERTO);

            // Flujo de entrada y salida
            PrintWriter salida = new PrintWriter(sCliente.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(sCliente.getInputStream()));

            // Flujos de mensajes
            salida.println("Hola");
            System.out.println("Servidor: " + entrada.readLine());
            salida.println("Com estàs?");
            System.out.println("Servidor: " + entrada.readLine());
            salida.println("Adeu");
            System.out.println("Servidor: " + entrada.readLine());

            // Cierro y libero conexiones
            sCliente.close();
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }

    // 5. Método main para ejecutar
    public static void main(String[] args) {
        T3S1P2ClientServidor1NAVARRO_V conexion = new T3S1P2ClientServidor1NAVARRO_V();
        
        if (args.length > 0) {
            if (args[0].equals("servidor")) {
                conexion.iniciarServidor();
            } else if (args[0].equals("cliente")) {
                conexion.iniciarCliente();
            } else {
                System.out.println("Uso: java SERVIDOR [servidor|cliente]");
            }
        } else {
            System.out.println("Uso: java SERVIDOR [servidor|cliente]");
        }
    }
}