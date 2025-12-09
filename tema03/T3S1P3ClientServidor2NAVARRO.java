/**
 * EXPLICACION DEL FLUJO QUE HE DEFINIDO: 
 * 1. Declaración de variables: Se define el puerto fijo y el host por defecto.
 * 2. Creamos un programa modificado de T3P2 con input de teclat en client y servidor eterno.
 * 2.1. El main verifica si hay argumentos: si sí, ejecuta Client con el host; si no, Servidor.
 * 2.2. Clase Client: Se conecta al servidor, lee de teclat con Scanner hasta "Adeu", envía cada mensaje y muestra respuesta.
 * 2.3. En Client, loop while: Lee línea de teclat, envía, si "Adeu" rompe; lee respuesta y muestra.
 * 2.4. Al "Adeu", envía, lee "Fins després", cierra conexión y acaba.
 * 2.5. Clase Servidor: Crea ServerSocket, bucle while(true) para múltiples clients, acepta uno por vez.
 * 2.6. En Servidor, para cada client: Loop while hasta "Adeu", lee mensaje, muestra, responde fijo (Hola -> "Hola sóc el servidor", etc.).
 * 2.7. Al "Adeu", responde "Fins després", cierra client (no servidor), sigue esperando siguiente.
 * 2.8. Manejo de excepciones simple en try-catch para IOException.
 * 2.9. Cierres en orden: Flujos primero, socket después (pág. 27 del Bloc 1).
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;  // Para input teclat (nuevo en T3P3).

public class T3S1P3ClientServidor2NAVARRO {
    
    // 1. Declaración de variables: Puerto fijo y host por defecto.
    static final int PUERTO = 5000;
    static final String HOST_DEFAULT = "localhost";
    
    // 2.1. El main verifica si hay argumentos.
    public static void main(String[] args) {
        if (args.length > 0) {
            new Client(args[0]);
        } else {
            new Servidor();
        }
    }
    
    // 2.2. Clase Client: Lee de teclat hasta "Adeu".
    static class Client {
        String host;
        
        Client(String h) {
            host = h;
            Socket socketCliente = null;
            DataOutputStream salida = null;
            DataInputStream entrada = null;
            Scanner sc = null;
            
            try {
                // PAS 1: Conecta y crea flujos (pág. 17-23).
                socketCliente = new Socket(host, PUERTO);
                System.out.println("Cliente conectado a " + host + ":" + PUERTO);
                salida = new DataOutputStream(socketCliente.getOutputStream());
                entrada = new DataInputStream(socketCliente.getInputStream());
                sc = new Scanner(System.in);
                
                // 2.3. Loop while: Lee teclat, envía, lee respuesta.
                System.out.println("Escribe mensajes (Adeu para acabar):");
                while (true) {
                    String mensaje = sc.nextLine();
                    salida.writeUTF(mensaje);
                    salida.flush();
                    String respuesta = entrada.readUTF();
                    System.out.println("Servidor: " + respuesta);
                    if ("Adeu".equals(mensaje)) {
                        break;  // 2.4. Acaba al "Adeu".
                    }
                }
                
            } catch (IOException e) {
                // 2.8. Manejo simple.
                System.out.println("Error en cliente: " + e.getMessage());
            } finally {
                // 2.9. Cierres.
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
    
    // 2.5. Clase Servidor: Eterno con while(true) para múltiples clients.
    static class Servidor {
        Servidor() {
            ServerSocket miServicio = null;
            
            try {
                // PAS 1: Crea ServerSocket (pág. 28).
                miServicio = new ServerSocket(PUERTO);
                System.out.println("Servidor eterno iniciado en puerto " + PUERTO);
                
                // 2.5. Bucle eterno para múltiples clients.
                while (true) {
                    Socket socketServicio = null;
                    DataInputStream entrada = null;
                    DataOutputStream salida = null;
                    
                    try {
                        // 2.6. Acepta client y loop mensajes hasta "Adeu".
                        socketServicio = miServicio.accept();
                        System.out.println("Nuevo client conectado: " + socketServicio.getInetAddress().getHostAddress());
                        entrada = new DataInputStream(socketServicio.getInputStream());
                        salida = new DataOutputStream(socketServicio.getOutputStream());
                        
                        String mensaje;
                        while (!(mensaje = entrada.readUTF()).equals("Adeu")) {
                            System.out.println("Mensaje del client: " + mensaje);
                            // 2.6. Responde fijo (esquema T3P2, orden no importa).
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
                        
                        // 2.7. Al "Adeu", responde "Fins després" y cierra client.
                        System.out.println("Adeu rebut, tancant client.");
                        salida.writeUTF("Fins després");
                        salida.flush();
                        
                    } catch (IOException e) {
                        System.out.println("Error con client: " + e.getMessage());
                    } finally {
                        // 2.9. Cierres por client.
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
                // No arriba aquí (etern).
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