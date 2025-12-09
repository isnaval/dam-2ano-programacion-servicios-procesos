import java.io.*;
import java.net.*;

public class T3S1P2ClientServidor1NAVARRO_V {
    // Puerto común para servidor y cliente
    static final int PUERTO = 5000;
    static final String HOST = "localhost";

    // Método constructor vacío
    public T3S1P2ClientServidor1NAVARRO_V() {
        // Constructor vacío según especificaciones
    }

    // Método para iniciar el servidor
    public void iniciarServidor() {
        try {
            ServerSocket ssServidor = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            // Aceptar conexión de un cliente
            Socket sCliente = ssServidor.accept();
            System.out.println("Cliente conectado");

            // Flujos de entrada y salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(sCliente.getInputStream()));
            PrintWriter salida = new PrintWriter(sCliente.getOutputStream(), true);

            // Procesar mensajes
            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Cliente: " + mensaje);

                // Responder según el mensaje
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

            // Cerrar conexiones
            sCliente.close();
            ssServidor.close();
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    // Método para iniciar el cliente
    public void iniciarCliente() {
        try {
            Socket sCliente = new Socket(HOST, PUERTO);

            // Flujos de entrada y salida
            PrintWriter salida = new PrintWriter(sCliente.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(sCliente.getInputStream()));

            // Enviar mensajes predefinidos
            salida.println("Hola");
            System.out.println("Servidor: " + entrada.readLine());

            salida.println("Com estàs?");
            System.out.println("Servidor: " + entrada.readLine());

            salida.println("Adeu");
            System.out.println("Servidor: " + entrada.readLine());

            // Cerrar conexión
            sCliente.close();
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }

    // Método main para ejecutar
    public static void main(String[] args) {
        T3S1P2ClientServidor1NAVARRO_V conexion = new T3S1P2ClientServidor1NAVARRO_V();
        
        if (args.length > 0) {
            if (args[0].equals("servidor")) {
                conexion.iniciarServidor();
            } else if (args[0].equals("cliente")) {
                conexion.iniciarCliente();
            } else {
                System.out.println("Uso: java T3S1P2ClientServidor1NAVARRO_V [servidor|cliente]");
            }
        } else {
            System.out.println("Uso: java T3S1P2ClientServidor1NAVARRO_V [servidor|cliente]");
        }
    }
}