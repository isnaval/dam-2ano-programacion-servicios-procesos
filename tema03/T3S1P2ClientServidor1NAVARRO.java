/**
 * EXPLICACION DEL FLUJO QUE HE DEFINIDO: 
 * 1. Declaración de variables que son el puerto fijo y el host por defecto.
 * 2. El main verifica si hay argumentos: si sí, ejecuta Client con el host; si no, Servidor.
 * 
 * 3. Generamos la clase Client, 
 * 3.1 Declaro la variable host y en el constructor creo el socket cliente, flujos de entrada y salida.
 * 3.2. Declaro las variables y la funcionidad por pasos
 * 3.3. Con un try-catch-finally, envía mensajes y  lee respuesta y la muestra.
 * 3.4 & 3.5 manejo excepciones y cierro y libero recursos en orden.
 * 
 * 4. Generamos la Clase Servidor,
 * 4.1. Declaro las variables del servidor en el constructor Servidor y defino funcionalidad con un try-catch-finally
 * 4.2. Cuando recibe "Adeu", responde y cierra la conexión del cliente y el servidor.
 * 4.3. Manejo de excepciones y cierres en orden
 */
import java.io.*;
import java.net.*;

public class T3S1P2ClientServidor1NAVARRO {
    
    // 1. Declaración de variables: Puerto fijo (>1023, como en el ejemplo del profe pág. 17).
    static final int PUERTO = 5000; 
    static final String HOST_DEFAULT = "localhost"; 
    
    // 2. Esta parte me gusta mucho --> defino la función principal y en caso que haya argmentos se ejecuta el metodo client y si no servidor
    public static void main(String[] args) {
        if (args.length > 0) {
            new Client(args[0]);
        } else {
            new Servidor();
        }
    }
    
    // 3 Clase Client: Se conecta al servidor con el host proporcionado y envía mensajes fijos.
    static class Client {
        // 3.1. Declaro la variable host. y el constructor Client
        String host;
        
        Client(String h) {
            // 3.2. Declaro las variables del servidor en el constructor Client y defino funcionalidad con un try-catch-finally
            host = h;
            Socket socketCliente = null;
            DataOutputStream salida = null;
            DataInputStream entrada = null;
            
            try {
                // 3.3  En Client, para cada mensaje --> Envía con writeUTF, lee respuesta con readUTF y la muestra.
                // PAS 1: Crea el socket y conecta al servidor
                socketCliente = new Socket(host, PUERTO);
                System.out.println("Cliente conectado al servidor en " + host + ":" + PUERTO);
                
                // PAS 2: Crea flujos de salida y entrada .
                salida = new DataOutputStream(socketCliente.getOutputStream());
                entrada = new DataInputStream(socketCliente.getInputStream());
                
                // PAS 3: Envía "Hola" y lee respuesta.
                salida.writeUTF("Hola");
                salida.flush();
                System.out.println("Respuesta del servidor: " + entrada.readUTF());
                
                // PAS 4: Envía "Com estàs?" y lee respuesta.
                salida.writeUTF("Com estas?");
                salida.flush();
                System.out.println("Respuesta del servidor: " + entrada.readUTF());
            
                // PAS 5: Envía "como te llamas?" y lee respuesta.
                salida.writeUTF("Com et dius?");
                salida.flush();
                System.out.println("Respuesta del servidor: " + entrada.readUTF());
                
                // 2.4. Al enviar "Adios", lee la respuesta final y cierra la conexión.
                salida.writeUTF("Adeu");
                salida.flush();
                System.out.println("Respuesta final del servidor: " + entrada.readUTF());
                
            } catch (IOException e) {
                // 3.3. Manejo de excepciones simple.
                System.out.println("Error en el cliente: " + e.getMessage());
            } finally {
                // 3.4. Cierres en orden 
                try {
                    if (salida != null) salida.close();
                    if (entrada != null) entrada.close();
                    if (socketCliente != null) socketCliente.close();
                    System.out.println("Conexión del cliente cerrada.");
                } catch (IOException e) {
                    System.out.println("Error al cerrar cliente: " + e.getMessage());
                }
            }
        }
    }
    
    // 4. Clase Servidor: .
    static class Servidor {
        Servidor() {

            //4.1. Declaro las variables del servidor en el constructor Servidor y defino funcionalidad con un try-catch-finally
            ServerSocket miServicio = null;
            Socket socketServicio = null;
            DataInputStream entrada = null;
            DataOutputStream salida = null;
            
            try {
                // PAS 1: Crea el ServerSocket
                miServicio = new ServerSocket(PUERTO);
                System.out.println("Servidor iniciado en el puerto " + PUERTO);
                System.out.println("Esperando cliente...");
                
                // PAS 2: Acepta un cliente
                socketServicio = miServicio.accept();
                System.out.println("Cliente conectado: " + socketServicio.getInetAddress().getHostAddress());
                
                // PAS 3: Crea flujos).
                entrada = new DataInputStream(socketServicio.getInputStream());
                salida = new DataOutputStream(socketServicio.getOutputStream());
                
                // 2.6. En Servidor, para cada mensaje: Lo muestra por pantalla y responde según el tipo.
                String mensajeCliente;
                while (!(mensajeCliente = entrada.readUTF()).equals("Adeu")) {
                    // Muestra el mensaje recibido 
                    System.out.println("Mensaje del cliente: " + mensajeCliente);
                    
                    // Responde según el mensaje 
                    if ("Hola".equals(mensajeCliente)) {
                        salida.writeUTF("Hola soc el servidor");
                    } else if ("Com estas?".equals(mensajeCliente)) {
                        salida.writeUTF("Molt be");
                    } else if ("Com et dius?".equals(mensajeCliente)) {
                        salida.writeUTF("Em dic Servidor");
                    } else {
                        // Para otros mensajes, respuesta genérica
                        salida.writeUTF("Missatge no reconegut");
                    }
                    salida.flush();
                }
                
                //4.2. Cuando escucha Adeu - se cierra la conexión
                System.out.println("Adeu recibido, cerrando conexión.");
                salida.writeUTF("Adeu client");
                salida.flush();
                
            } catch (IOException e) {
                // 4.3. Manejo de excepciones simple y se librean los recuersos en el finally
                System.out.println("Error en el servidor: " + e.getMessage());
            } finally {
                try {
                    if (salida != null) salida.close();
                    if (entrada != null) entrada.close();
                    if (socketServicio != null) socketServicio.close();
                    if (miServicio != null) miServicio.close();
                    System.out.println("Servidor cerrado.");
                } catch (IOException e) {
                    System.out.println("Error al cerrar servidor: " + e.getMessage());
                }
            }
        }
    }
}