//Estructura bàsica fil
Class NomFil extends Thread {
	
	public void run() {

	}

}

/**
1. Heredar de Thread:
- Cuando extiendes `Thread`, estás diciendo "esta clase va a ejecutarse en paralelo"
- Todo lo que pongas en `run()` se ejecutará en ese hilo separado

2. El método `run()`:
- Es el "corazón" del hilo
- Contiene el código que se ejecutará cuando arranques el hilo
- Nunca llames directamente `run()` - eso lo ejecutaría en el hilo principal
- Debes llamar a `start()` para lanzar el hilo correctamente

3. Ciclo de vida básico:
new NomFil() → start() → run() ejecutándose → termina
 */

 /**
┌─────────────────────────────────────────────────────┐
│           Clase Thread (del sistema Java)           │
│  ┌──────────────────────────────────────────────┐   │
│  │ Métodos importantes:                         │   │
│  │ • start()      → Lanza el hilo               │   │
│  │ • run()        → DEBES SOBRESCRIBIR esto     │   │
│  │ • join()       → Espera a que termine        │   │
│  │ • sleep()      → Pausa el hilo               │   │
│  │ • isAlive()    → ¿Está ejecutándose?         │   │
│  │ • getName()    → Nombre del hilo             │   │
│  │ • getPriority()→ Prioridad del hilo          │   │
│  └──────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────┘
         ↑
         │ extends (heredamos)
         │
    ┌────┴─────────────────┐
    │  Tu clase MiHilo     │
    │  extends Thread      │
    ├──────────────────────┤
    │ public void run() {  │
    │   // TU CÓDIGO       │
    │ }                    │
    └──────────────────────┘
  */