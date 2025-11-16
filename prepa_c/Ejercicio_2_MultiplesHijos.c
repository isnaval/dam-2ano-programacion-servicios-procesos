/**
 * EJERCICIO 2: MÚLTIPLES PROCESOS EN BUCLE
 * Objetivo: Crear N procesos en un bucle
 */

#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main()
{
    printf("=== EJERCICIO 2: MÚLTIPLES PROCESOS ===\n\n");

    int N = 3;

    for (int i = 0; i < N; i++)
    {
        int pid = fork();

        if (pid == 0)
        {
            printf("Soy el hijo %d (PID: %d)\n", i, getpid());
            printf("Mi padre es: %d\n\n", getppid());
            return 0;
        }
    }

    printf("Soy el padre (PID: %d)\n", getpid());
    printf("Esperando a los %d hijos...\n\n", N);

    for (int i = 0; i < N; i++)
    {
        wait(NULL);
    }

    printf("Todos los hijos han terminado\n");

    return 0;
}