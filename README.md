# Programación de Servicios y Procesos - 2º DAM
Repositorio de ejercicios de **Programación de Servicios y Procesos (PSP)** del ciclo **Desarrollo de Aplicaciones Multiplataforma (DAM)**.

---

## 📋 Contenido

Este repositorio contiene ejercicios prácticos de programación concurrente y multihilo en dos lenguajes:

- **Tema 1:** Programación Multiproceso en **C**
- **Tema 2:** Programación Multihilo en **Java**

---

## 🎯 Tema 1: Programación Multiproceso (C)

### Objetivos
- Creación y duplicación de procesos
- Comunicación entre procesos
- Sincronización de procesos
- Tuberías (pipes) y señales

### Prácticas Realizadas
- Creación de procesos con `fork()`
- IPC (Inter-Process Communication)
- Sincronización con semáforos

---

## 🎯 Tema 2: Programación Multihilo (Java)

### Objetivos
- Creación de threads
- Sincronización de hilos
- Coordinación con `wait()` y `notify()`
- Resolución de problemas clásicos de concurrencia

### Conceptos Clave
####Thread
Unidad de ejecución concurrente. Cada thread ejecuta su propio código de forma independiente y simultánea con otros threads en el mismo proceso.
####synchronized
Palabra clave que proporciona control de acceso exclusivo a métodos o bloques de código. Solo un thread puede ejecutar código sincronizado sobre el mismo objeto a la vez.
####wait()
Método que pausa la ejecución del thread actual y lo pone en espera. El thread libera el lock del objeto y espera a ser notificado por otro thread.
####notify()
Despierta exactamente uno de los threads que está esperando en wait() sobre el mismo objeto. Si múltiples threads esperan, se despierta solo uno (no determinista).
####notifyAll()
Despierta todos los threads que están esperando en wait() sobre el mismo objeto. Es más seguro que notify() en la mayoría de casos.
####Deadlock
Situación de bloqueo mutuo donde dos o más threads se quedan esperándose mutuamente, nunca pueden progresar. Ejemplo: Thread A espera por recurso de B, Thread B espera por recurso de A.
---

## 🔧 Métodos Utilizados por Ejercicio
TEMA 1: Programación Multiproceso (C)
Ejercicio 1: Creación de Procesos

fork() - Duplica el proceso actual. Sirve para crear un nuevo proceso hijo idéntico al padre.
getpid() - Obtiene el ID del proceso actual. Se usa para identificar el proceso en ejecución.
wait() - Pone en pausa el proceso padre hasta que terminen los procesos hijo.
exit() - Finaliza el proceso actual con un código de estado.

Ejercicio 2: Variables Compartidas

fork() - Crea procesos hijo (heredan variables del padre pero con copia independiente).
getpid() - Identifica qué proceso está ejecutando.
printf() - Muestra las variables y sus valores en cada proceso.

Ejercicio 3: Comunicación entre Procesos (Pipes)

pipe() - Crea un canal de comunicación unidireccional entre procesos.
fork() - Crea procesos que compartirán los descriptores del pipe.
read() - Lee datos del pipe.
write() - Escribe datos en el pipe.
close() - Cierra el descriptor del pipe.

Ejercicio 4: Señales

signal() - Instala un manejador para una señal específica.
kill() - Envía una señal a un proceso.
pause() - Pausa el proceso hasta recibir una señal.

Ejercicio 5: Semáforos

semget() - Crea o accede a un conjunto de semáforos.
semop() - Realiza operaciones atómicas sobre semáforos.
semctl() - Controla los semáforos.


TEMA 2: Programación Multihilo (Java)
T1S2Ejemplos: Introducción a Threads
Métodos básicos:

Thread.start() - Inicia la ejecución del thread. Llama a run() en un nuevo hilo.
Thread.run() - Contiene el código que ejecutará el thread. Se sobrescribe en clases que extienden Thread.
Thread.sleep(long ms) - Pausa el thread actual durante milisegundos especificados.


T2S5Ex1: Productor-Consumidor SIN Sincronización
El problema:

put(int) - Coloca valor sin control → Consumidor pierde datos.
get() - Obtiene valor sin esperar → Intenta leer cuando está vacío.
sleep(long) - Simula tiempo pero no sincroniza.

¿Qué falla? Acceso simultáneo sin control causa condiciones de carrera.

T2S5Ex2: Productor-Consumidor CON Sincronización
La solución:

synchronized put(int valor) - Bloquea acceso exclusivamente durante escritura.
synchronized int get() - Bloquea acceso exclusivamente durante lectura.
wait() - Pausa el thread si la condición no se cumple.
notifyAll() - Despierta threads en espera cuando hay cambios.

¿Qué mejora? Coordinación perfecta: consumidor espera si vacío, productor espera si lleno.

T2S4P8Compte: Cuenta Bancaria Simple
Métodos sincronizados:

synchronized ingresar(int cantidad) - Suma cantidad al saldo (acceso exclusivo).
synchronized retirar(int cantidad) - Resta cantidad al saldo (acceso exclusivo).
synchronized getSaldo() - Obtiene saldo actual (acceso exclusivo).


T2S5P9Compte2: Cuenta con wait/notify (DEADLOCK)
Métodos principales:
1. synchronized void ingres(int cantidad, String nombre)

Sirve: Ingresar dinero a la cuenta.
Lógica:

     MIENTRAS (saldo + cantidad > máximo)
         ESPERAR (wait)
     HACER ingreso
     DESPERTAR todos (notifyAll)

Causa deadlock: Dos personas esperan ingresar pero saldo está al máximo.

2. synchronized void reintegrament(int cantidad, String nombre)

Sirve: Retirar dinero de la cuenta.
Lógica:

     MIENTRAS (saldo < cantidad)
         ESPERAR (wait)
     HACER retirada
     DESPERTAR todos (notifyAll)

Causa deadlock: Dos personas esperan retirar pero hay poco saldo.

Escenario clásico de deadlock:
Saldo: 47€, Máximo: 500€
├─ Persona1 quiere retirar: 112€  → wait() (insuficiente)
└─ Persona2 quiere retirar: 345€  → wait() (insuficiente)
                ↓
         DEADLOCK PERMANENTE
  (Ambas esperan que la otra ingrese dinero)

T2S5BarberEjericio: Problema del Barbero
Métodos principales:
1. synchronized void entrar_cliente(String nombre)

Sirve: Que cliente entre a barbería.
Lógica:

     SI (no hay cadiras)
         Cliente se va
     SINO
         Ocupar cadira
         SI (barbero duerme)
             Despertarlo
         ESPERAR a ser atendido
2. synchronized void dormir_barbero()

Sirve: Dormir barbero si no hay clientes.
Lógica:

     MIENTRAS (no hay clientes)
         Barbero DUERME
         ESPERAR (wait)
     Barbero CORTA cabello
3. synchronized void terminar_corte()

Sirve: Finalizar corte y liberar sitio.
Lógica:

     Liberar cadira
     DESPERTAR clientes esperando (notifyAll)
4. synchronized void salir_cliente(String nombre)

Sirve: Cliente se va después del corte.
Lógica:

     NOTIFICAR cambios
