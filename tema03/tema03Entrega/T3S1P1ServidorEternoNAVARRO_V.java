import java.io.*;
import java.net.*;

public class T3S1P1ServidorEternoNAVARRO_V {
    // Puerto común para servidor y cliente
    static final int PUERTO = 5000;
    static final String HOST = "localhost";

    // Método constructor vacío
    public T3S1P1ServidorEternoNAVARRO_V() {
        // Constructor vacío según especificaciones
    }

    // Método para iniciar el servidor
    public void iniciarServidor() {
        try {
            ServerSocket ssServidor = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            // Bucle infinito para atender clientes
            while (true) {
                Socket sCliente = ssServidor.accept();
                System.out.println("Cliente conectado: ");
                OutputStream aux = sCliente.getOutputStream();
                DataOutputStream flux = new DataOutputStream(aux);
                flux.writeUTF("Hola client, soy un servidor ETERNO!");
                sCliente.close();
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    // Método para iniciar el cliente
    public void iniciarCliente() {
        try {
            Socket sCliente = new Socket(HOST, PUERTO);
            InputStream aux = sCliente.getInputStream();
            DataInputStream flux = new DataInputStream(aux);
            System.out.println(flux.readUTF());
            sCliente.close();
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }

    // Método main para ejecutar
    public static void main(String[] args) {
        T3S1P1ServidorEternoNAVARRO_V servidor = new T3S1P1ServidorEternoNAVARRO_V();
        
        if (args.length > 0) {
            if (args[0].equals("servidor")) {
                servidor.iniciarServidor();
            } else if (args[0].equals("cliente")) {
                servidor.iniciarCliente();
            } else {
                System.out.println("Uso: java T3S1P1ServidorEternoNAVARRO_V [servidor|cliente]");
            }
        } else {
            System.out.println("Uso: java T3S1P1ServidorEternoNAVARRO_V [servidor|cliente]");
        }
    }
}