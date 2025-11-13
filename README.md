# Programación de Servicios y Procesos - 2º DAM

Repositorio de ejercicios de **Programación de Servicios y Procesos (PSP)** del ciclo **Desarrollo de Aplicaciones Multiplataforma (DAM)**.

---

## 📋 Contenido General

Este repositorio contiene ejercicios prácticos en dos lenguajes:

- **Tema 1:** Programación Multiproceso en **C**
- **Tema 2:** Programación Multihilo en **Java**

---

## 🎯 TEMA 1: Programación Multiproceso (C)

### Objetivos del Tema
- Creación y duplicación de procesos
- Comunicación entre procesos
- Sincronización de procesos
- Tuberías (pipes) y señales

### Ejercicio 1: Creación de Procesos

**Métodos utilizados:**
- `fork()` → Duplica el proceso actual. Crea un nuevo proceso hijo idéntico al padre.
- `getpid()` → Obtiene el ID del proceso actual. Se usa para identificar el proceso en ejecución.
- `wait()` → Pausa el proceso padre hasta que terminen los procesos hijo.
- `exit()` → Finaliza el proceso actual con un código de estado.

**¿Para qué sirve?** Implementar paralelismo dividiendo el trabajo en múltiples procesos.

---

### Ejercicio 2: Variables Compartidas

**Métodos utilizados:**
- `fork()` → Crea procesos hijo (heredan variables del padre pero con copia independiente).
- `getpid()` → Identifica qué proceso está ejecutando.
- `printf()` → Muestra las variables y sus valores en cada proceso.

**¿Para qué sirve?** Entender que cada proceso tiene su propia copia de variables (no están realmente compartidas).

---

### Ejercicio 3: Comunicación entre Procesos (Pipes)

**Métodos utilizados:**
- `pipe()` → Crea un canal de comunicación unidireccional entre procesos.
- `fork()` → Crea procesos que compartirán los descriptores del pipe.
- `read()` → Lee datos del pipe (extremo lectura).
- `write()` → Escribe datos en el pipe (extremo escritura).
- `close()` → Cierra el descriptor del pipe.

**¿Para qué sirve?** Permitir comunicación entre procesos usando tuberías.

---

### Ejercicio 4: Señales

**Métodos utilizados:**
- `signal()` → Instala un manejador para una señal específica.
- `kill()` → Envía una señal a un proceso.
- `pause()` → Pausa el proceso hasta recibir una señal.

**¿Para qué sirve?** Comunicación asíncrona entre procesos mediante señales del sistema.

---

### Ejercicio 5: Semáforos

**Métodos utilizados:**
- `semget()` → Crea o accede a un conjunto de semáforos.
- `semop()` → Realiza operaciones atómicas sobre semáforos (wait/signal).
- `semctl()` → Controla los semáforos (inicializa, elimina, etc.).

**¿Para qué sirve?** Sincronización entre procesos para controlar acceso a recursos compartidos.

---

## 🧵 TEMA 2: Programación Multihilo (Java)

### Objetivos del Tema
- Creación de threads
- Sincronización de hilos
- Coordinación con `wait()` y `notify()`
- Resolución de problemas clásicos de concurrencia

### Conceptos Fundamentales

#### 1. Thread
**Definición:** Unidad de ejecución concurrente dentro del mismo proceso.

**Características:**
- Cada thread ejecuta su código de forma independiente
- Múltiples threads comparten el mismo espacio de memoria
- Ejecutan simultáneamente (aparentemente)

#### 2. synchronized
**Definición:** Control de acceso exclusivo a métodos o bloques de código.

**Características:**
- Solo un thread puede ejecutar código sincronizado sobre el mismo objeto a la vez
- Previene condiciones de carrera
- Bloquea el acceso hasta que se libere

#### 3. wait()
**Definición:** Pausa la ejecución del thread actual y lo pone en espera.

**Características:**
- El thread libera el lock del objeto
- Se queda esperando a ser notificado por otro thread
- Usar SIEMPRE dentro de `while`, no de `if`

#### 4. notify()
**Definición:** Despierta exactamente uno de los threads que está esperando.

**Características:**
- Solo despierta UN thread de los que esperan
- No determinista (no se sabe cuál se despierta)
- Usar cuando hay UN thread esperando

#### 5. notifyAll()
**Definición:** Despierta todos los threads que están esperando.

**Características:**
- Despierta TODOS los threads en espera
- Más seguro que `notify()`
- Usar cuando hay MÚLTIPLES threads esperando

#### 6. Deadlock
**Definición:** Situación de bloqueo mutuo donde threads se quedan esperándose mutuamente.

**Características:**
- Nunca pueden progresar
- Ejemplo: Thread A espera recurso de B, Thread B espera recurso de A
- Causado por mal uso de sincronización

---

### T1S2Ejemplos: Introducción a Threads

**Métodos básicos:**
- `Thread.start()` → Inicia la ejecución del thread. Llama a `run()` en un nuevo hilo.
- `Thread.run()` → Contiene el código que ejecutará el thread. Se sobrescribe en clases que extienden Thread.
- `Thread.sleep(long ms)` → Pausa el thread actual durante milisegundos especificados.

**¿Para qué sirve?** Aprender lo básico sobre creación y ejecución de threads.

---

### T2S5Ex1: Productor-Consumidor SIN Sincronización

**El Problema:**

| Método | Función | Problema |
|--------|---------|----------|
| `put(int)` | Coloca valor en buffer | Sin control → Consumidor pierde datos |
| `get()` | Obtiene valor del buffer | Sin esperar → Intenta leer cuando está vacío |
| `sleep(long)` | Simula tiempo | No sincroniza acceso |

**¿Qué falla?** Acceso simultáneo sin control causa **condiciones de carrera**.

**Ejemplo:**
```
Productor pone: 0
Consumidor lee: 0 ✓
Consumidor intenta leer de nuevo: (vacío) ✗
Productor pone: 1
Consumidor lee: 1 ✓
(Pero puede perder datos si lee dos veces antes de escribir)
```

---

### T2S5Ex2: Productor-Consumidor CON Sincronización

**La Solución:**

| Método | Función | Mejora |
|--------|---------|--------|
| `synchronized put(int valor)` | Bloquea acceso exclusivamente durante escritura | Control garantizado |
| `synchronized int get()` | Bloquea acceso exclusivamente durante lectura | Control garantizado |
| `wait()` | Pausa si condición no se cumple | Productor espera si lleno, Consumidor si vacío |
| `notifyAll()` | Despierta threads esperando | Coordinación perfecta |

**¿Qué mejora?** Coordinación perfecta:
- Consumidor espera si buffer está vacío
- Productor espera si buffer está lleno
- Sin pérdida de información

---

### T2S4P8Compte: Cuenta Bancaria Simple

**Métodos sincronizados:**
- `synchronized ingresar(int cantidad)` → Suma cantidad al saldo (acceso exclusivo).
- `synchronized retirar(int cantidad)` → Resta cantidad al saldo (acceso exclusivo).
- `synchronized getSaldo()` → Obtiene saldo actual (acceso exclusivo).

**¿Para qué sirve?** Proteger saldo contra modificaciones simultáneas de múltiples threads.

---

### T2S5P9Compte2: Cuenta con wait/notify (Demuestra DEADLOCK)

**Método 1: `synchronized void ingres(int cantidad, String nombre)`**

```
FUNCIÓN: Ingresar dinero a la cuenta

LÓGICA:
    MIENTRAS (saldo + cantidad > máximo)
        ESPERAR (wait)
    
    HACER ingreso
    saldo = saldo + cantidad
    
    DESPERTAR todos (notifyAll)
```

**¿Causa deadlock?** Sí, cuando dos personas quieren ingresar pero el saldo ya está casi al máximo.

**Escenario:**
```
Saldo: 480€, Máximo: 500€
├─ Persona1 quiere ingresar: 50€  → wait() (480 + 50 > 500)
└─ Persona2 quiere ingresar: 100€ → wait() (480 + 100 > 500)
                    ↓
         DEADLOCK PERMANENTE
    (Ambas esperan que la otra retire dinero)
```

---

**Método 2: `synchronized void reintegrament(int cantidad, String nombre)`**

```
FUNCIÓN: Retirar dinero de la cuenta

LÓGICA:
    MIENTRAS (saldo < cantidad)
        ESPERAR (wait)
    
    HACER retirada
    saldo = saldo - cantidad
    
    DESPERTAR todos (notifyAll)
```

**¿Causa deadlock?** Sí, cuando dos personas quieren retirar pero hay poco saldo.

**Escenario:**
```
Saldo: 47€, Máximo: 500€
├─ Persona1 quiere retirar: 112€  → wait() (47 < 112)
└─ Persona2 quiere retirar: 345€  → wait() (47 < 345)
                ↓
         DEADLOCK PERMANENTE
    (Ambas esperan que la otra ingrese dinero)
```

---

### T2S5BarberEjericio: Problema del Barbero (Solución Exitosa)

**Método 1: `synchronized void entrar_cliente(String nombre)`**

```
FUNCIÓN: Que cliente entre a barbería

LÓGICA:
    SI (no hay cadiras disponibles)
        Cliente se va ✗
    
    SINO
        Ocupar cadira
        Decrementar cadiras disponibles
        
        SI (barbero está durmiendo)
            Despertarlo
            barbero_durmiendo = false
        
        ESPERAR a ser atendido
        wait()
```

**¿Para qué sirve?** Controlar entrada de clientes y despertar al barbero si está durmiendo.

---

**Método 2: `synchronized void dormir_barbero()`**

```
FUNCIÓN: Barbero duerme si no hay clientes

LÓGICA:
    MIENTRAS (no hay clientes)
        Barbero DUERME
        barbero_durmiendo = true
        
        ESPERAR (wait)
    
    Barbero DESPIERTA y CORTA cabello
```

**¿Para qué sirve?** Conservar recursos y evitar que barbero espere activamente.

---

**Método 3: `synchronized void terminar_corte()`**

```
FUNCIÓN: Finalizar corte y liberar sitio

LÓGICA:
    Liberar cadira
    Incrementar cadiras disponibles
    
    DESPERTAR clientes esperando
    notifyAll()
```

**¿Para qué sirve?** Permitir que entre el siguiente cliente y continuar con la barbería.

---

**Método 4: `synchronized void salir_cliente(String nombre)`**

```
FUNCIÓN: Cliente se va después del corte

LÓGICA:
    NOTIFICAR cambios
    notifyAll()
```

**¿Para qué sirve?** Sincronización final y notificación de cambios.

---

## 📊 COMPARATIVA RÁPIDA: C vs Java

### Procesos vs Threads

| Aspecto | Procesos (C) | Threads (Java) |
|--------|------------|----------------|
| **Creación** | `fork()` | `new Thread()` + `start()` |
| **Comunicación** | Pipes, Señales, IPC | Memoria compartida |
| **Sincronización** | Semáforos | `synchronized`, `wait()`, `notify()` |
| **Memoria** | Independiente | Compartida en el mismo proceso |

---

## 🔑 PUNTOS CLAVE PARA EXAMEN

### Diferencia Entre wait() y sleep()

```java
// ❌ INCORRECTO: sleep() NO libera el lock
synchronized void metodo() {
    Thread.sleep(1000);  // Mantiene el lock! ❌
}

// ✅ CORRECTO: wait() libera el lock
synchronized void metodo() {
    wait();  // Libera el lock ✓
}
```

### Usar WHILE, No IF

```java
// ❌ INCORRECTO: if
if (!condicion) {
    wait();
}

// ✅ CORRECTO: while
while (!condicion) {
    try {
        wait();
    } catch (InterruptedException e) {}
}
```

### Siempre notifyAll()

```java
synchronized void cambiar_estado() {
    // cambios...
    estado = nuevo_valor;
    
    notifyAll();  // ✅ IMPORTANTE: Siempre hacerlo
}
```

### Estructura Básica de Thread con Sincronización

```java
class MiThread extends Thread {
    private ObjetoCompartido objeto;
    
    public void run() {
        synchronized(objeto) {
            while (!condicion) {
                try {
                    objeto.wait();
                } catch (InterruptedException e) {}
            }
            // Hacer algo
            objeto.notifyAll();
        }
    }
}
```

---

## ✅ RESPUESTAS RÁPIDAS A PREGUNTAS COMUNES

### "¿Qué es un Deadlock?"
→ Bloqueo mutuo donde dos o más threads se esperan entre sí y nunca pueden progresar.

### "¿Cuándo usar notify() vs notifyAll()?"
→ `notify()` si UN thread espera | `notifyAll()` si MÚLTIPLES threads esperan

### "¿Por qué wait() debe estar en un while?"
→ Para verificar nuevamente la condición después de despertar (spurious wakeups).

### "¿Cuál es la diferencia entre fork() y Thread.start()?"
→ `fork()` crea un nuevo proceso | `Thread.start()` crea un nuevo hilo en el mismo proceso

### "¿Qué es un Pipe?"
→ Canal de comunicación unidireccional entre procesos (unidirectional).

### "¿Cómo se sincroniza en C?"
→ Con semáforos usando `semget()`, `semop()`, `semctl()`

### "¿Cómo se sincroniza en Java?"
→ Con `synchronized`, `wait()`, `notify()`, `notifyAll()`

---

## 📁 ESTRUCTURA CARPETAS IMPORTANTE

```
tema01/                     ← Programación Multiproceso (C)
├── Ejercicio 1 (fork)
├── Ejercicio 2 (variables)
├── Ejercicio 3 (pipes)
├── Ejercicio 4 (señales)
└── Ejercicio 5 (semáforos)

tema02/                     ← Programación Multihilo (Java)
├── T1S2Ejemplos
├── T2S4Sincronizacion
│   └── T2S4P8Compte
├── T2S5SincronizacionAvanzada
│   ├── T2S5Ex1ProductorConsumidorNoSincronitzat
│   ├── T2S5Ex2ProductorConsumidorSincronitzat
│   ├── T2S5P9Compte2                    ← DEADLOCK
│   └── T2S5BarberEjericio               ← SOLUCIÓN
└── T2S4TicTac
```

---

## 🎓 RESUMEN DE COMPETENCIAS

✅ Comprensión de **concurrencia** y **paralelismo**  
✅ Implementación de **sincronización de hilos**  
✅ Uso de **wait()** y **notify()**  
✅ Identificación y prevención de **deadlocks**  
✅ Resolución de **problemas clásicos de concurrencia**  
✅ Escritura de código **thread-safe**

---

**Última actualización:** 13 de Noviembre de 2025
