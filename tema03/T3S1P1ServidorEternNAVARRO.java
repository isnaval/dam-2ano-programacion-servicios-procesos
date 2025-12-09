package tema03;

/**
 * EXPLICACION DEL FLUJO QUE HE DEFINIDO: 
 * 1. Declaración de variables el puerto y el ServerSocket.
 * 2. Creamos un servidor eterno usando excepciones para manejar errores.
 * 2.1. Creo el servidor en el puerto indicado en 1234 y logueo la información
 * 2.2. utilizo un while (dandole con true) - y defino las variables del sock y entrada y salida
 * 2.3. Asigno valores a las variables e inicio la comunicación con el cliente
 * 2.4. Gestiono la comunicación con el cliente leyendo el mensaje que me envía
 * 2.5. Envío una respuesta al cliente incluyendo un contador de clientes donde le indico el número de cliente que es y el mensaje que me ha enviado
 * 2.6. Cierro flujos y el socket del cliente para liberar recursos
 * 2.7. Manejo de excepciones y cierro el servidor en el finally
 */
import java.io.*;
import java.net.*;

public class T3S1P1ServidorEternNAVARRO {
    
    // 1. Declaración de variables: Puerto fijo 
    static final int PUERTO = 1234;
    static int contadorClientes = 0; 
    public static void main(String[] args) {
        
        // 2. Creamos un servidor eterno usando excepciones para manejar errores.
        ServerSocket miServicio = null; 
        
        try {
            // 2.1. Creo el servidor en el puerto indicado y logueo la información.
            miServicio = new ServerSocket(PUERTO);
            System.out.println("Servidor eterno iniciado en el puerto " + PUERTO);
            System.out.println("Presiona Ctrl + C para finalizar el seervidor");
            
            // 2.2. Para generar un puerto eterno, voy a utilizar la logica mediante un bucle usando un while (dandole con true).
            while (true) { 
                Socket socketServicio = null;
                DataInputStream entrada = null;  
                DataOutputStream salida = null; 
                
                try {
                    // 2.3. Asigno valores a las variables que he definido previamente y inicio la comunicación con el cliente.
                    System.out.println("Esperando cliente...");
                    socketServicio = miServicio.accept();  
                    System.out.println("Cliente conectado: " + socketServicio.getInetAddress().getHostAddress());
                    
                    // 2.3. Creo los flujos de entrada y salida.
                    entrada = new DataInputStream(socketServicio.getInputStream());
                    salida = new DataOutputStream(socketServicio.getOutputStream());
                    
                    // 2.4. Gestiono la comunicación con el cliente leyendo el mensaje que me envía.
                    String mensajeCliente = entrada.readUTF(); 
                    System.out.println("Mensaje del cliente: " + mensajeCliente);
                    
                    // 2.5. Envío una respuesta al cliente incluyendo un contador de clientes donde le indico el número de cliente que es y el mensaje que me ha enviado.
                    contadorClientes++;
                    salida.writeUTF("Hola cliente " + contadorClientes + ", has enviado: " + mensajeCliente);
                    salida.flush(); 
                    
                } catch (IOException e) {
                    System.out.println("Error en la comunicación con el cliente: " + e.getMessage()); 
                } finally {
                    // 2.6. Cierro los flujos y el socket y liberar recursos
                    try {
                        if (entrada != null) entrada.close();
                        if (salida != null) salida.close();
                        if (socketServicio != null) socketServicio.close();
                        System.out.println("Conexión con el cliente cerrada.");
                    } catch (IOException e) {
                        System.out.println("Error al cerrar la conexión con el cliente: " + e.getMessage());
                    }
                }
            }
            
        } catch (IOException e) {
            // 2.7. Manejo de excepciones y cierro el servidor.
            System.out.println("Error al iniciar el servidor: " + e.getMessage());
        } finally {
            try {
                if (miServicio != null) miServicio.close();
                System.out.println("Servidor cerrado.");
            } catch (IOException e) {
                System.out.println("Error al cerrar el servidor: " + e.getMessage());
            }
        }
    }
}