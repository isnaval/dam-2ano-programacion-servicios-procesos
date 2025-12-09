package tema03.T3S1P1ServidorEtern;  
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * EXPLICACIOND EL FLUJO QUE HE DEFINIDO: 
 * 1. Declaración de variables: Se define el puerto y el ServerSocket.
 * 2. Creamos un servidor eterno usando excepciones para manejar errores.
 * 2.1. Creo el servidor en el puerto indicado en este caso 1234  y logueo la información
 * 2.2. Para generar un puerto eterno, voy a utulizar la logica mediante un bucle usando un while (dandole cono true) - en este en punto defino las variables del sock y entrada y salida
 * 2.3. Asigno valores a las variables que he definido previamente y inicio la comunicación con el cliente
 * 2.4. Gestiono la comunicación con el cliente leyendo el mensaje que me envía
 * 2.5. Envío una respuesta al cliente incluyendo un contador de clientes donde le indico el número de cliente que es y el mensaje que me ha enviado
 * 2.6. Cierro los flujos y el socket del cliente para liberar recursos
 * 2.6. Manejo de excepciones al iniciar el servidor y cierro el servidor en el finally
*/


public class T3S1P1ServidorEternNAVARRO {
    static int contadorClientes = 0; 
   
    public static void main(String[] args) {
        
        // 1. Declaro la variable puerto asignandole el valor 1234 e inicio miServicio a null
        int puerto = 1234; 
        ServerSocket miServicio = null; 

        //2. Creamos un servidor con un try-catch-finally 
        try {
            // Pas.2.1. Creo el servidor en el puerto indicado
            miServicio = new ServerSocket(puerto);
            System.out.println("Servidor iniciado en el puerto " + puerto);
            System.out.println("Presionar Ctrl + C para finalizar el servidor");
            
            // Pas.2.2. Creo un bucle infinito para que el servidor esté siempre a la escucha de clientes - iniciado tres variables a null
            while (true) { 
                Socket socketServicio = null; 
                DataInputStream entrada = null; 
                DataOutputStream salida = null;

                try {
                    //Pas 2.3. creo los flujos - dandoles valor a las tres variables que he creado
                    System.out.println("Esperando cliente...");
                    socketServicio = miServicio.accept();
                    System.out.println("Cliente conectado: " + socketServicio.getInetAddress().getHostAddress());
                    entrada = new DataInputStream(socketServicio.getInputStream());
                    salida = new DataOutputStream(socketServicio.getOutputStream());

                    //Paso 2.4. Gestión de la comunicación con el cliente
                    String mensajeCliente = entrada.readUTF(); 
                    System.out.println("Mensaje del cliente: " + mensajeCliente);

                    // Pas. 2.5. Envío respuesta al cliente
                    contadorClientes++;
                    salida.writeUTF("Hola cliente " + contadorClientes + ", has enviado: " + mensajeCliente);
                    salida.flush();

                } catch (IOException e) {
                    System.out.println("Error en la comunicación con el cliente: " + e.getMessage());
                } finally {
                    // Paso 2.6. Cierro los flujos y el socket del cliente
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
        
        // 2.7. Manejo de excepciones al iniciar el servidor
        } catch (IOException e) {
            System.out.println("Error al iniciar el servidor: " + e.getMessage());
        } finally {
            try {
                if (miServicio != null) miServicio.close();
                System.out.print("Servidor Cerrado");
                
            } catch (IOException e) {  // Corregido: IOException para precisión (antes era Exception)
                System.out.println("Error al cerrar el servidor: " + e.getMessage());
            }
        }
    }
}

