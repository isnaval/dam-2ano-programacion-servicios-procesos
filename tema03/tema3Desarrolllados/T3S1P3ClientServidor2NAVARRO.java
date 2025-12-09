package tema03.tema3Desarrolllados;
/**
 * EXPLICACION DEL FLUJO QUE HE DEFINIDO:
 * 
 * 1. Declaración de variables: Se define el puerto fijo y el host por defecto.
 * 2. El main verifica si hay argumentos: si sí, ejecuta Client con el host; si no, Servidor.
 * 
 * 3. Generamos la clase Client, 
 * 3.1. Declaro la variable host y en el constructor creo el socket cliente, flujos de entrada y salida.
 * 3.2. REalizo la iteracion de lectura de teclado hasta recibir "Adeu":
 *      - Lee mensaje por teclado
 *      - Envía mensaje al servidor
 *      - Lee respuesta del servidor
 *      - Muestra respuesta (A MODO DE CRUD)
 *      - Al recibir "Adeu", rompe el bucle y cierra conexión.
 * 3.5. Manejo de excepciones y Y CIERRE para liberar recursos
 * 
 * 4. Defino la Clase Servidor
 * 4.1. Implemento servidor eterno con bucle while(true) para muchos clientes.
 * 4.2. las funcionalidades que pasaran para cada cliente: 
 *      - Acepta conexión
 *      - Lee mensajes hasta recibir "Adeu"
 *      - si recibe "Adeu", responde "Fins després" y cierra conexión con cliente.
 * 4.3. Defino el manejo de excepciones y cierre de recursos por cliente.
 */


import java.io.*;
import java.net.*;
import java.util.Scanner; 

public class T3S1P3ClientServidor2NAVARRO {
    
    // 1. Declaración de variables del puerto fijo y host por defecto.
    static final int PUERTO = 5000;
    static final String HOST_DEFAULT = "localhost";
    
    // 2. Defino la funcion principal y si hay argumento inicio Cliente y si no hay argumentos inicio el servidor
public static void main(String[] args) {
    if (args.length > 0) {
        new Client(args[0]);
    } else {
        new Client(HOST_DEFAULT); 
    }
}
    
    // 3. Defino la clase Client
    //3.1 Declaro la variable host y en el constructor y creo el socket cliente, flujos de entrada y salida.
    static class Client {
        String host;
        
        Client(String h) {
            host = h;
            Socket socketCliente = null;
            DataOutputStream salida = null;
            DataInputStream entrada = null;
            Scanner sc = null;
            
            try {
                socketCliente = new Socket(host, PUERTO);
                System.out.println("Cliente conectado a " + host + ":" + PUERTO);
                salida = new DataOutputStream(socketCliente.getOutputStream());
                entrada = new DataInputStream(socketCliente.getInputStream());
                sc = new Scanner(System.in);
                
                // 3.2. REalizo la iteracion de lectura de teclado hasta recibir "Adeu"
                System.out.println("Escribe mensajes (Adeu para acabar):");
                while (true) {
                    String mensaje = sc.nextLine();
                    salida.writeUTF(mensaje);
                    salida.flush();
                    String respuesta = entrada.readUTF();
                    System.out.println("Servidor: " + respuesta);
                    if ("Adeu".equals(mensaje)) {
                        break; 
                    }
                }
            // 3.5. Manejo de excepciones y Y CIERRE para liberar recursos
            } catch (IOException e) {
                System.out.println("Error en cliente: " + e.getMessage());
            } finally {
                try {
                    if (sc != null) sc.close();
                    if (salida != null) salida.close();
                    if (entrada != null) entrada.close();
                    if (socketCliente != null) socketCliente.close();
                } catch (IOException e) {
                    System.out.println("Error cierre cliente: " + e.getMessage());
                }
            }
        }
    }
    
    // 4. Clase Servidor con el constructor Servidor e inicio el socker con sus flujos de entrada y salida
    static class Servidor {
        // 4.1. Implemento servidor eterno con bucle while(true) para muchos clientes.
        Servidor() {
            ServerSocket miServicio = null;
            
            try {
                miServicio = new ServerSocket(PUERTO);
                System.out.println("Servidor eterno iniciado en puerto " + PUERTO);
                
                while (true) {
                    Socket socketServicio = null;
                    DataInputStream entrada = null;
                    DataOutputStream salida = null;
                    
                    try {
                        socketServicio = miServicio.accept();
                        System.out.println("Nuevo client conectado: " + socketServicio.getInetAddress().getHostAddress());
                        entrada = new DataInputStream(socketServicio.getInputStream());
                        salida = new DataOutputStream(socketServicio.getOutputStream());
                        
                        String mensaje;
                        while (!(mensaje = entrada.readUTF()).equals("Adeu")) {
                            System.out.println("Mensaje del client: " + mensaje);
                            // 4.2. las funcionalidades que pasaran para cada cliente
                            if ("Hola".equals(mensaje)) {
                                salida.writeUTF("Hola soc el servidor");
                            } else if ("Com estas?".equals(mensaje)) {
                                salida.writeUTF("Molt be");
                            } else if ("Com et dius?".equals(mensaje)) {
                                salida.writeUTF("Em dic Servidor");
                            } else {
                                salida.writeUTF("Missatge desconegut");
                            }
                            salida.flush();
                        }
                        // si recibe "Adeu", responde "Fins després" y cierra conexión con cliente
                        System.out.println("Adeu rebut, tancant client.");
                        salida.writeUTF("Fins després");
                        salida.flush();
                        
                    } catch (IOException e) {
                        System.out.println("Error con client: " + e.getMessage());
                    } finally {

                        try {
                            if (salida != null) salida.close();
                            if (entrada != null) entrada.close();
                            if (socketServicio != null) socketServicio.close();
                            System.out.println("Client desconnectat. Esperant següent...");
                        } catch (IOException e) {
                            System.out.println("Error cierre client: " + e.getMessage());
                        }
                    }
                }
                // 4.3. Defino el manejo de excepciones y cierre de recursos por cliente.
            } catch (IOException e) {
                System.out.println("Error inici servidor: " + e.getMessage());
            } finally {
                try {
                    if (miServicio != null) miServicio.close();
                } catch (IOException e) {
                    System.out.println("Error cierre servidor: " + e.getMessage());
                }
            }
        }
    }
}