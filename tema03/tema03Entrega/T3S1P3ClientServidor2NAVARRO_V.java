import java.io.*;
import java.net.*;

/**
 * DEFINO EL FLUJO  DE DATOS
 * 1. Declaro variables para puerto y host (parametro de entrada)
 * 2. Declaro un constructor vacio
 * 
 * 3. Metodo para iniciar el servidor
 *   3.1. declaro un servidor con argumento del purto
 *   3.2 Aceptar conexiones de clientes
 *   3.3 Defino los Flujos de entrada y salida
 *   3.4. Procesar mensajes con el usuario
 *   3.5 Cerrar conexiones y libero recursos

 * 4. Método para iniciar el cliente
 *   4.1. declaro un socket con argumento del host y puerto
 *   4.2. Flujos de entrada y salida
 *   4.3. Flujos de mensajes
 * 5. Método main para ejecutar
 * 
 * IMPORTANTE: a la hora de iiniciar el servidor el argumento sera "servidor"
 */

public class T3S1P3ClientServidor2NAVARRO_V {
    // 1. Declaro variables para puerto y host
    static final int PUERTO = 5000;
    static final String HOST = "localhost";

    // 2. Declaro un constructor vacio
    public T3S1P3ClientServidor2NAVARRO_V() {
    }

    // 3. Metodo para iniciar el servidor
    public void iniciarServidor() {
        try {
            // 3.1. declaro un servidor con argumento del puwrto
            ServerSocket ssServidor = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando en el puerto " + PUERTO);
            
            // 3.2 Aceptar conexiones de clientes
            Socket sCliente = ssServidor.accept();
            System.out.println("Cliente conectado");

            // 3.3 Defino los Flujos de entrada y salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(sCliente.getInputStream()));
            PrintWriter salida = new PrintWriter(sCliente.getOutputStream(), true);

            // 3.4. Procesar mensajes con el usuario 
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
                    default:
                        salida.println("Missatge no reconegut");
                }

                if (mensaje.equals("Adeu"))
                    break;
            }

            // 3.5 Cerrar conexiones
            sCliente.close();
            ssServidor.close();
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    //4. Método para iniciar el cliente
    public void iniciarCliente() {
        try {
            //4.1. DECLARO un socket con argumento del host y puerto
            Socket sCliente = new Socket(HOST, PUERTO);

            //4.2. Flujos de entrada y salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(sCliente.getInputStream()));
            PrintWriter salida = new PrintWriter(sCliente.getOutputStream(), true);
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            String mensaje;
            do {
                //4.3. Flujos de mensajes y la interacción con el usuario
                System.out.print("Introduce mensaje: ");
                mensaje = teclado.readLine();

                //4.4. Enviar mensaje al servidor
                salida.println(mensaje);

                //4.5. Recibir respuesta en caso que se cierre el servidor
                if (!mensaje.equals("Adeu")) {
                    String respuesta = entrada.readLine();
                    System.out.println("Servidor: " + respuesta);
                }

            } while (!mensaje.equals("Adeu"));

            // 4.6. Cerrar conexión
            sCliente.close();
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }

    // 5. Método main para ejecutar
    public static void main(String[] args) {
        T3S1P3ClientServidor2NAVARRO_V conexion = new T3S1P3ClientServidor2NAVARRO_V();

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