package tema03.T3S1P1ServidorEtern;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class T3S1P1TestClienteNAVARRO {
    public static void main(String[] args) {
        String host = "localhost";  // Cambia a IP real si pruebas en otra máquina.
        int puerto = 1234;
        Socket miCliente = null;
        DataInputStream entrada = null;
        DataOutputStream salida = null;
        
        try {
            // Paso 1: Conecta al servidor (pág. 17 del Bloc 1).
            miCliente = new Socket(host, puerto);
            System.out.println("Cliente conectado al servidor en " + host + ":" + puerto);
            
            // Paso 2: Crea streams (págs. 18-23).
            salida = new DataOutputStream(miCliente.getOutputStream());
            entrada = new DataInputStream(miCliente.getInputStream());
            
            // Paso 3: Envía mensaje al servidor.
            String mensaje = "¡Hola desde el cliente NAVARRO! Prueba número 1.";
            salida.writeUTF(mensaje);
            salida.flush();
            System.out.println("Mensaje enviado: " + mensaje);
            
            // Paso 4: Recibe respuesta del servidor.
            String respuesta = entrada.readUTF();
            System.out.println("Respuesta recibida: " + respuesta);
            
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        } finally {
            // Paso 5: Cierra en orden (pág. 26).
            try {
                if (salida != null) salida.close();
                if (entrada != null) entrada.close();
                if (miCliente != null) miCliente.close();
                System.out.println("Cliente desconectado.");
            } catch (IOException e) {
                System.err.println("Error cerrando cliente: " + e.getMessage());
            }
        }
    }
}