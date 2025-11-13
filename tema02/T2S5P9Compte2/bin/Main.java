package tema02.T2S5P9Compte2.bin;
public class Main {
    public static void main(String[] args)  {
        Cuenta cuenta = new Cuenta(40, 500);
        Persona p1 = new Persona("Persona1", cuenta);
        Persona p2 = new Persona("Persona2", cuenta);
        p1.start();
        p2.start();

    }
    
}
