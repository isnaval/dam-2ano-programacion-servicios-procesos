### **1. Compte.java**

- **Rol**: Gestiona la cuenta bancaria (saldo)
- **Qué hace**: Almacena el saldo, permite retirar dinero de forma sincronizada
- **Métodos principales**:
  - `getSaldo()` - retorna el saldo actual
  - `restar(int cantidad)` - resta dinero del saldo
  - `RetirarDiners(int cant, String nom)` - retira dinero con validación

### **2. Persona.java**

- **Rol**: Representa un cliente (thread)
- **Qué hace**: Extiende `Thread` para ejecutar retiros en paralelo
- **Método**: `run()` - ejecuta 4 retiros de 10 unidades

### **3. UsPersonaCompte.java**

- **Rol**: Clase principal (con `main()`)
- **Qué hace**: Crea una cuenta con 40 unidades y dos clientes (Anna y Joan) que retiran simultáneamente

---

## Cómo compilar (estoy en VSC)

**Desde la raíz del proyecto** (donde están las carpetas `src/` y `bin/`):

```powershell
javac -d bin src/T2S4P8ComptePLANTILLA/*.java
```

Esto compila todos los `.java` y genera los `.class` en `bin/`.

---

## Cómo ejecutar

**Desde la misma raíz del proyecto**:

```powershell
java -cp bin T2S4P8ComptePLANTILLA.UsPersonaCompte
```

---

## Output esperado

```
Anna va a retirar saldo (saldo actual es: 40)
Joan va a retirar saldo (saldo actual es: 40)
Anna retira => : 10. Saldo Actual: 30
Joan retira => : 10. Saldo Actual: 20
...
```

---

## Notas técnicas

- Uso de `synchronized` para proteger acceso concurrente al saldo
- uso de `Thread.sleep()` para simular delay en operaciones
- Múltiples threads accediendo al mismo recurso de forma segura
