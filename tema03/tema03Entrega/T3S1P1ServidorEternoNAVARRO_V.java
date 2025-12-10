import java.io.*;
import java.net.*;

/**
 * DEFINO EL FLUJO  DE DATOS
 * 1. Declaro variables para puerto y host (parametro de entrada)
 * 2. Declaro un constructor vacio
 * 
 * 3. Metodo para iniciar el servidor
 *  3.1. declaro un servidor con argumento del purto
 *  3.2. Genero un bucle infinito para atender clientes
 * 4. Método para iniciar el cliente
 * 
 * 5. Método main para ejecutar
 * 
 * IMPORTNATE: a la hora de iiniciar el servidor el argumento sera "servidor"
 */

public class T3S1P1ServidorEternoNAVARRO_V {
    //1. Declaro variables para puerto y host (parametro de entrada)
    static final int PUERTO = 5000;
    static final String HOST = "localhost";

    //2. Declaro un constructor vacio
    public T3S1P1ServidorEternoNAVARRO_V() {

    }

    // 
    public void iniciarServidor() {
        try {
            //3.1. declaro un servidor con argumento del purto
            ServerSocket ssServidor = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            //3.2. Genero un bucle infinito para atender clientes
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

    //4. Método para iniciar el cliente
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

    //5. Método main para ejecutar
    public static void main(String[] args) {
        // Creo el objeto de la clase
        // para poder llamar a los metodos no estaticos Y o le doy argumento servidor o cliente o no le doy nada

        T3S1P1ServidorEternoNAVARRO_V servidor = new T3S1P1ServidorEternoNAVARRO_V();
        
        if (args.length > 0) {
            if (args[0].equals("servidor")) {
                servidor.iniciarServidor();
            } else if (args[0].equals("cliente")) {
                servidor.iniciarCliente();
            } else {
                System.out.println("Uso: java SERVIDOR ETERNO [servidor|cliente]");
            }
        } else {
            System.out.println("Uso: java SERVIDOR ETERNO [servidor|cliente]");
        }
    }
}